package com.ping.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ping.dao.DBEntityManager;
import com.ping.dao.model.PingEntity;

/**
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Configuration
@EnableScheduling
public class IdleTimeOutManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdleTimeOutManager.class);

	@Autowired
	DBEntityManager dBEntityManager;

	@Autowired
	ConfigurationManager configurationManager;

	@Autowired
	LockManager lockManager;

	@Autowired
	LogProcessor logProcessor;

	/**
	 * Method to process the idle out requests
	 */
	@Scheduled(fixedDelayString = "${idleTimeOutJob.delay}")
	public void processTimeOutRequest() {
		boolean lockAcquired = false;
		long thresholdLimit = configurationManager.fetchThresholdLimit();
		try {
			lockAcquired = lockManager.lock();
			if (lockAcquired) {
				long currentTimestamp = System.currentTimeMillis();

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(
							"Processing the idle time out requests for thresholdTimestamp ::" + currentTimestamp);
				}

				Collection<PingEntity> fetchAllRecords = dBEntityManager.findAllLatestPingDetails();
				Map<String, Long> idleTimeoutClientDetails = new HashMap<String, Long>();
				for (PingEntity client : fetchAllRecords) {
					if ((currentTimestamp - client.getPingTimeStamp()) > thresholdLimit) {
						idleTimeoutClientDetails.put(client.getClientId(),
								currentTimestamp - client.getPingTimeStamp());

					}
				}
				logProcessor.processTimeOutRequests(idleTimeoutClientDetails);
			}
		} catch (Exception e) {
			LOGGER.error("Exception while processing the idleTimeOut requests", e);

		} finally {
			// If the lock has been acquired release the lock in the final block
			if (lockAcquired) {
				lockManager.unlock();
			}
		}

	}

}

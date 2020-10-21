package com.ping.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	FileManager fileManager;

	/**
	 * Method to process the idle out requests
	 */
	@Scheduled(fixedDelayString = "${idleTimeOutJob.delay}")
	public void processTimeOutRequest() {

		List<PingEntity> timeoutRequests = new ArrayList<PingEntity>();
		long thresholdLimit = configurationManager.fetchThresholdLimit();
		try {
			lockManager.lock();
			long thresholdTimestamp = System.currentTimeMillis();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Processing the idle time out requests for thresholdTimestamp ::" + thresholdTimestamp);
			}

			Collection<PingEntity> fetchAllRecords = dBEntityManager.findAllLatestPingDetails();
			for (PingEntity client : fetchAllRecords) {
				if ((thresholdTimestamp - client.getPingTimeStamp()) > thresholdLimit)
					client.setPingTimeStamp(client.getPingTimeStamp() + thresholdLimit);
				timeoutRequests.add(client);
			}

			if (LOGGER.isDebugEnabled()) {
				for (PingEntity entry : timeoutRequests)
					LOGGER.debug("ClientId:: " + entry.getClientId() + " ---TimeStamp::" + entry.getPingTimeStamp());
			}

			fileManager.processTimeOutRequests(timeoutRequests);
		} catch (Exception e) {
			LOGGER.error("Exception while processing the idleTimeOut requests", e);

		} finally {
			lockManager.unlock();
		}

	}

}

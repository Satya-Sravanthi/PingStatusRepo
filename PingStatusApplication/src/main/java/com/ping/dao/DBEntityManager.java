package com.ping.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.dao.model.ConfigurationEntity;
import com.ping.dao.model.PingEntity;
import com.ping.dao.model.PingHistoryEntity;

/**
 * Component to process the database transactions
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Service
public class DBEntityManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBEntityManager.class);

	@Autowired
	PingDatabaseRepository pingDatabaseRepository;

	@Autowired
	PingHistoryDatabaseRepository pingHistoryDatabaseRepository;

	@Autowired
	ConfigurationDatabaseRepository configurationDatabaseRepository;

	/**
	 * Method to update the ping details to the repository. The method update the
	 * details to the active table and history table
	 *
	 * @param pingEntity - PingEntity object
	 */
	public void saveOrUpdate(PingEntity pingEntity) {

		PingEntity entity = pingDatabaseRepository.findByClientId(pingEntity.getClientId());
		if (entity != null) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updating the existing record for the client Id ::" + pingEntity.getClientId());
			}
			pingDatabaseRepository.updateLogEntry(pingEntity.getPingTimeStamp(), pingEntity.getClientId());
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Adding new record for the client Id ::" + pingEntity.getClientId());
			}
			pingDatabaseRepository.save(pingEntity);
		}
		PingHistoryEntity pingHistoryEntity = new PingHistoryEntity();
		pingHistoryEntity.setClientId(pingEntity.getClientId());
		pingHistoryEntity.setPingTimeStamp(pingEntity.getPingTimeStamp());
		pingHistoryDatabaseRepository.save(pingHistoryEntity);
	}

	/**
	 * Method to fetch all the recent ping details of every client
	 * 
	 * @return - list of ping entity details
	 */
	public List<PingEntity> findAllLatestPingDetails() {
		return pingDatabaseRepository.findAll();
	}

	/**
	 * Method to fetch the configurations
	 * 
	 * @return - list of configuration details
	 */
	public List<ConfigurationEntity> getConfigurations() {
		return configurationDatabaseRepository.findAll();
	}
}

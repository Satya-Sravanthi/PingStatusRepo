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

	public void savePingDetails(PingEntity clientLogEntry) {

		PingEntity entity = pingDatabaseRepository.findByClientId(clientLogEntry.getClientId());
		if (entity != null) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updating the existing record for the client Id ::" + clientLogEntry.getClientId());
			}
			pingDatabaseRepository.updateLogEntry(clientLogEntry.getPingTimeStamp(), clientLogEntry.getClientId());
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Adding new record for the client Id ::" + clientLogEntry.getClientId());
			}
			pingDatabaseRepository.save(clientLogEntry);
		}
		PingHistoryEntity pingHistoryEntity = new PingHistoryEntity();
		pingHistoryEntity.setClientId(clientLogEntry.getClientId());
		pingHistoryEntity.setPingTimeStamp(clientLogEntry.getPingTimeStamp());
		pingHistoryDatabaseRepository.save(pingHistoryEntity);
	}

	public void updatePingDetails(PingEntity clientLogEntry) {
		pingDatabaseRepository.updateLogEntry(clientLogEntry.getPingTimeStamp(), clientLogEntry.getClientId());
	}

	public List<PingEntity> findAllLatestPingDetails() {
		return pingDatabaseRepository.findAll();
	}

	public Iterable<ConfigurationEntity> getConfigurations() {
		return configurationDatabaseRepository.findAll();
	}
}

package com.ping.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ping.api.PingEndpoint;
import com.ping.dao.DBEntityManager;
import com.ping.dao.model.ConfigurationEntity;
import com.ping.exception.PingException;

/**
 * Configuration Component to fetch the configuration details
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Component
public class ConfigurationManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);

	Map<String, Long> configurationDetails = new HashMap<String, Long>();
	private static String THRESHOLD_LIMIT = "idleTimeOutThresholdLimit";

	@Autowired
	DBEntityManager dBEntityManager;

	/**
	 * Method to get the threshold limit for the ping requests
	 * 
	 * @return - threshold limit in milli seconds
	 * @throws PingException
	 */
	public long fetchThresholdLimit() throws PingException {

		if (configurationDetails.containsKey(THRESHOLD_LIMIT)) {
			return configurationDetails.get(THRESHOLD_LIMIT);
		} else {
			loadConfigurations();
			if (configurationDetails.get(THRESHOLD_LIMIT) != null)

			{
				return configurationDetails.get(THRESHOLD_LIMIT);
			}
		}
		return 0;
	}

	/**
	 * Load the configurations from the database to the in-memory object for faster
	 * accessing of the configurations
	 */
	private void loadConfigurations() {
		
		LOGGER.info("loading the configurations");
		Iterable<ConfigurationEntity> configurations = dBEntityManager.getConfigurations();
		if (configurations != null) {
			for (ConfigurationEntity configurationEntity : configurations) {
				configurationDetails.put(configurationEntity.getConfigurationName(),
						configurationEntity.getConfigurationValue());
			}
		}

	}
}

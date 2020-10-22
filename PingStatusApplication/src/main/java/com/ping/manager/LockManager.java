package com.ping.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Component which acquires and releases lock to handle concurrency issues
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Component
public class LockManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(LockManager.class);

	/**
	 * Method to acquire the lock
	 * 
	 * @return - Returns true if the lock is acquired or else false
	 */
	public boolean lock() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("acquiring lock");
		}

		return true;
	}

	/**
	 * Method to unlock
	 */
	public void unlock() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("releasing lock");
		}
	}

}

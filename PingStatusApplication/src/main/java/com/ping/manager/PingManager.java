package com.ping.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ping.PingConstants;
import com.ping.api.PingRequest;
import com.ping.dao.DBEntityManager;
import com.ping.dao.model.PingEntity;
import com.ping.exception.PingException;

/**
 * Manager component to process the ping requests and store it in the datbase
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Component
public class PingManager {

	@Autowired
	DBEntityManager dBEntityManager;

	/**
	 * 
	 * @param pingRequest
	 * @return
	 * @throws PingException
	 */
	public int processPingRequest(PingRequest pingRequest) throws PingException {
		PingEntity clientLogEntry = new PingEntity();
		clientLogEntry.setClientId(pingRequest.getClientId());
		long timestamp = System.currentTimeMillis();
		clientLogEntry.setPingTimeStamp(timestamp);
		dBEntityManager.saveOrUpdate(clientLogEntry);
		return PingConstants.SUCCESS;

	}

}

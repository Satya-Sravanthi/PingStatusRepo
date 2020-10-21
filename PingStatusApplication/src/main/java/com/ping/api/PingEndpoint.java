package com.ping.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ping.PingConstants;
import com.ping.exception.PingException;
import com.ping.manager.PingManager;

/**
 * Rest endpoint class to accept the ping requests
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@RestController
public class PingEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingEndpoint.class);

	@Autowired
	PingManager pingManager;

	/**
	 * ping rest endpoint api call
	 * 
	 * @param pingRequest - PingRequest object from the client
	 * @return pingResponse - Returns status of the ping request in the PingResponse
	 *         object
	 */
	@PostMapping(path = "/ping", consumes = "application/json", produces = "application/json")
	public PingResponse ping(@RequestBody PingRequest pingRequest) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Received ping request for the client Id :" + pingRequest.getClientId());
		}

		PingResponse response = new PingResponse();
		try {
			int status = pingManager.processPingRequest(pingRequest);
			response.setStatus(status);
		} catch (PingException e) {
			response.setStatus(PingConstants.FAILURE);
		}
		return response;
	}

}

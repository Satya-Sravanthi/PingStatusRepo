package com.ping.api;

/**
 * Ping Request class for the rest endpoint
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
public class PingRequest {

	/**
	 * Client Id through which the request is sent
	 */
	private String clientId;

	public PingRequest() {

	}

	public PingRequest(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * get the client id
	 * 
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Set the client id
	 * 
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "PingRequest [clientId=" + clientId + "]";
	}

}

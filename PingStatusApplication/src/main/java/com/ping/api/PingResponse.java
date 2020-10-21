package com.ping.api;

/**
 * Ping response class to the rest endpoint
 * 
 * @author sdhaneku
 *
 */
public class PingResponse {

	/**
	 * status of the ping request
	 */
	private int status;

	/**
	 * get the status
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * set the status
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PingResponse [status=" + status + "]";
	}

}

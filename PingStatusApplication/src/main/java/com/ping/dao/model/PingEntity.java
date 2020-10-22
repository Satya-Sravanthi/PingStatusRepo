package com.ping.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Entity
@Table(name = "PING")
public class PingEntity {

	@Id
	@Column(name = "CLIENT_ID")
	private String clientId;

	@Column(name = "PING_TIMESTAMP")
	private long pingTimeStamp;

	public void setPingTimeStamp(long pingTimeStamp) {
		this.pingTimeStamp = pingTimeStamp;
	}

	public long getPingTimeStamp() {
		return pingTimeStamp;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "PingEntity [clientId=" + clientId + ", pingTimeStamp=" + pingTimeStamp + "]";
	}

}

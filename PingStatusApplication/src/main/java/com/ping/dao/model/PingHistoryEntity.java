package com.ping.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Entity
@Table(name = "PING_HISTORY")
public class PingHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "PingHistoryEntity [id=" + id + ", clientId=" + clientId + ", pingTimeStamp=" + pingTimeStamp + "]";
	}

}

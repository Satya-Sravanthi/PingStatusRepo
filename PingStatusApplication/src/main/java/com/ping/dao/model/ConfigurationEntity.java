package com.ping.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIGURATION")
public class ConfigurationEntity {

	@Id
	private String id;
	
	private String configurationName;
	
	private long configurationValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public long getConfigurationValue() {
		return configurationValue;
	}

	public void setConfigurationValue(long configurationValue) {
		this.configurationValue = configurationValue;
	}

	@Override
	public String toString() {
		return "ConfigurationEntity [id=" + id + ", configurationName=" + configurationName + ", configurationValue="
				+ configurationValue + "]";
	}

}

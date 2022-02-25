package com.applause.findtesterapp.dataobject;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICES")
@AttributeOverride(name = "id", column = @Column(name = "DEVICE_ID"))
public class DeviceDO {
	
	@Id
	private Integer deviceId;
	@Column(name="DESCRIPTION")
	private String description;

	public DeviceDO() {
		
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, deviceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceDO other = (DeviceDO) obj;
		return Objects.equals(description, other.description) && Objects.equals(deviceId, other.deviceId);
	}

	@Override
	public String toString() {
		return "DeviceDO [deviceId=" + deviceId + ", description=" + description + "]";
	}
	
	
}

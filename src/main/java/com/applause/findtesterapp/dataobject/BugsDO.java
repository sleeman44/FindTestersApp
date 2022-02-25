package com.applause.findtesterapp.dataobject;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUGS")
@AttributeOverride(name = "id", column = @Column(name = "BUG_ID"))
public class BugsDO {

	@Id
	private Integer bugId;
	@Column(name="TESTER_ID")
	private Integer testerId;
	@Column(name="DEVICE_ID")
	private Integer deviceId;
	
	public BugsDO() {
		
	}

	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}

	public Integer getTesterId() {
		return testerId;
	}

	public void setTesterId(Integer testerId) {
		this.testerId = testerId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bugId, deviceId, testerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BugsDO other = (BugsDO) obj;
		return Objects.equals(bugId, other.bugId) && Objects.equals(deviceId, other.deviceId)
				&& Objects.equals(testerId, other.testerId);
	}

	@Override
	public String toString() {
		return "BugsDO [bugId=" + bugId + ", testerId=" + testerId + ", deviceId=" + deviceId + "]";
	}
	
	
}

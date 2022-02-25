package com.applause.findtesterapp.dataobject;

import java.util.Objects;

public class TesterResultsDO implements Comparable<TesterResultsDO> {
	
	private Integer totalBugs;
	private String user;
	private String device;
	
	public TesterResultsDO() {
		
	}
	
	public TesterResultsDO(Integer totalBugs, String user, String device) {
		
		this.totalBugs = totalBugs;
		this.user = user;
		this.device = device;
	}

	public Integer getTotalBugs() {
		return totalBugs;
	}

	public void setTotalBugs(Integer totalBugs) {
		this.totalBugs = totalBugs;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public int hashCode() {
		return Objects.hash(device, totalBugs, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TesterResultsDO other = (TesterResultsDO) obj;
		return Objects.equals(device, other.device) && totalBugs == other.totalBugs && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "TesterResultsDO [totalBugs=" + totalBugs + ", user=" + user + ", device=" + device + "]";
	}
	
	@Override
    public int compareTo(TesterResultsDO other) {
        return Integer.compare(getTotalBugs(), other.getTotalBugs());
    }

}

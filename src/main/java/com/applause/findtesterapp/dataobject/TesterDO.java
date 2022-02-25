package com.applause.findtesterapp.dataobject;

import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TESTERS")
@AttributeOverride(name = "id", column = @Column(name = "TESTER_ID"))
public class TesterDO {
	
	@Id
	private Integer testerId;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="country")
	private String country;
	@Column(name="LAST_LOGIN")
	private Date lastLogin;
	
	public TesterDO() {
		
	}

	public Integer getTesterId() {
		return testerId;
	}

	public void setTesterId(Integer testerId) {
		this.testerId = testerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, firstName, lastLogin, lastName, testerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TesterDO other = (TesterDO) obj;
		return Objects.equals(country, other.country) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastLogin, other.lastLogin) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(testerId, other.testerId);
	}

	@Override
	public String toString() {
		return "TesterDO [testerId=" + testerId + ", firstName=" + firstName + ", lastName=" + lastName + ", country="
				+ country + ", lastLogin=" + lastLogin + "]";
	}
	
	
}

package com.applause.findtesterapp.dataobject;

import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_CONTROL")
@AttributeOverride(name = "id", column = @Column(name = "APP_CTRL_ID"))
public class AppControlDO {
	
	@Id
	private Integer appCtrlId;
	private String ctrlCode;
	private String stringValue;
	private Date dateValue;
	private Integer numberValue;
	
	public AppControlDO() {
		
	}

	public Integer getAppCtrlId() {
		return appCtrlId;
	}

	public void setAppCtrlId(Integer appCtrlId) {
		this.appCtrlId = appCtrlId;
	}

	public String getCtrlCode() {
		return ctrlCode;
	}

	public void setCtrlCode(String ctrlCode) {
		this.ctrlCode = ctrlCode;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Integer getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(Integer numberValue) {
		this.numberValue = numberValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appCtrlId, ctrlCode, dateValue, numberValue, stringValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppControlDO other = (AppControlDO) obj;
		return Objects.equals(appCtrlId, other.appCtrlId) && Objects.equals(ctrlCode, other.ctrlCode)
				&& Objects.equals(dateValue, other.dateValue) && Objects.equals(numberValue, other.numberValue)
				&& Objects.equals(stringValue, other.stringValue);
	}

	@Override
	public String toString() {
		return "AppControlDO [appCtrlId=" + appCtrlId + ", ctrlCode=" + ctrlCode + ", stringValue=" + stringValue
				+ ", dateValue=" + dateValue + ", numberValue=" + numberValue + "]";
	}
	
	

}

package com.todo.list.entity.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "EventLogRecord")
public class EventLogRecordEntity {

	@Id
	private Long id;

	@Column(name = "LOCATION_OF_OCCURRENCE")
	private String locationOfOccurrence;

	@Column(name = "METHOD_OF_OCCURRENCE")
	private String methodOfOccurrence;

	@Column(name = "DATE_OF_OCCURRENCE")
	private String dateOfOccurrence;

	@Column(name = "LOGMESSAGE")
	private String logMessage;

	public EventLogRecordEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationOfOccurrence() {
		return locationOfOccurrence;
	}

	public void setLocationOfOccurrence(String locationOfOccurrence) {
		this.locationOfOccurrence = locationOfOccurrence;
	}

	public String getMethodOfOccurrence() {
		return methodOfOccurrence;
	}

	public void setMethodOfOccurrence(String methodOfOccurrence) {
		this.methodOfOccurrence = methodOfOccurrence;
	}

	public String getDateOfOccurrence() {
		return dateOfOccurrence;
	}

	public void setDateOfOccurrence(String dateOfOccurrence) {
		this.dateOfOccurrence = dateOfOccurrence;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

}

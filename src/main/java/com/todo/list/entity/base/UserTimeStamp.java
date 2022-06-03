package com.todo.list.entity.base;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class UserTimeStamp {

	// @Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	//@Column(name = "CREATETIMESTAMP")
	private Timestamp creaTimestamp;

	@UpdateTimestamp
	//@Column(name = "UPDATETIMESTAMP")
	private Timestamp updateTimestamp;

}

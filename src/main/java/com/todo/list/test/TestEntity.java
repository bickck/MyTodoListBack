package com.todo.list.test;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "testArg1")
	private String testArg1;

	@Column(name = "testArg2")
	private String testArg2;

	@Column(name = "testArg3")
	private String testArg3;

	@Column(name = "testArg4")
	private String testArg4;

	@Column(name = "testArg5")
	private Long testArg5;

	@Column(name = "testArg6")
	private Long testArg6;

	@Column(name = "testArg7")
	private Time testArg7;

	public TestEntity() {
		// TODO Auto-generated constructor stub
	}

	public TestEntity(String testArg1, String testArg2, String testArg3, String testArg4, Long testArg5,
			Long testArg6) {

		this.testArg1 = testArg1;
		this.testArg2 = testArg2;
		this.testArg3 = testArg3;
		this.testArg4 = testArg4;
		this.testArg5 = testArg5;
		this.testArg6 = testArg6;

	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", testArg1=" + testArg1 + ", testArg2=" + testArg2 + ", testArg3=" + testArg3
				+ ", testArg4=" + testArg4 + ", testArg5=" + testArg5 + ", testArg6=" + testArg6 + ", testArg7="
				+ testArg7 + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestArg1() {
		return testArg1;
	}

	public void setTestArg1(String testArg1) {
		this.testArg1 = testArg1;
	}

	public String getTestArg2() {
		return testArg2;
	}

	public void setTestArg2(String testArg2) {
		this.testArg2 = testArg2;
	}

	public String getTestArg3() {
		return testArg3;
	}

	public void setTestArg3(String testArg3) {
		this.testArg3 = testArg3;
	}

	public String getTestArg4() {
		return testArg4;
	}

	public void setTestArg4(String testArg4) {
		this.testArg4 = testArg4;
	}

	public Long getTestArg5() {
		return testArg5;
	}

	public void setTestArg5(Long testArg5) {
		this.testArg5 = testArg5;
	}

	public Long getTestArg6() {
		return testArg6;
	}

	public void setTestArg6(Long testArg6) {
		this.testArg6 = testArg6;
	}

	public Time getTestArg7() {
		return testArg7;
	}

	public void setTestArg7(Time testArg7) {
		this.testArg7 = testArg7;
	}

}

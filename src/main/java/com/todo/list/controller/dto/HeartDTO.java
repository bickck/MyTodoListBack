package com.todo.list.controller.dto;

public class HeartDTO {

	private String id;

	private String uuid;

	private boolean exists;

	public HeartDTO() {
		// TODO Auto-generated constructor stub
	}

	public HeartDTO(String uuid, boolean exists) {
		super();
		this.uuid = uuid;
		this.exists = exists;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

}

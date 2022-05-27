package com.todo.list.controller.dto;

import com.todo.list.entity.base.AdminImageEntity;

public class ImageInfoDTO {

	private Long id;
	private String originalFileName;

	public ImageInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ImageInfoDTO(Long id, String originalFileName) {
		super();
		this.id = id;
		this.originalFileName = originalFileName;
	}

	public ImageInfoDTO(AdminImageEntity adminImageEntity) {
		super();
		this.id = adminImageEntity.getId();
		this.originalFileName = adminImageEntity.getOriginalFileName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

}

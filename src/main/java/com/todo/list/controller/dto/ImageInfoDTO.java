package com.todo.list.controller.dto;

public class ImageInfoDTO {

	private String originalFileName;
	private String filePath;

	public ImageInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ImageInfoDTO(String originalFileName, String filePath) {
		super();
		this.originalFileName = originalFileName;
		this.filePath = filePath;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

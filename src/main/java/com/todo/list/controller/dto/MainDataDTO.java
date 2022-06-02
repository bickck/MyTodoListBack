package com.todo.list.controller.dto;

import java.util.List;

public class MainDataDTO {

	private List<ImageInfoDTO> imageInfoDTO;

	private int quoteTotalSize;

	public MainDataDTO() {
		// TODO Auto-generated constructor stub
	}

	public MainDataDTO(List<ImageInfoDTO> imageInfoDTO, int quoteTotalSize) {
		super();
		this.imageInfoDTO = imageInfoDTO;
		this.quoteTotalSize = quoteTotalSize;
	}

	public List<ImageInfoDTO> getImageInfoDTO() {
		return imageInfoDTO;
	}

	public void setImageInfoDTO(List<ImageInfoDTO> imageInfoDTO) {
		this.imageInfoDTO = imageInfoDTO;
	}

	public int getQuoteTotalSize() {
		return quoteTotalSize;
	}

	public void setQuoteTotalSize(int quoteTotalSize) {
		this.quoteTotalSize = quoteTotalSize;
	}

}

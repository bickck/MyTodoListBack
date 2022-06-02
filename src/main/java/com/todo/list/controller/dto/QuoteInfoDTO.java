package com.todo.list.controller.dto;

public class QuoteInfoDTO {

	private Long quoteTotalSize;

	private int quoteTotalPages;

	public QuoteInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public QuoteInfoDTO(Long quoteTotalSize, int quoteTotalPages) {
		super();
		this.quoteTotalSize = quoteTotalSize;
		this.quoteTotalPages = quoteTotalPages;
	}

	public Long getQuoteTotalSize() {
		return quoteTotalSize;
	}

	public void setQuoteTotalSize(Long quoteTotalSize) {
		this.quoteTotalSize = quoteTotalSize;
	}

	public int getQuoteTotalPages() {
		return quoteTotalPages;
	}

	public void setQuoteTotalPages(int quoteTotalPages) {
		this.quoteTotalPages = quoteTotalPages;
	}

}

package com.todo.list.controller.dto.page;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.user.UserDTO;

public class PageUserDTO {

	private List<UserDTO> userDTO;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	public PageUserDTO(List<UserDTO> userDTO, int number, int totalPages, long totalElement, long size,
			int numberOfElements, Pageable pageable) {
		super();
		this.userDTO = userDTO;
		this.number = number;
		this.totalPages = totalPages;
		this.totalElement = totalElement;
		this.size = size;
		this.numberOfElements = numberOfElements;
		this.pageable = pageable;
	}

	public List<UserDTO> getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(List<UserDTO> userDTO) {
		this.userDTO = userDTO;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

}

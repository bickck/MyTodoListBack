package com.todo.list.domain.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name="IMAGE")
public class ImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "ORIGINAL_NAME")
	private String original_file_name;

	@NotNull
	@Column(name = "FILE_PATH")
	private String stored_file_path;

	@NotNull
	private Long file_size;

	public ImageEntity(@NotNull String original_file_name, @NotNull String stored_file_path, @NotNull Long file_size) {
		super();
		this.original_file_name = original_file_name;
		this.stored_file_path = stored_file_path;
		this.file_size = file_size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}

	public String getStored_file_path() {
		return stored_file_path;
	}

	public void setStored_file_path(String stored_file_path) {
		this.stored_file_path = stored_file_path;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

}

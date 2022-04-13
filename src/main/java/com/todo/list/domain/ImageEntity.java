package com.todo.list.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
public class ImageEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}

package com.todo.list.entity.base;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public enum PlatForm {

	KAKAO("KAKAO");

	private final String code;

	PlatForm(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

	@JsonCreator(mode = Mode.DELEGATING)
	public static PlatForm findByCode(String code) {

		return Stream.of(PlatForm.values()).filter(t -> t.code.equals(code)).findFirst().orElse(null);
	}
}

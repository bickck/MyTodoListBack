package com.todo.list.entity.base;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public enum Publish {

	PUBLISH("PUBLISH"), PRIVATE("PRIVATE");

	private final String code;

	Publish(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

	@JsonCreator(mode = Mode.DELEGATING)
	public static Publish findByCode(String code) {

		return Stream.of(Publish.values()).filter(t -> t.code.equals(code)).findFirst().orElse(null);
	}
}

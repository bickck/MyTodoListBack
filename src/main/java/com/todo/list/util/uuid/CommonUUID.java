package com.todo.list.util.uuid;

import java.util.UUID;

public class CommonUUID{
	
	
	private String generator() {
		return UUID.randomUUID().toString();
	}

	public String generatorCommentUUID() {
		return generator().replace("-", "");

	}

	public String generatorImageUUID() {
		return generator().replace("-", "");
	}

}

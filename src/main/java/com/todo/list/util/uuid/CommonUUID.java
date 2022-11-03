package com.todo.list.util.uuid;

import java.util.UUID;

public class CommonUUID {

	public String generatorCommentUUID() {
		return UUID.randomUUID().toString().replace("-", "");

	}

	public String generatorImageUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}

package com.todo.list.util.uuid;

import java.util.UUID;


public class CommentUUID{

	public String generatorCommentUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	//private 

}

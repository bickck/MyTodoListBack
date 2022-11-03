package com.todo.list.file;

public interface FileSupport {

	String generatorFilePath(String uuid);

	boolean existsFilePath(String path);

}

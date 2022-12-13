package com.todo.list.file;

public interface FileSupport {

	String generatorFilePath(String uuid, String path);

	boolean existsFilePath(String path);

}

package com.todo.list.util.auth;

public interface BcryptHelper {

	String bCrypt(String password);
	
	boolean isMatch(String rowPassword, String encPassword);
}

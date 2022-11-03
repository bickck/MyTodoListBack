package com.todo.list.util;

import java.io.File;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class CreateUUIDTest {

	@Test
	public void uuidTest() {
		String originalUUID = UUID.randomUUID().toString().replace("-", "");

		String fileUUID = "";

		for (int i = 0; i < originalUUID.length(); i += 2) {
			fileUUID += File.separator + originalUUID.subSequence(i, i + 2);

		}

		System.out.println(originalUUID);
		System.out.println(originalUUID.length());
		System.out.println(originalUUID.subSequence(0, 2));
		System.out.println(fileUUID);

	}
}

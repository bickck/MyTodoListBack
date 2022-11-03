package com.todo.list.file;

import java.io.File;

import com.todo.list.util.Utils;

public class FileSupportImpl implements FileSupport {

	private static final String DEFAULT_PATH = "";

	private Utils utils = new Utils();

	@Override
	public String generatorFilePath(String uuid) {
		// TODO Auto-generated method stub
		try {
			if (uuid == null) {
				throw new NullPointerException();
			}

			if (uuid.isEmpty()) {
				throw new Exception("FileSupportImpl => Parameter is empty");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DEFAULT_PATH + File.separator + utils.converToFilePath(uuid);
	}

	@Override
	public boolean existsFilePath(String path) {
		// TODO Auto-generated method stub
		return false;
	}
}

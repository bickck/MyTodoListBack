package com.todo.list.util;

import java.io.File;

public class Utils {
	
	
	/**
	 * 
	 * @param str
	 * @return
	 */

	public String nvl(String str) {

		if (str == null) {
			str = "";
		}

		return str;
	}
	
	/**
	 * UUID를 폴더 경로로 변경
	 * 
	 * ex) e96a8cc1622245dca971171910fc062b -> \e9\6a\8c\c1\62\22\45\dc\a9\71\17\19\10\fc\06\2b
	 * @param uuid
	 * @return
	 */

	public String converToFilePath(String uuid) {
		String convertPath = "";

		for (int i = 0; i < uuid.length(); i += 2) {
			convertPath += File.separator + uuid.subSequence(i, i + 2);
		}
		return convertPath;
	}
}

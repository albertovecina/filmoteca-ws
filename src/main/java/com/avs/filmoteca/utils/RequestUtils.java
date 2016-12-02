package com.avs.filmoteca.utils;

import javax.servlet.http.HttpServletRequest;

import com.avs.filmoteca.data.db.DataBase.Environment;

public class RequestUtils {

	public static Environment getEnvironment(HttpServletRequest request) {
		String pathInfo = request.getServletPath(); // /{value}/test
		if (pathInfo == null)
			return Environment.PRO;
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length > 0)
			return Environment.findByPath(pathParts[1]);
		return Environment.PRO;
	}

}

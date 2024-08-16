package edu.ifsp.web;

import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {
	public static String extractOperation(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int pos = uri.indexOf(contextPath) + contextPath.length();
		String op = uri.substring(pos);		
		return op;
	}

}

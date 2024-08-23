package edu.ifsp.web;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Flash {
	
	@SuppressWarnings("unchecked")
	public static void setAttribute(HttpServletRequest request, String scope, String key, Object value) {
		scope = "flash:" + scope;
		HttpSession session = request.getSession();
		Map<String, Object> map = (Map<String, Object>)session.getAttribute(scope);
		if (map == null) {
			map = new HashMap<String, Object>();
			session.setAttribute(scope, map);
		}
		
		map.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static void move(HttpServletRequest request, String scope) {
		scope = "flash:" + scope;
		HttpSession session = request.getSession();
		Map<String, Object> map = (Map<String, Object>)session.getAttribute(scope);

		if (map != null) {
			map.forEach((key, value) -> {
				request.setAttribute(key, value);
			});
		}
		session.removeAttribute(scope);		
	}
}


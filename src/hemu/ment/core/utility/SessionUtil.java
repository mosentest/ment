package hemu.ment.core.utility;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	
	public static void setObject(String key, Object value) {
		setObject(ControllerUtil.getRequest(), key, value);
	}
	
	public static void setObject(ServletRequest request, String key, Object value) {
		if (request instanceof HttpServletRequest) {
			setObject((HttpServletRequest) request, key, value);
		}
	}
	
	private static void setObject(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	public static Object getObject(String key) {
		return getObject(ControllerUtil.getRequest(), key);
	}
	
	public static Object getObject(ServletRequest request, String key) {
		if (request instanceof HttpServletRequest) {
			return getObject((HttpServletRequest) request, key);
		}
		return null;
	}
	
	public static Object getObject(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
}

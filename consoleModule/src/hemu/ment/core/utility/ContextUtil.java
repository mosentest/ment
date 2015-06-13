package hemu.ment.core.utility;

import hemu.ment.core.constant.C;
import hemu.ment.core.controller.LoginBean;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ContextUtil {

	public static String getClientAddress() {
		return getClientAddress(getRequest());
	}

	public static String getClientAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void clearSession() {
		getSession().invalidate();
		getSession(true);
	}

	public static String getAuthToken() {
		return (String) getSession().getAttribute(C.AUTH_TOKEN);
	}

	public static Object getRequestObject(String key) {
		return getContext().getRequestParameterMap().get(key);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpSession getSession() {
		return getSession(false);
	}

	public static HttpSession getSession(boolean renew) {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(renew);
	}

	public static Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}

	public static ExternalContext getContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	private static void setObject(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	public static Object getObject(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

}

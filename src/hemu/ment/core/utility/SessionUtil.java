package hemu.ment.core.utility;

import hemu.ment.core.controller.UserBean;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
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

	public static UserBean getUserBean(HttpServletRequest request) {
		return (UserBean) request.getSession().getAttribute("user");
	}

    public static User getUser(ServletRequest request) {
        return getUser((HttpServletRequest) request);
    }

    public static User getUser(HttpServletRequest request) {
        return getUserBean(request).getUser();
    }

    public static Enterprise getEnterprise(HttpServletRequest request) {
        return ((UserBean) request.getSession().getAttribute("user")).getEnterprise();
    }

	
}

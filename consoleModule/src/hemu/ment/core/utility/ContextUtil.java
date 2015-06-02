package hemu.ment.core.utility;

import hemu.ment.core.controller.LoginBean;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ContextUtil {

	public static ExternalContext getContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
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

	public static LoginBean getUserBean(HttpServletRequest request) {
		return (LoginBean) request.getSession().getAttribute("current");
	}

    public static User getUser(ServletRequest request) {
        return getUser((HttpServletRequest) request);
    }

    public static User getUser(HttpServletRequest request) {
        return getUserBean(request).getUser();
    }

    public static Enterprise getEnterprise(HttpServletRequest request) {
        return ((LoginBean) request.getSession().getAttribute("current")).getEnterprise();
    }

	
}

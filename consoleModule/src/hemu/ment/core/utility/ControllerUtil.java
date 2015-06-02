package hemu.ment.core.utility;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {
	
	public static FacesMessage sendMessage(String resourceBundle, Severity severity, String titleCode, String detailedCode, Object...params) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle, locale);
		MessageFormat titleFormat = new MessageFormat(bundle.getString(titleCode), locale);
		MessageFormat detailFormat = new MessageFormat(bundle.getString(detailedCode), locale);
		FacesMessage facesMessage = new FacesMessage(severity, titleFormat.format(params), detailFormat.format(params));
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return facesMessage;
	}
	
	public static Object getParam(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
}

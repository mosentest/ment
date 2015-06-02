package hemu.ment.core.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by muu on 2015/5/29.
 */
public class FacesMessageUtil {

    public static boolean containsValidationError() {
        return FacesContext.getCurrentInstance().getMessageList().size() > 0;
    }

    public static void validationError(String form, String component, String message) {
        FacesContext.getCurrentInstance().addMessage(form + ":" + component,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public static void addMessage(FacesMessage.Severity severity, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, details));
    }

    public static void addInfoMessage(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message, null);
    }

    public static void addInfoMessage(String message, String details) {
        addMessage(FacesMessage.SEVERITY_INFO, message, details);
    }

    public static void addErrorMessage(String message, String details) {
        addMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }

    public static void addErrorMessage(String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, message, null);
    }

}

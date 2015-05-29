package hemu.ment.core.utility;

import hemu.ment.core.entity.Identifiable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * Created by muu on 2015/5/28.
 */
public class ApplicationUtil {

    public static void setAttribute(String key, Object object) {
        getServletContext().setAttribute(key, object);
    }

    public static Long getIdentifier(String key) {
        return ((Identifiable) getAttribute(key)).getId();
    }

    public static Object getAttribute(String key) {
        return getServletContext().getAttribute(key);
    }

    public static <T> T getAttribute(Class<T> classType, String key) {
        return (T) getServletContext().getAttribute(key);
    }

    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }


}

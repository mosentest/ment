package hemu.ment.core.utility;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * Created by muu on 2015/5/28.
 */
public class ApplicationUtil {

    public static <T> T getObject(Class<T> classType, String key) {
        return (T) getServletContext().getAttribute(key);
    }

    public static Object getObject(String key) {
        return getServletContext().getAttribute(key);
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }


}

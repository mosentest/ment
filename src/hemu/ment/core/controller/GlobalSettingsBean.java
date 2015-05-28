package hemu.ment.core.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by muu on 2015/5/28.
 */
@ManagedBean(name = "globalSettings")
@ViewScoped
public class GlobalSettingsBean {

    //managed bean containing method auyhority
    //interceptor intercept all permission

    public String updateI18N() {
        return null;
    }

    public String updateGlobal() {
        return null;
    }

    public String updateEmail() {
        return null;
    }

}

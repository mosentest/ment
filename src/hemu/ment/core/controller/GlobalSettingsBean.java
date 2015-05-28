package hemu.ment.core.controller;

import hemu.ment.core.constant.ApplicationVariable;
import hemu.ment.core.constant.SupportedConstant;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;
import hemu.ment.core.utility.ApplicationUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by muu on 2015/5/28.
 */
@ManagedBean(name = "globalSettings")
@ViewScoped
public class GlobalSettingsBean {

    @ManagedProperty(value="#{user.enterprise}")
    private Enterprise enterprise;

    private InternationalizationSettings i18nSettings;

    private EmailSettings emailSettings;

    private GlobalSettings globalSettings;

    private Map<String, Object> value = new HashMap<>();

    public void fetchI18N() {
        i18nSettings = ApplicationUtil.getObject(InternationalizationSettings.class, ApplicationVariable.I18N + enterprise.getCode());
        value.put("timeZoneList", SupportedConstant.SUPPORTED_TIMEZONE);
        value.put("localeList", SupportedConstant.SUPPORTED_LOCALE);
    }

    public void fetchGlobal() {
        globalSettings =  ApplicationUtil.getObject(GlobalSettings.class, ApplicationVariable.GLOBAL + enterprise.getCode());
    }

    public void fetchEmail() {
        emailSettings = ApplicationUtil.getObject(EmailSettings.class, ApplicationVariable.EMAIL + enterprise.getCode());
    }

    public String updateI18N() {
        InternationalizationSettings settings =
                ApplicationUtil.getObject(InternationalizationSettings.class, ApplicationVariable.I18N + enterprise.getCode());
        return null;
    }

    public String updateGlobal() {
        GlobalSettings settings =
                ApplicationUtil.getObject(GlobalSettings.class, ApplicationVariable.GLOBAL + enterprise.getCode());
        return null;
    }

    public String updateEmail() {
        EmailSettings settings =
                ApplicationUtil.getObject(EmailSettings.class, ApplicationVariable.EMAIL + enterprise.getCode());
        return null;
    }

    public GlobalSettings getGlobalSettings() {
        return globalSettings;
    }

    public void setGlobalSettings(GlobalSettings globalSettings) {
        this.globalSettings = globalSettings;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public InternationalizationSettings getI18nSettings() {
        return i18nSettings;
    }

    public void setI18nSettings(InternationalizationSettings i18nSettings) {
        this.i18nSettings = i18nSettings;
    }

    public EmailSettings getEmailSettings() {
        return emailSettings;
    }

    public void setEmailSettings(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}

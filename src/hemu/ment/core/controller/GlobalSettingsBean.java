package hemu.ment.core.controller;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.constant.ApplicationVariable;
import hemu.ment.core.constant.SupportedConstant;
import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;
import hemu.ment.core.utility.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;

/**
 * Created by muu on 2015/5/28.
 */
@ManagedBean(name = "globalSettingsBean")
@RequestScoped
public class GlobalSettingsBean {

    @ManagedProperty(value = "#{current.enterprise}")
    private Enterprise enterprise;

    @EJB
    private EnterpriseSettingsLocal enterpriseSettingsEJB;

    @Inject
    private CacheConsole cacheConsole;

    private InternationalizationSettings i18nSettings;

    private EmailSettings emailSettings;

    private GlobalSettings globalSettings;

    @PostConstruct
    public void postConstruct() {
        if (i18nSettings == null) i18nSettings = new InternationalizationSettings();
        if (emailSettings == null) emailSettings = new EmailSettings();
        if (globalSettings == null) globalSettings = new GlobalSettings();
    }

    public void fetchI18N() {
        i18nSettings = cacheConsole.getAppCache(InternationalizationSettings.class, ApplicationVariable.I18N + enterprise.getCode());
    }

    public void fetchGlobal() {
        globalSettings =  cacheConsole.getAppCache(GlobalSettings.class, ApplicationVariable.GLOBAL + enterprise.getCode());
    }

    public void fetchEmail() {
        emailSettings = cacheConsole.getAppCache(EmailSettings.class, ApplicationVariable.EMAIL + enterprise.getCode());
    }

    private void validateI18N() {
        if (i18nSettings == null) {
            FacesMessageUtil.addErrorMessage("Error occurred");
        }
        if (!SupportedConstant.isSupportedLocale(i18nSettings.getDefaultLocaleString())) {
            FacesMessageUtil.validationError("form", "default_locale_string", "Locale is not supported");
        }
        if (!SupportedConstant.isSupportedTimeZone(i18nSettings.getDefaultTimeZoneID())) {
            FacesMessageUtil.validationError("form", "default_timezone_id", "Time zone is not supported");
        }
        try {
            new SimpleDateFormat(i18nSettings.getDateFormatString());
        } catch (NullPointerException | IllegalArgumentException e) {
            FacesMessageUtil.validationError("form", "date_format_string", "Date format is invalid");
        }
        try {
            new SimpleDateFormat(i18nSettings.getDateTimeFormatString());
        } catch (NullPointerException | IllegalArgumentException e) {
            FacesMessageUtil.validationError("form", "date_time_format_string", "Time format is invalid");
        }
        try {
            new SimpleDateFormat(i18nSettings.getTimeFormatString());
        } catch (NullPointerException | IllegalArgumentException e) {
            FacesMessageUtil.validationError("form", "time_format_string", "Time format is invalid");
        }
    }

    private void validateGlobal() {
        if (globalSettings == null) {
            FacesMessageUtil.addErrorMessage("Error occurred");
        }
        if (!globalSettings.getTitle().matches("[a-zA-Z0-9 ]{5,20}")) {
            FacesMessageUtil.validationError("form", "title", "Title invalid");
        }
        if (globalSettings.getMaxAuthenticationAllowed() < 1 || globalSettings.getMaxAuthenticationAllowed() > 5) {
            FacesMessageUtil.validationError("form", "max_authentication_allowed", "Max authentication allowed range 1-5");
        }
    }

    private void validateEmail() {
        if (emailSettings == null) {
            FacesMessageUtil.addErrorMessage("Error occurred");
        }
        if (emailSettings.getSmtpPort() < 1 || emailSettings.getSmtpPort() > 65535) {
            FacesMessageUtil.validationError("form", "smtp_port", "Port must be in range from 1 to 65535");
        }
    }

    public String updateI18N() {
        validateI18N();
        if (FacesMessageUtil.containsValidationError()) {
            return "/c/settings/internationalizationform.xhtml";
        }
        Long id = cacheConsole.getIdentifier(ApplicationVariable.I18N + enterprise.getCode());
        InternationalizationSettings settings = enterpriseSettingsEJB.updateInternationalizationSettings(id, i18nSettings);
        cacheConsole.appCache(ApplicationVariable.I18N + enterprise.getCode(), settings);
        FacesMessageUtil.addInfoMessage("Internationalization Settings has been updated");
        return "/c/settings/internationalization.xhtml";
    }

    public String updateGlobal() {
        validateGlobal();
        if (FacesMessageUtil.containsValidationError()) {
            return "/c/settings/globalform.xhtml";
        }
        Long id = cacheConsole.getIdentifier(ApplicationVariable.GLOBAL + enterprise.getCode());
        GlobalSettings settings = enterpriseSettingsEJB.updateGlobalSettings(id, globalSettings);
        cacheConsole.appCache(ApplicationVariable.GLOBAL + enterprise.getCode(), settings);
        FacesMessageUtil.addInfoMessage("Global Settings has been updated");
        return "/c/settings/global.xhtml";
    }

    public String updateEmail() {
        validateEmail();
        if (FacesMessageUtil.containsValidationError()) {
            return "/c/settings/emailform.xhtml";
        }
        Long id = cacheConsole.getIdentifier(ApplicationVariable.EMAIL + enterprise.getCode());
        EmailSettings settings = enterpriseSettingsEJB.updateEmailSettings(id, emailSettings);
        cacheConsole.appCache(ApplicationVariable.EMAIL + enterprise.getCode(), settings);
        FacesMessageUtil.addInfoMessage("Email Settings has been updated");
        return "/c/settings/email.xhtml";
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
}

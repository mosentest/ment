package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Enterprise;

import java.io.Serializable;
import java.util.Properties;

import javax.persistence.*;

@Entity
@Table(name = "t_settings_global", schema = "ment_core")
public class GlobalSettings implements SettingsEntity, Serializable {
	
	private static final long serialVersionUID = 8189118879112150940L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "globalSettings")
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@Column(name = "title")
	private String title;

	@Column(name = "max_authentication_allowed")
	private int maxAuthenticationAllowed;

	@Column(name = "captcha_on_signup")
	private boolean captchaOnSignUp;
	
	@Column(name = "enable_personal_settings")
	private boolean enablePersonalSettings;
	
	@Column(name = "email_format")
	private String emailFormat;
	
	@Column(name = "introduction")
	private String introduction;

	@Column(name = "enable_logout_confirmation")
	private boolean enableLogoutConfirmation;

	@Column(name = "enable_contact_administrator")
	private boolean enableContactAdministrator;

	@Column(name = "contact_administrator_message")
	private String contactAdministratorMessage;
	
	public GlobalSettings() {}

	@Override
	public void setDefaultSettings(Properties properties) {
		maxAuthenticationAllowed = Integer.parseInt(properties.getProperty("settings.global.default.maxAuthenticationAllowed"));
		captchaOnSignUp = Boolean.parseBoolean(properties.getProperty("settings.global.default.captchaOnSignUp"));
		emailFormat = properties.getProperty("settings.global.default.emailFormat");
		introduction = properties.getProperty("settings.global.default.introduction");
		enableLogoutConfirmation = Boolean.parseBoolean(properties.getProperty("settings.global.default.enableLogoutConfirmation"));
		enableContactAdministrator = Boolean.parseBoolean(properties.getProperty("settings.global.default.enableContactAdministrator"));
		contactAdministratorMessage = properties.getProperty("settings.global.default.contactAdministratorMessage");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMaxAuthenticationAllowed() {
		return maxAuthenticationAllowed;
	}

	public void setMaxAuthenticationAllowed(int maxAuthenticationAllowed) {
		this.maxAuthenticationAllowed = maxAuthenticationAllowed;
	}

	public boolean isCaptchaOnSignUp() {
		return captchaOnSignUp;
	}

	public void setCaptchaOnSignUp(boolean captchaOnSignUp) {
		this.captchaOnSignUp = captchaOnSignUp;
	}

	public String getEmailFormat() {
		return emailFormat;
	}

	public void setEmailFormat(String emailFormat) {
		this.emailFormat = emailFormat;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isEnableLogoutConfirmation() {
		return enableLogoutConfirmation;
	}

	public void setEnableLogoutConfirmation(boolean enableLogoutConfirmation) {
		this.enableLogoutConfirmation = enableLogoutConfirmation;
	}

	public boolean isEnableContactAdministrator() {
		return enableContactAdministrator;
	}

	public void setEnableContactAdministrator(boolean enableContactAdministrator) {
		this.enableContactAdministrator = enableContactAdministrator;
	}

	public String getContactAdministratorMessage() {
		return contactAdministratorMessage;
	}

	public void setContactAdministratorMessage(String contactAdministratorMessage) {
		this.contactAdministratorMessage = contactAdministratorMessage;
	}
	
}

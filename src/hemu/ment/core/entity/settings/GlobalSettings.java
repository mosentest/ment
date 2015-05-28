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
	
	@Column(name = "enable_personal_settings")
	private boolean enablePersonalSettings;

	@Column(name = "enable_logout_confirmation")
	private boolean enableLogoutConfirmation;
	
	public GlobalSettings() {}

	@Override
	public void setDefaultSettings(Properties properties) {
		maxAuthenticationAllowed = Integer.parseInt(properties.getProperty("settings.global.default.maxAuthenticationAllowed"));
		enableLogoutConfirmation = Boolean.parseBoolean(properties.getProperty("settings.global.default.enableLogoutConfirmation"));
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

	public boolean isEnableLogoutConfirmation() {
		return enableLogoutConfirmation;
	}

	public void setEnableLogoutConfirmation(boolean enableLogoutConfirmation) {
		this.enableLogoutConfirmation = enableLogoutConfirmation;
	}

	public boolean isEnablePersonalSettings() {
		return enablePersonalSettings;
	}

	public void setEnablePersonalSettings(boolean enablePersonalSettings) {
		this.enablePersonalSettings = enablePersonalSettings;
	}
}

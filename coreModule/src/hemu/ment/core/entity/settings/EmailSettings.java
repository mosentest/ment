package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.RegexFilter;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.persistence.*;

@Entity
@Table(name = "t_settings_email", schema = "ment_core")
public class EmailSettings implements SettingsEntity, Serializable {

	private static final long serialVersionUID = -5262004266517206358L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "emailSettings")
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "enable_email_notification")
	private boolean enableEmailNotification;

	@Column(name = "smtp_host")
	private String smtpHost;

	@Column(name = "smtp_port")
	private int smtpPort;

	@Column(name = "smtp_username")
	private String smtpUsername;

	@Column(name = "smtp_password")
	private String smtpPassword;

	@Column(name = "smtp_password_hash")
	private String smtpPasswordHash;

	@Column(name = "smtp_auth")
	private boolean smtpAuth;//for uname and password

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "sender_mail")
	private String senderMail;

	@Column(name = "connection_prefix")
	private String connectionPrefix;

	@Column(name = "ticket_template")
	private String ticketTemplate;

	@Column(name = "support_template")
	private String supportTemplate;

	@Column(name = "banned_domains")
	private String bannedDomains;

	@Column(name = "banned_adresses")
	private String bannedAdresses;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(schema = "ment_core", name = "t_email_filter",
			joinColumns = @JoinColumn(name = "settings_id"),
			inverseJoinColumns = @JoinColumn(name = "filter_id"))
	private List<RegexFilter> filters;

	@Override
	public void setDefaultSettings(Properties properties) {

	}

	@Override
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

	public boolean isEnableEmailNotification() {
		return enableEmailNotification;
	}

	public void setEnableEmailNotification(boolean enableEmailNotification) {
		this.enableEmailNotification = enableEmailNotification;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public boolean isSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getConnectionPrefix() {
		return connectionPrefix;
	}

	public void setConnectionPrefix(String connectionPrefix) {
		this.connectionPrefix = connectionPrefix;
	}

	public String getTicketTemplate() {
		return ticketTemplate;
	}

	public void setTicketTemplate(String ticketTemplate) {
		this.ticketTemplate = ticketTemplate;
	}

	public String getSupportTemplate() {
		return supportTemplate;
	}

	public void setSupportTemplate(String supportTemplate) {
		this.supportTemplate = supportTemplate;
	}

	public String getBannedDomains() {
		return bannedDomains;
	}

	public void setBannedDomains(String bannedDomains) {
		this.bannedDomains = bannedDomains;
	}

	public String getBannedAdresses() {
		return bannedAdresses;
	}

	public void setBannedAdresses(String bannedAdresses) {
		this.bannedAdresses = bannedAdresses;
	}

	public List<RegexFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<RegexFilter> filters) {
		this.filters = filters;
	}

	public String getSmtpPasswordHash() {
		return smtpPasswordHash;
	}

	public void setSmtpPasswordHash(String smtpPasswordHash) {
		this.smtpPasswordHash = smtpPasswordHash;
	}
}

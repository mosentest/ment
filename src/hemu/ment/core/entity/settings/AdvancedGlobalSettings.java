package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Enterprise;

import java.io.Serializable;
import java.util.Properties;

import javax.persistence.*;

@Entity
@Table(name = "t_settings_advancedglobal", schema = "ment_core")
public class AdvancedGlobalSettings implements SettingsEntity, Serializable {
	
	private static final long serialVersionUID = 727855077877535985L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "advancedGlobalSettings")
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "attachment_number_of_zip_entries")
	private int attachmentNumberOfZipEntries;
	
	@Column(name = "clone_prefix")
	private String clonePrefix;

	@Column(name = "date_time_java_format")
	private String dateTimeJavaFormat;

	@Column(name = "date_time_javascript_format")
	private String dateTimeJavascriptFormat;

	@Column(name = "date_java_format")
	private String dateJavaFormat;

	@Column(name = "date_javascript_format")
	private String dateJavascriptFormat;

	@Column(name = "ascending_issue_action_order")
	private boolean ascendingIssueActionOrder;	

	@Column(name = "autocomplete_max_results")
	private int autocompleteMaxResults;
	
	public AdvancedGlobalSettings() {}
	
	@Override
	public void setDefaultSettings(Properties properties) {
		attachmentNumberOfZipEntries = Integer.parseInt(properties.getProperty("settings.globaladvanced.default.attachmentNumberOfZipEntries"));
		clonePrefix = properties.getProperty("settings.globaladvanced.default.clonePrefix");
		dateTimeJavaFormat = properties.getProperty("settings.globaladvanced.default.dateTimeJavaFormat");
		dateTimeJavascriptFormat = properties.getProperty("settings.globaladvanced.default.dateTimeJavascriptFormat");
		dateJavaFormat = properties.getProperty("settings.globaladvanced.default.dateJavaFormat");
		dateJavascriptFormat = properties.getProperty("settings.globaladvanced.default.dateJavascriptFormat");
		ascendingIssueActionOrder = Boolean.valueOf(properties.getProperty("settings.globaladvanced.default.ascendingIssueActionOrder"));
		autocompleteMaxResults = Integer.parseInt(properties.getProperty("settings.globaladvanced.default.autocompleteMaxResults"));
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

	public int getAttachmentNumberOfZipEntries() {
		return attachmentNumberOfZipEntries;
	}

	public void setAttachmentNumberOfZipEntries(int attachmentNumberOfZipEntries) {
		this.attachmentNumberOfZipEntries = attachmentNumberOfZipEntries;
	}

	public String getClonePrefix() {
		return clonePrefix;
	}

	public void setClonePrefix(String clonePrefix) {
		this.clonePrefix = clonePrefix;
	}

	public String getDateTimeJavaFormat() {
		return dateTimeJavaFormat;
	}

	public void setDateTimeJavaFormat(String dateTimeJavaFormat) {
		this.dateTimeJavaFormat = dateTimeJavaFormat;
	}

	public String getDateTimeJavascriptFormat() {
		return dateTimeJavascriptFormat;
	}

	public void setDateTimeJavascriptFormat(String dateTimeJavascriptFormat) {
		this.dateTimeJavascriptFormat = dateTimeJavascriptFormat;
	}

	public String getDateJavaFormat() {
		return dateJavaFormat;
	}

	public void setDateJavaFormat(String dateJavaFormat) {
		this.dateJavaFormat = dateJavaFormat;
	}

	public String getDateJavascriptFormat() {
		return dateJavascriptFormat;
	}

	public void setDateJavascriptFormat(String dateJavascriptFormat) {
		this.dateJavascriptFormat = dateJavascriptFormat;
	}

	public boolean isAscendingIssueActionOrder() {
		return ascendingIssueActionOrder;
	}

	public void setAscendingIssueActionOrder(boolean ascendingIssueActionOrder) {
		this.ascendingIssueActionOrder = ascendingIssueActionOrder;
	}

	public int getAutocompleteMaxResults() {
		return autocompleteMaxResults;
	}

	public void setAutocompleteMaxResults(int autocompleteMaxResults) {
		this.autocompleteMaxResults = autocompleteMaxResults;
	}
	
}

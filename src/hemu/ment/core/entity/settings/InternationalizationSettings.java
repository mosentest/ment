package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Enterprise;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.persistence.*;

@Entity
@Table(name = "t_settings_internationalization", schema = "ment_core")
public class InternationalizationSettings implements SettingsEntity, Serializable {

	private static final long serialVersionUID = 4720358394692523462L;
	
	public static final TimeZone[] SUPPORTED_TIMEZONE = {
		TimeZone.getTimeZone("CET"),
		TimeZone.getTimeZone("CTT"),
		TimeZone.getTimeZone("PST")
	};
	
	public static final Locale[] SUPPORTED_LOCALE = {
		Locale.US,
		Locale.CHINA,
		new Locale("sv", "SE")
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "internationalizationSettings")
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "default_timezone_id")
	private String defaultTimeZoneID;

	@Transient
	private TimeZone defaultTimeZone;
	
	@Column(name = "default_locale")
	private String defaultLocaleString;
	
	@Transient
	private Locale defaultLocale;
	
	@Column(name = "date_format")
	private String dateFormatString; 
	
	@Transient
	private SimpleDateFormat dateFormat;
	
	@Column(name = "time_format")
	private String timeFormatString;
	
	@Transient
	private SimpleDateFormat timeFormat;
	
	@Column(name = "datetime_format")
	private String dateTimeFormatString;
	
	@Transient
	private SimpleDateFormat dateTimeFormat;
	public InternationalizationSettings() {}
	
	public static boolean isSupportedLocale(String localeString) {
		for (Locale locale : SUPPORTED_LOCALE) {
			System.out.println(locale.toString());
			if (locale.toString().equals(localeString)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSupportedTimeZone(String timeZoneID) {
		for (TimeZone timeZone : SUPPORTED_TIMEZONE) {
			if (timeZone.getID().equals(timeZoneID)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setDefaultSettings(Properties properties) {
		defaultTimeZoneID = properties.getProperty("settings.i18n.default.defaultTimeZoneID");
		defaultLocaleString = properties.getProperty("settings.i18n.default.defaultLocaleString");
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

	public String getDefaultTimeZoneID() {
		return defaultTimeZoneID;
	}

	public void setDefaultTimeZoneID(String defaultTimeZoneID) {
		this.defaultTimeZoneID = defaultTimeZoneID;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getDefaultLocaleString() {
		return defaultLocaleString;
	}

	public void setDefaultLocaleString(String defaultLocaleString) {
		this.defaultLocaleString = defaultLocaleString;
	}

	public TimeZone getDefaultTimeZone() {
		return defaultTimeZone;
	}

	public void setDefaultTimeZone(TimeZone defaultTimeZone) {
		this.defaultTimeZone = defaultTimeZone;
	}

	public String getDateFormatString() {
		return dateFormatString;
	}

	public void setDateFormatString(String dateFormatString) {
		this.dateFormatString = dateFormatString;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormatString() {
		return timeFormatString;
	}

	public void setTimeFormatString(String timeFormatString) {
		this.timeFormatString = timeFormatString;
	}

	public SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(SimpleDateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDateTimeFormatString() {
		return dateTimeFormatString;
	}

	public void setDateTimeFormatString(String dateTimeFormatString) {
		this.dateTimeFormatString = dateTimeFormatString;
	}

	public SimpleDateFormat getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(SimpleDateFormat dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}
	
}

package hemu.ment.core.constant;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by muu on 2015/5/27.
 */
public class SupportedConstant {

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

	public static boolean isSupportedLocale(String localeString) {
		return LOCALE_MAP.containsKey(localeString);
	}

	public static boolean isSupportedTimeZone(String timeZoneID) {
		return TIME_ZONE_MAP.containsKey(timeZoneID);
	}

	public static final Map<String, TimeZone> TIME_ZONE_MAP = new HashMap<>();

	public static final Map<String, Locale> LOCALE_MAP = new HashMap<>();

	static {
		TIME_ZONE_MAP.put("CET", TimeZone.getTimeZone("CET"));
		TIME_ZONE_MAP.put("CTT", TimeZone.getTimeZone("CTT"));
		TIME_ZONE_MAP.put("PST", TimeZone.getTimeZone("PST"));

		LOCALE_MAP.put("en_US", Locale.US);
		LOCALE_MAP.put("zh_CN", Locale.CHINA);
		LOCALE_MAP.put("sv_SE", new Locale("sv", "SE"));
	}

}

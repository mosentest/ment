package hemu.ment.core.utility;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static boolean isToday(Date date) {
		Calendar today = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) == today.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
	}
	
}

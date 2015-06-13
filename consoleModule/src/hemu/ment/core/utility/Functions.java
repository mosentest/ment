package hemu.ment.core.utility;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.cache.SessionObject;
import hemu.ment.core.query.Page;

import javax.inject.Inject;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by muu on 2015/5/28.
 */
public final class Functions {

	private Functions() {
	}

	public static String encodeURL(String url) {
		String encoded = null;
		try {
			encoded = URLEncoder.encode(url, "UTF-8");
		} catch (Exception e) {
			encoded = null;
		}
		return encoded;
	}

	public static <T> String printPage(Page<T> page, String url) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<ul class='pagination'>");
		if (page.getTotalPage() > 5) {
			if (page.getCurrentPage() == 0) {
				buffer.append("<li class='disabled'><a href='javascript:void();'>First</a></li>");
			} else {
				buffer.append("<li><a href='" + url + "'>First</a></li>");
			}
		}

		int total = page.getTotalPage() - 1;
		int current = page.getCurrentPage();
		if (total <= 5 || current <= 2) {
			for (int i = 0; i < total + 1 && i < 5; i++) {
				buffer.append(getLink(url, current, i));
			}
		} else if (total - current < 2) {
			for (int i = total - 4, j = 0; j < 5; i++, j++) {
				buffer.append(getLink(url, current, i));
			}
		} else {
			for (int i = current - 2, j = 0; j < 5; i++, j++) {
				buffer.append(getLink(url, current, i));
			}
		}

		if (page.getTotalPage() > 5) {
			if (page.getCurrentPage() + 1 == page.getTotalPage()) {
				buffer.append("<li class='disabled'><a href='javascript:void();'>Last</a></li>");
			} else {
				buffer.append("<li><a href='" + url + "&pn=" + (page.getTotalPage() - 1) + "'>Last</a></li>");
			}
		}
		buffer.append("<li><a href='javascript:void();'>Total " + page.getTotalElement() + " elements</a></li>");
		buffer.append("</ul>");
		return buffer.toString();
	}

	private static String getLink(String url, int current, int index) {
		return String.format("<li %s><a href='%s'>%d</a></li>",
				current == index ? "class='active'" : "", url + "&pn=" + index, index + 1);
	}

	public static int[] intArray(int begin, int size, int step) {
		int[] array = new int[size];
		for (int i = begin, j = 0; j < size; i += step, j++) {
			array[j] = i;
		}
		return array;
	}

	public static String dateFormat(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}


}

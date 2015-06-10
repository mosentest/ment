package hemu.ment.comm.entity;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by muu on 2015/6/10.
 */
public abstract class Conversation implements Comparable<Conversation> {

	public static final String PRIVATE = "PRIVATE";
	public static final String GROUP = "GROUP";

	protected String type;

	public String getType() {
		return type;
	}

	public abstract Date getLatest();

	public abstract int getnUnread();

	public abstract String getAvatar();

	public abstract String getTitle();

	public abstract String getDescription();

	@Override
	public int compareTo(Conversation o) {
		return getLatest().compareTo(o.getLatest());
	}
}

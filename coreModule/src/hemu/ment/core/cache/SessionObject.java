package hemu.ment.core.cache;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import java.io.Serializable;

/**
 * Created by muu on 2015/6/13.
 */
public class SessionObject implements Serializable {

	private static final long serialVersionUID = 1564494162588238599L;

	private User user;

	private boolean authenticated;

	public SessionObject(User user) {
		this.user = user;
	}

	public void authenticate(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public User getUser() {
		return user;
	}

	public long getUid() {
		return user.getId();
	}

	public Enterprise getEnterprise() {
		return user.getEnterprise();
	}

	public long getEid() {
		return user.getEnterprise().getId();
	}

}

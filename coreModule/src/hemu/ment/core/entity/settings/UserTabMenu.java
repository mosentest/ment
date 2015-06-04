package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.User;

import javax.persistence.*;

/**
 * Created by muu on 2015/5/25.
 */
@Entity
@Table(name = "t_user_tab_menu", schema = "ment_core")
public class UserTabMenu extends TabMenu {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by muu on 2015/6/10.
 */
@Entity
@Table(name = "t_message_private_conversation", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "PrivateConversation.List", query = "SELECT c FROM PrivateConversation c WHERE c.enterprise.id = :enterprise AND c.user.id = :user AND c.open = TRUE"),
})
public class PrivateConversation extends Conversation implements Serializable {

	private static final long serialVersionUID = 8424971411921135177L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "conversation_id")
	private Long conversationId;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "latest_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date latest;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "target_id")
	private User target;

	@Column(name = "n_unread")
	private int nUnread;

	@Column(name = "is_open")
	private boolean open;

	public PrivateConversation() {
		type = Conversation.PRIVATE;
	}

	@Override
	public String getAvatar() {
		return target.getAvatar();
	}

	@Override
	public String getTitle() {
		return target.getFullName();
	}

	@Override
	public String getDescription() {
		return null;
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

	@Override
	public int getnUnread() {
		return nUnread;
	}

	public void setnUnread(int nUnread) {
		this.nUnread = nUnread;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Date getLatest() {
		return latest;
	}

	public void setLatest(Date latest) {
		this.latest = latest;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}

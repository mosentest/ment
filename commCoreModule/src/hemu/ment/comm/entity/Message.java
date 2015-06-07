package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by muu on 2015/6/4.
 */
@Entity
@Table(name = "t_message", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "Message.Recent", query = "SELECT m FROM Message m WHERE m.conversation.id = :conversation ORDER BY date DESC")
})
public class Message implements Serializable {

	private static final long serialVersionUID = 7325167214419439874L;

	public static final int PRIVATE = 0;
	public static final int GROUP = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "is_read")
	private boolean read;

	@Column(name = "content")
	private String content;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Conversation conversation;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "from_id")
	private User from;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "to_id")
	private User to;

	@Column(name = "message_type")
	private int type;

	public Message() {}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}
}

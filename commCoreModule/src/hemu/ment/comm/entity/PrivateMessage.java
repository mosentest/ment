package hemu.ment.comm.entity;

import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by muu on 2015/6/10.
 */
@Entity
@Table(name = "t_message_private", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "PrivateMessage.Recent", query = "SELECT m FROM PrivateMessage m WHERE m.conversationId = :conversation")
})
public class PrivateMessage implements Serializable {

	private static final long serialVersionUID = 5247296663292061306L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "conversation_id")
	private Long conversationId;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	private User sender;

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "content")
	private String content;

	public PrivateMessage() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}

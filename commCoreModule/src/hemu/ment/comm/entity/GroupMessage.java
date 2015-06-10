package hemu.ment.comm.entity;

import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by muu on 2015/6/10.
 */
@Entity
@Table(name = "t_message_group", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "GroupMessage.Recent", query = "SELECT m FROM GroupMessage m WHERE m.conversation.id = :conversation")
})
public class GroupMessage implements Serializable {

	private static final long serialVersionUID = 2143740557424883585L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private GroupConversation conversation;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	private User sender;

	@Column(name = "create_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "content")
	private String content;

	public GroupMessage() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupConversation getConversation() {
		return conversation;
	}

	public void setConversation(GroupConversation conversation) {
		this.conversation = conversation;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
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
}

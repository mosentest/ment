package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by muu on 2015/6/5.
 */
@Entity
@Table(name = "t_message_conversation", schema = "ment_core")
public class Conversation implements Serializable {

	private static final long serialVersionUID = 1947773009672809761L;

	public static final int PRIVATE = 0;
	public static final int GROUP = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "conversation_type")
	private int type;

	@Column(name = "n_participant")
	private boolean nParticipant;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	private User creator;

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
	private List<ConversationParticipant> participants;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messages;

	public Conversation() {}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isnParticipant() {
		return nParticipant;
	}

	public void setnParticipant(boolean nParticipant) {
		this.nParticipant = nParticipant;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<ConversationParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ConversationParticipant> participants) {
		this.participants = participants;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
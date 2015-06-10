package hemu.ment.comm.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by muu on 2015/6/10.
 */
@Entity
@Table(name = "t_message_group_conversation", schema = "ment_core")
@NamedQueries({
		@NamedQuery(name = "GroupConversation.List", query = "SELECT c FROM GroupConversation c JOIN c.participants p WHERE c.enterprise.id = :enterprise AND p.participantId = :user AND p.open = true"),
})
public class GroupConversation extends Conversation implements Serializable {

	private static final long serialVersionUID = 9119460315519244294L;

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

	@Column(name = "n_participant")
	private int nParticipant;

	@Column(name = "latest_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date latest;

	@Transient
	private int nUnread;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	private User creator;

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
	private List<ConversationParticipant> participants;

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GroupMessage> messages;

	public GroupConversation()  {
		type = Conversation.GROUP;
	}

	@Override
	public String getAvatar() {
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
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getnParticipant() {
		return nParticipant;
	}

	public void setnParticipant(int nParticipant) {
		this.nParticipant = nParticipant;
	}

	@Override
	public Date getLatest() {
		return latest;
	}

	public void setLatest(Date latest) {
		this.latest = latest;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public int getnUnread() {
		return nUnread;
	}

	public void setnUnread(int nUnread) {
		this.nUnread = nUnread;
	}

	public List<ConversationParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ConversationParticipant> participants) {
		this.participants = participants;
	}

	public List<GroupMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<GroupMessage> messages) {
		this.messages = messages;
	}
}

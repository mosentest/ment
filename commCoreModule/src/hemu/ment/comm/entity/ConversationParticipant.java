package hemu.ment.comm.entity;

import hemu.ment.core.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by muu on 2015/6/7.
 */
@Entity
@Table(name = "t_message_conversation_participant", schema = "ment_core")
public class ConversationParticipant implements Serializable {

	private static final long serialVersionUID = 3724307650578553256L;

	@Id
	@Column(name = "participant_id")
	private Long participantId;

	@Id
	@Column(name = "conversation_id")
	private Long conversationId;

	@ManyToOne
	@JoinColumn(name = "participant_id", insertable = false, updatable = false)
	private User participant;

	@ManyToOne
	@JoinColumn(name = "conversation_id", insertable = false, updatable = false)
	private Conversation conversation;

	@Column(name = "is_open")
	private boolean open;

	@Column(name = "n_unread")
	private int nUnread;

	public ConversationParticipant() {}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getnUnread() {
		return nUnread;
	}

	public void setnUnread(int nUnread) {
		this.nUnread = nUnread;
	}
}

package hemu.ment.comm.faces;

import hemu.ment.comm.ejb.local.ConversationLocal;
import hemu.ment.comm.entity.Conversation;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by muu on 2015/6/10.
 */
@ManagedBean(name = "communicationBean")
@ViewScoped
public class CommunicationBean {

	private List<Conversation> conversationList;

	private long openedId;

	@EJB
	private ConversationLocal conversationEJB;

	@PostConstruct
	public void init() {
		conversationList = conversationEJB.list(1, 1);
	}

	public List<Conversation> getConversationList() {
		return conversationList;
	}

	public void setConversationList(List<Conversation> conversationList) {
		this.conversationList = conversationList;
	}


	public ConversationLocal getConversationEJB() {
		return conversationEJB;
	}

	public void setConversationEJB(ConversationLocal conversationEJB) {
		this.conversationEJB = conversationEJB;
	}
}

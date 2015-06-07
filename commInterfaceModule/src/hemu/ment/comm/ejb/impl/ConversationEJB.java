package hemu.ment.comm.ejb.impl;

import hemu.ment.comm.ejb.local.ConversationLocal;
import hemu.ment.comm.entity.Conversation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by muu on 2015/6/5.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ConversationEJB implements ConversationLocal {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;

	@Override
	public List<Conversation> list(long enterprise, long user) {
		return null;
	}
}

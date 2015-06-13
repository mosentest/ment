package hemu.ment.resource.ejb.impl;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.resource.ejb.local.ResourceLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by muu on 2015/6/13.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ResourceEJB implements ResourceLocal {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;


	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean userAccessible(long user, long enterprise) {
		Query query = entityManager.createNamedQuery("User.Accessible");
		query.setParameter("user", user);
		query.setParameter("enterprise", enterprise);
		return (boolean) query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Enterprise getUserEnterprise(long user) {
		Query query = entityManager.createNamedQuery("User.GetEnterprise");
		query.setParameter("user", user);
		return (Enterprise) query.getSingleResult();
	}
}

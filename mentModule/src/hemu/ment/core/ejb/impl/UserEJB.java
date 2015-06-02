
package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.remote.UserRemote;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserEJB implements UserRemote {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;
}

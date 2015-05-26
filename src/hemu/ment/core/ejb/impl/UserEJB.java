package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.ejb.remote.UserRemote;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Stateless
@Remote(UserRemote.class)
@Local(UserLocal.class)
public class UserEJB implements UserLocal, UserRemote {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;
	
}

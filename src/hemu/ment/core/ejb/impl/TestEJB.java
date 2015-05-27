package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.remote.TestRemote;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Created by muu on 2015/5/25.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TestEJB implements TestRemote {

    @PersistenceContext(unitName = "ment_core")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public boolean test1() {
        return false;
    }

    @Override
    public boolean test2() {
        return false;
    }

    @Override
    public boolean test3() {
        return false;
    }

    @Override
    public boolean test4() {
        return false;
    }

    @Override
    public boolean test5() {
        return false;
    }

    @Override
    public boolean test6() {
        return false;
    }
}

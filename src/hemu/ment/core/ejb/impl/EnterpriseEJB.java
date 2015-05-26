package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.remote.EnterpriseRemote;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.UserGroup;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by muu on 2015/5/25.
 */
@Stateless
@Remote(EnterpriseRemote.class)
public class EnterpriseEJB implements EnterpriseRemote {

    @PersistenceContext(unitName = "ment_core")
    private EntityManager coreManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public Enterprise create(Enterprise enterprise) {
        try {
            transaction.begin();
            coreManager.persist(enterprise);
            //create settings, role, user group etc...

            enterprise.getAdvancedGlobalSettings().setEnterprise(enterprise);
            coreManager.persist(enterprise.getAdvancedGlobalSettings());
            enterprise.getEmailSettings().setEnterprise(enterprise);
            coreManager.persist(enterprise.getEmailSettings());
            enterprise.getGlobalSettings().setEnterprise(enterprise);
            coreManager.persist(enterprise.getGlobalSettings());
            enterprise.getInternationalizationSettings().setEnterprise(enterprise);
            coreManager.persist(enterprise.getInternationalizationSettings());

            //menu
            //support config

            UserGroup userGroup = new UserGroup();
            userGroup.setEnterprise(enterprise);

            UserGroup adminGroup = new UserGroup();
            adminGroup.setEnterprise(enterprise);

            enterprise.getDefaultUser().setEnterprise(enterprise);
            coreManager.persist(enterprise.getDefaultUser());
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {

            }
        }
        return enterprise;
    }

    @Override
    public Enterprise update(Enterprise enterprise) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Enterprise get(Long id) {
        return null;
    }

    @Override
    public List<Enterprise> getList(int offset, int size) {
        return null;
    }
}

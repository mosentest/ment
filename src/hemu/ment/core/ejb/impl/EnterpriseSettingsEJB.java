package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.ejb.remote.EnterpriseSettingsRemote;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Created by muu on 2015/5/25.
 */
@Stateless
@Remote(EnterpriseSettingsRemote.class)
@Local(EnterpriseSettingsLocal.class)
public class EnterpriseSettingsEJB implements EnterpriseSettingsLocal, EnterpriseSettingsRemote {

    @PersistenceContext(unitName = "ment_core")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public EmailSettings updateEmailSettings(EmailSettings emailSettings) {
        return null;
    }

    @Override
    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings) {
        return null;
    }

    @Override
    public InternationalizationSettings updateInternationalizationSettings(InternationalizationSettings internationalizationSettings) {
        return null;
    }

}

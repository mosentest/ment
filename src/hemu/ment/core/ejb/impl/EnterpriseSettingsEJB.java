package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.ejb.remote.EnterpriseSettingsRemote;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnterpriseSettingsEJB implements EnterpriseSettingsLocal, EnterpriseSettingsRemote {

    @PersistenceContext(unitName = "ment_core")
    private EntityManager entityManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Enterprise> getAllSettings() {
        Query query = entityManager.createNamedQuery("Enterprise.GetAllActive");
        List<Enterprise> enterpriseList = query.getResultList();
        for (Enterprise enterprise : enterpriseList) {
            enterprise.getInternationalizationSettings().getId();
            enterprise.getGlobalSettings().getId();
            enterprise.getEmailSettings().getId();
            enterprise.getModules().size();
        }
        return enterpriseList;
    }

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

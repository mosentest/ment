package hemu.ment.core.ejb.impl;

import hemu.ment.core.constant.SupportedConstant;
import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;
import hemu.ment.core.utility.EncryptionUtil;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnterpriseSettingsEJB implements EnterpriseSettingsLocal {

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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public EmailSettings updateEmailSettings(Long id, EmailSettings settings) {
		EmailSettings managed = entityManager.find(EmailSettings.class, id);
		if (!EncryptionUtil.unchanged(managed.getSmtpPassword(), managed.getSmtpPasswordHash(), settings.getSmtpPassword())) {
			byte[] key = EncryptionUtil.createKey();
			managed.setSmtpPasswordHash(EncryptionUtil.encodeKey(key));
			managed.setSmtpPassword(EncryptionUtil.encrypt(key, settings.getSmtpPassword()));
			managed.setSmtpUsername(settings.getSmtpUsername());
		}
		managed.setSmtpHost(settings.getSmtpHost());
		managed.setSmtpPort(settings.getSmtpPort());
		managed.setSmtpAuth(settings.isSmtpAuth());
		managed.setSenderMail(settings.getSenderMail());
		managed.setSenderName(settings.getSenderName());
		managed.setConnectionPrefix(settings.getConnectionPrefix());
		managed.setEnableEmailNotification(settings.isEnableEmailNotification());
		managed.setBannedAdresses(settings.getBannedAdresses());
		managed.setBannedDomains(settings.getBannedDomains());
		managed.setSupportTemplate(settings.getSupportTemplate());
		managed.setTicketTemplate(settings.getTicketTemplate());
		//regex filter
		entityManager.flush();
		return managed;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GlobalSettings updateGlobalSettings(Long id, GlobalSettings settings) {
		GlobalSettings managed = entityManager.find(GlobalSettings.class, id);
		managed.setEnableLogoutConfirmation(settings.isEnableLogoutConfirmation());
		managed.setEnablePersonalSettings(settings.isEnablePersonalSettings());
		managed.setTitle(settings.getTitle());
		managed.setMaxAuthenticationAllowed(settings.getMaxAuthenticationAllowed());
		entityManager.flush();
		return managed;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public InternationalizationSettings updateInternationalizationSettings(Long id, InternationalizationSettings settings) {
		InternationalizationSettings managed = entityManager.find(InternationalizationSettings.class, id);
		managed.setDateFormatString(settings.getDateFormatString());
		managed.setDateTimeFormatString(settings.getDateTimeFormatString());
		managed.setTimeFormatString(settings.getTimeFormatString());
		managed.setDefaultLocaleString(settings.getDefaultLocaleString());
		managed.setDefaultTimeZoneID(settings.getDefaultTimeZoneID());
		managed.setDateFormat(new SimpleDateFormat(settings.getDateFormatString()));
		managed.setDateTimeFormat(new SimpleDateFormat(settings.getDateTimeFormatString()));
		managed.setTimeFormat(new SimpleDateFormat(settings.getTimeFormatString()));
		managed.setDefaultLocale(SupportedConstant.LOCALE_MAP.get(settings.getDefaultLocaleString()));
		managed.setDefaultTimeZone(SupportedConstant.TIME_ZONE_MAP.get(settings.getDefaultTimeZoneID()));
		return managed;
	}

}

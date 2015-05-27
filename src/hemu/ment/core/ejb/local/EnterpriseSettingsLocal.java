package hemu.ment.core.ejb.local;

import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

/**
 * Created by muu on 2015/5/25.
 */
public interface EnterpriseSettingsLocal {

    public EmailSettings updateEmailSettings(EmailSettings emailSettings);

    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings);

    public InternationalizationSettings updateInternationalizationSettings(InternationalizationSettings internationalizationSettings);

}

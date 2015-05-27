package hemu.ment.core.ejb.remote;

import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

import javax.ejb.Remote;

/**
 * Created by muu on 2015/5/25.
 */
@Remote
public interface EnterpriseSettingsRemote {

    public EmailSettings updateEmailSettings(EmailSettings emailSettings);

    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings);

    public InternationalizationSettings updateInternationalizationSettings(InternationalizationSettings internationalizationSettings);

}

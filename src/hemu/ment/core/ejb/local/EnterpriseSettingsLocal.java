package hemu.ment.core.ejb.local;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.EmailSettings;
import hemu.ment.core.entity.settings.GlobalSettings;
import hemu.ment.core.entity.settings.InternationalizationSettings;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by muu on 2015/5/25.
 */
@Local
public interface EnterpriseSettingsLocal {

    public List<Enterprise> getAllSettings();

    public EmailSettings updateEmailSettings(Long id, EmailSettings settings);

    public GlobalSettings updateGlobalSettings(Long id, GlobalSettings settings);

    public InternationalizationSettings updateInternationalizationSettings(Long id, InternationalizationSettings settings);

}

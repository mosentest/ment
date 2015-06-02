package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Identifiable;

import java.util.Properties;

public interface SettingsEntity extends Identifiable {

	public void setDefaultSettings(Properties properties);
	
}

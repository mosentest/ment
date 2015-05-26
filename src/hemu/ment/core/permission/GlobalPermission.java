package hemu.ment.core.permission;

import hemu.ment.core.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

public enum GlobalPermission implements Permission {

	SYSTEM_ADMIN("hemu.ment.permission.global.system_admin", "ROLE_GLOBAL_SYSTEM_ADMIN", 32, -1),
	SYSTEM_ADMIN_GRANT("hemu.ment.permission.global.system_admin", "ROLE_GLOBAL_SYSTEM_ADMIN", 32, -1),
	ADMIN("hemu.ment.permission.global.admin", "ROLE_GLOBAL_ADMIN", 16, 4015),
	ADMIN_GRANT("hemu.ment.permission.global.admin", "ROLE_GLOBAL_ADMIN", 16, 4015),
	PROJECT_MANAGER("hemu.ment.permission.global.admin", "ROLE_GLOBAL_ADMIN", 16, 4015),
	PROJECT_MANAGER_GRANT("hemu.ment.permission.global.admin", "ROLE_GLOBAL_ADMIN", 16, 4015),
	USER("hemu.ment.permission.global.user", "ROLE_GLOBAL_USER", 1, 0),
	USER_GRANT("hemu.ment.permission.global.user", "ROLE_GLOBAL_USER", 1, 0);
	
	private final String key;
	
	private final String authKey;
	
	private final String category = "hemu.ment.permission.global.global";
	
	private final String description;
	
	private final int authority;
	
	private final int fullAuthority;
	
	private GlobalPermission(String key, String authKey, int authority, int fullAuthority) {
		this.key = key;
		this.authKey = authKey;
		this.description = key + ".description";
		this.authority = authority;
		this.fullAuthority = fullAuthority;
	}
	
	@Override
	public int getAuthority() {
		return authority;
	}
	
	public static List<GlobalPermission> getPermissions(int fullAuthority) {
		if (CacheManager.GLOBALPERMISSION_CACHE.containsKey(fullAuthority)) {
			return CacheManager.GLOBALPERMISSION_CACHE.get(fullAuthority);
		}
		List<GlobalPermission> permissions = new ArrayList<>();
		for (GlobalPermission permission : values()) {
			if ((permission.getAuthority() & fullAuthority) == permission.getAuthority()) {
				permissions.add(permission);
			}
		}
		CacheManager.GLOBALPERMISSION_CACHE.put(fullAuthority, permissions);
		return permissions;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getFullAuthority() {
		return fullAuthority;
	}

	@Override
	public String getAuthKey() {
		return authKey;
	}
	
}

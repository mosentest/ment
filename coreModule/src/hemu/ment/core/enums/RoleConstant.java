package hemu.ment.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleConstant {

	SYSTEM_ADMIN("sys_admin", "System Administrator", "omitted..."),
	SYSTEM_DEVELOPER("sys_dev", "System Developer", "omitted..."),
	ENTERPRISE_OWNER("owner", "Enterprise Owner", "omitted..."),
	ENTERPRISE_ADMIN("admin", "Enterprise Administrator", "omitted..."),
	ENTERPRISE_SUPPORT("support", "Enterprise Support", "omitted..."),
	ENTERPRISE_USER("user", "User", "omitted..."),
	ENTERPRISE_DEVELOPER("dev", "Enterprise Developer", "omitted...");

	public final String code;

	public final String name;

	public final String description;

	private static final Map<String, RoleConstant> cache = new HashMap<>();

	static {
		for (RoleConstant roleConstant : values()) {
			cache.put(roleConstant.code, roleConstant);
		}
	}

	private RoleConstant(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public static RoleConstant getRoleConstant(String role) {
		return cache.get(role);
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}

package hemu.ment.core.enums;

public enum RoleConstant {

	SYSTEM_ADMIN("SYSTEM_ADMIN", "System Administrator", "omitted...");
	
	public final String code;
	
	public final String name;
	
	public final String description;
	
	private RoleConstant(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
}

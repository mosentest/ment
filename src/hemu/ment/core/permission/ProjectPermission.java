package hemu.ment.core.permission;

import hemu.ment.core.cache.CacheManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProjectPermission implements Permission {

	PROJECT_ADMIN("hemu.ment.permission.project.project_admin", "ROLE_PROJECT_PROJECT_ADMIN", "hemu.ment.permission.project.project", 536870912, -1),
	BROWSE_PROJECT("hemu.ment.permission.project.browse_project", "ROLE_PROJECT_BROWSE_PROJECT", "hemu.ment.permission.project.project", 268435456, 268435456),
	DEVELOPEMENT_TOOLS("hemu.ment.permission.project.development_tools", "ROLE_PROJECT_DEV_TOOLS", "hemu.ment.permission.project.project", 134217728, 134217728),
	WORK_FLOW("hemu.ment.permission.project.work_flow", "ROLE_PROJECT_WORKFLOW", "hemu.ment.permission.project.project", 67108864, 67108864),
	ISSUE_ASSIGN("hemu.ment.permission.project.issue_assign", "ROLE_PROJECT_ISSUE_ASSIGN", "hemu.ment.permission.project.issue", 33554432, 33554432),
	ASSIGNABLE_USER("hemu.ment.permission.project.assignable_user", "ROLE_PROJECT_ASSIGNABLE", "hemu.ment.permission.project.issue", 16777216, 16777216),
	ISSUE_CLOSE("hemu.ment.permission.project.issue_close", "ROLE_PROJECT_ISSUE_CLOSE", "hemu.ment.permission.project.issue", 8388608, 8388608),
	ISSUE_CREATE("hemu.ment.permission.project.issue_create", "ROLE_PROJECT_ISSUE_CREATE", "hemu.ment.permission.project.issue", 4194304, 4194304),
	ISSUE_DELETE("hemu.ment.permission.project.issue_delete", "ROLE_PROJECT_ISSUE_DEL", "hemu.ment.permission.project.issue", 2097152, 2097152),
	ISSUE_EDIT("hemu.ment.permission.project.issue_edit", "ROLE_PROJECT_ISSUE_EDIT", "hemu.ment.permission.project.issue", 1048576, 1048576),
	ISSUE_LINK("hemu.ment.permission.project.issue_link", "ROLE_PROJECT_ISSUE_LINK", "hemu.ment.permission.project.issue", 524288, 524288),
	MODIFY_REPORTER("hemu.ment.permission.project.modify_reporter", "ROLE_PROJECT_MODIFY_REPORTER", "hemu.ment.permission.project.issue", 262144, 262144),
	ISSUE_RESOLVE("hemu.ment.permission.project.issue_resolve", "ROLE_PROJECT_ISSUE_RESOLVE", "hemu.ment.permission.project.issue", 131072, 131072),
	ISSUE_SCHEDULE("hemu.ment.permission.project.issue_schedule", "ROLE_PROJECT_ISSUE_SCHEDULE", "hemu.ment.permission.project.issue", 65536, 65536),
	ISSUE_SECURITY("hemu.ment.permission.project.security_issue", "ROLE_PROJECT_ISSUE_SECURITY", "hemu.ment.permission.project.issue", 32768, 32768),
	ISSUE_TRANSITION("hemu.ment.permission.project.transition_issue", "ROLE_PROJECT_ISSUE_TRANSITION", "hemu.ment.permission.project.issue", 1073741824, 1073741824),
	
	COMMENT_ADD("hemu.ment.permission.project.comment_add", "ROLE_PROJECT_COMMENT_ADD", "hemu.ment.permission.project.comment", 16384, 16384),
	COMMENT_DEL_ALL("hemu.ment.permission.project.comment_del_all", "ROLE_PROJECT_COMMENT_DEL_ALL", "hemu.ment.permission.project.comment", 8192, 8192),
	COMMENT_DEL_OWN("hemu.ment.permission.project.comment_del_own", "ROLE_PROJECT_COMMENT_DEL_OWN", "hemu.ment.permission.project.comment", 4096, 4096),
	COMMENT_EDIT_ALL("hemu.ment.permission.project.comment_edit_all", "ROLE_PROJECT_COMMENT_EDIT_ALL", "hemu.ment.permission.project.comment", 2048, 2048),
	COMMENT_EDIT_OWN("hemu.ment.permission.project.comment_edit_own", "ROLE_PROJECT_COMMENT_EDIT_OWN", "hemu.ment.permission.project.comment", 1024, 1024),

	ATTACHMENT_ADD("hemu.ment.permission.project.attachment_add", "ROLE_PROJECT_ATTACHMENT_ADD", "hemu.ment.permission.project.attachment", 512, 512),
	ATTACHMENT_DEL_ALL("hemu.ment.permission.project.attachment_del_all", "ROLE_PROJECT_ATTACHMENT_DEL_ALL", "hemu.ment.permission.project.attachment", 256, 256),
	ATTACHMENT_DEL_OWN("hemu.ment.permission.project.attachment_del_own", "ROLE_PROJECT_ATTACHMENT_DEL_OWN", "hemu.ment.permission.project.attachment", 128, 128),

	MANAGE_WATCHER("hemu.ment.permission.project.manage_watcher", "ROLE_PROJECT_MANAGE_WATCHER", "hemu.ment.permission.project.vote_watcher", 64, 64),
	VIEW_VOTE_WATCHER("hemu.ment.permission.project.view_vote_watcher", "ROLE_PROJECT_VIEW_VOTE_WATCHER", "hemu.ment.permission.project.vote_watcher", 32, 32),

	LOG_ADD("hemu.ment.permission.project.log_add", "ROLE_PROJECT_LOG_ADD", "hemu.ment.permission.project.log", 16, 16),
	LOG_DEL_ALL("hemu.ment.permission.project.log_del_all", "ROLE_PROJECT_LOG_DEL_ALL", "hemu.ment.permission.project.log", 8, 8),
	LOG_DEL_OWN("hemu.ment.permission.project.log_del_own", "ROLE_PROJECT_LOG_DEL_OWN", "hemu.ment.permission.project.log", 4, 4),
	LOG_EDIT_ALL("hemu.ment.permission.project.log_edit_all", "ROLE_PROJECT_LOG_EDIT_ALL", "hemu.ment.permission.project.log", 2, 2),
	LOG_EDIT_OWN("hemu.ment.permission.project.log_edit_own", "ROLE_PROJECT_LOG_EDIT_OWN", "hemu.ment.permission.project.log", 1, 1);
	
	public static final List<ProjectPermission> WORKFLOW_PERMISSIONS;
	
	public static final int WORK_FLOW_ALL = 1721729024;
	
	static {
		WORKFLOW_PERMISSIONS = Arrays.asList(PROJECT_ADMIN, WORK_FLOW, ISSUE_ASSIGN, ISSUE_CLOSE, ISSUE_EDIT, ISSUE_LINK, 
				MODIFY_REPORTER, ISSUE_RESOLVE, ISSUE_SCHEDULE, 
				ISSUE_SECURITY, ISSUE_TRANSITION);
	}
	
	private final String key;
	
	private final String authKey;
	
	private final String category;
	
	private final String description;
	
	private final int authority;
	
	private final int fullAuthority;
	
	private ProjectPermission(String key, String authKey, String category, int authority, int fullAuthority) {
		this.key = key;
		this.authKey = authKey;
		this.category = category;
		this.description = key + ".description";
		this.authority = authority;
		this.fullAuthority = fullAuthority;
	}
	
	@Override
	public int getAuthority() {
		return authority;
	}
	
	public static List<ProjectPermission> getPermissions(int fullAuthority) {
		if (CacheManager.PROJECTPERMISSION_CACHE.containsKey(fullAuthority)) {
			return CacheManager.PROJECTPERMISSION_CACHE.get(fullAuthority);
		}
		List<ProjectPermission> permissions = new ArrayList<>();
		for (ProjectPermission permission : values()) {
			if ((permission.getAuthority() & fullAuthority) == permission.getAuthority()) {
				permissions.add(permission);
			}
		}
		CacheManager.PROJECTPERMISSION_CACHE.put(fullAuthority, permissions);
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

	public String getAuthKey() {
		return authKey;
	}
	
}

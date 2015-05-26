package hemu.ment.core.cache;

import hemu.ment.core.permission.GlobalPermission;
import hemu.ment.core.permission.ProjectPermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {

	public final static Map<Integer, List<GlobalPermission>> GLOBALPERMISSION_CACHE = new HashMap<>();
	
	public final static Map<Integer, List<ProjectPermission>> PROJECTPERMISSION_CACHE = new HashMap<>();
	
}

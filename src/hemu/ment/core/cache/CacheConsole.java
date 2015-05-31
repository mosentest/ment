package hemu.ment.core.cache;

import hemu.ment.core.constant.ApplicationVariable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;

@Singleton
public class CacheConsole implements Serializable {

	private static final long serialVersionUID = 5069759560292475667L;

	private Cache appCache;
	private Cache entCache;

	public CacheConsole() {
	}

	@PostConstruct
	public void init() {
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.clearAll();
		appCache = cacheManager.getCache("appCache");
		entCache = cacheManager.getCache("entCache");
		initCacheManager();
	}

	@PreDestroy
	public void destroy() {
		appCache.removeAll();
	}

	private void initCacheManager() {
		File file = new File(ApplicationVariable.IMAGE_PATH.replace("{enterprise}", ApplicationVariable.MASTER_ENT), "default.png");
		try {
			byte[] array = Files.readAllBytes(file.toPath());
			appCache.put(new Element("default-profile", array));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Element getAppCache(String key) {
		return appCache.get(key);
	}

	public String appCacheString(String key) {
		return (String) getAppCache(key).getObjectValue();
	}

	public byte[] appCacheByteArray(String key) {
		return (byte[]) getAppCache(key).getObjectValue();
	}

	public int appCacheInt(String key) {
		return (int) getAppCache(key).getObjectValue();
	}

	public boolean appCacheBoolean(String key) {
		return (boolean) getAppCache(key).getObjectValue();
	}

}

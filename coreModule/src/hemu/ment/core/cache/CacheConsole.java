package hemu.ment.core.cache;

import hemu.ment.core.constant.C;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.Identifiable;
import hemu.ment.core.entity.User;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;

@Singleton
public class CacheConsole implements Serializable {

	private static final long serialVersionUID = 5069759560292475667L;

	private MemcachedClient sessionClient;
	private MemcachedClient appClient;

	public CacheConsole() {
	}

	@PostConstruct
	public void init() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211"));
		sessionClient = builder.build();
		appClient = sessionClient;//Temporary solution
		initCacheManager();
	}

	@PreDestroy
	public void destroy() {
	}

	private void initCacheManager() {
		File file = new File(C.IMAGE_PATH.replace("{enterprise}", C.MASTER_ENT), "default.png");
		try {
			byte[] array = Files.readAllBytes(file.toPath());
			appClient.set("default-profile", 0, array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAuthToken(String address) {
		try {
			String authToken = (String) sessionClient.get(address);
			return authToken;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SessionObject getSession(String authToken) {
		try {
			SessionObject session = (SessionObject) sessionClient.get(authToken);
			return session;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean cacheSession(String authToken, Object value) {
		try {
			sessionClient.set(authToken, 3600, value);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void invalidateSession(String authToken) {
		try {
			sessionClient.delete(authToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean cacheApp(String key, Object value, int timeout) {
		try {
			appClient.set(key, timeout, value);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cacheApp(String key, Object value) {
		return cacheApp(key, value, 0);
	}

	public Object getAppCache(String key) {
		try {
			Object object = appClient.get(key);
			return object;
		} catch (Exception e) {
			return null;
		}
	}

	public <T> T getAppCache(Class<T> classType, String key) {
		try {
			T object = appClient.get(key);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public long uid(String authToken) {
		return getUser(authToken).getId();
	}

	public long eid(String authToken) {
		return getEnterprise(authToken).getId();
	}

	public User getUser(String authToken) {
		return getSession(authToken).getUser();
	}

	public Enterprise getEnterprise(String authToken) {
		return getUser(authToken).getEnterprise();
	}

	public Long getIdentifier(String key) {
		return getAppCache(Identifiable.class, key).getId();
	}

	public String getAppString(String key) {
		return (String) getAppCache(key);
	}

	public byte[] getAppByteArray(String key) {
		return (byte[]) getAppCache(key);
	}

}

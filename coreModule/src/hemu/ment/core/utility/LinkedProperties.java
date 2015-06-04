package hemu.ment.core.utility;

import java.util.*;

/**
 * Created by muu on 2015/5/29.
 */
public class LinkedProperties extends Properties {

	private Map<String, String> linkedMap = new LinkedHashMap<String, String>();

	@Override
	public synchronized Object put(Object key, Object value) {
		return linkedMap.put((String) key, (String) value);
	}

	@Override
	public synchronized boolean contains(Object value) {
		return linkedMap.containsValue(value);
	}

	@Override
	public boolean containsValue(Object value) {
		return linkedMap.containsValue(value);
	}

	public Set<String> propertyKeys() {
		return linkedMap.keySet();
	}

	public String getProperty(String key) {
		return linkedMap.get(key);
	}

	@Override
	public synchronized void clear() {
		linkedMap.clear();
	}

	@Override
	public synchronized boolean containsKey(Object key) {
		return linkedMap.containsKey(key);
	}

}
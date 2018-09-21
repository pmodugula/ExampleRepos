package core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * In order to use this effectively please package your data up into one object
 * and then add it in one go. E.g. setCache("xPath", allData). This way we will
 * not have namepace clashes.
 * 
 * @author nkhanna
 * 
 */
public class EaiCoreCacheJcn {
	private static HashMap<String, Object> cacheMap = null;
	private static core.cache.EaiCoreCacheJcn cache = null;

	static {
		cache = new core.cache.EaiCoreCacheJcn();
	}

	public static void setCache(String mapName, Object element) {
		cache._setCache(mapName, element);
	}

	public static Object getCache(String mapName) {
		Object element = cache._getCache(mapName);
		return element;
	}

	// public static Boolean ClearCacheJvm(MbElement rName, MbElement rConf) {
	public static void clearCache(String mapName) {
		cache._clearCache(mapName);
	}

	public void _setCache(String mapName, Object element) {
		synchronized (cache) {
			if (null == cacheMap) {
				cacheMap = new HashMap<String, Object>();
			}
			cacheMap.put(mapName, element);
		}
	}

	public Object _getCache(String mapName) {

		Object element = null;
		synchronized (cache) {
			if (null != cacheMap) {
				element = cacheMap.get(mapName);
			}

		}
		return element;
	}

	public void _clearCache(String mapName) {
		synchronized (cache) {
			if (null != cacheMap) {
				if (true == cacheMap.containsKey(mapName)) {
					cacheMap.remove(mapName);
				}
			}
		}
	}

	public static void main(String[] args) {
		List<String> someData = new ArrayList<String>();
		someData.add("Hello");
		someData.add(" ");
		someData.add("World");
		someData.add("!!!");
        EaiCoreCacheJcn.setCache("xPath", someData);
		Object element = EaiCoreCacheJcn.getCache("xPath");
		System.out.println("Value returned is " + element);
	}
}
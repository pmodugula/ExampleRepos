import java.util.HashMap;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbGlobalMap;

public class EaiCoreCacheWxs {
	private static final String EaiCoreCacheWxs = "EaiCoreCacheWxs";

	private static EaiCoreCacheWxs cache = null;
	
	static {
		cache = new EaiCoreCacheWxs();
	}

	//public static Boolean SetCacheWxs(MbElement rName, MbElement rConf) {
	public static Boolean SetCacheWxs(String mapName, MbElement rConf) {
		boolean set = Boolean.FALSE;
		//try {
			//String mapName = rName.getFirstChild().getValueAsString();
			set = cache._setCache(mapName, rConf);
		//} catch (MbException e) {
		//	System.out.println(e.getMessage());
		//	e.printStackTrace(System.err);
		//}
		return set;
	}
	//public static Boolean GetCacheWxs(MbElement rName, MbElement rConf) {
	public static Boolean GetCacheWxs(String mapName, MbElement rConf) {
		boolean got = Boolean.FALSE;
		//try {
			//String mapName = rName.getFirstChild().getValueAsString();
			got = cache._getCache(mapName, rConf);
		//} catch (MbException e) {
		//	System.out.println(e.getMessage());
		//	e.printStackTrace(System.err);
		//}
		return got;
	}
	//public static Boolean ClearCacheWxs(MbElement rName, MbElement rConf) {
	public static Boolean ClearCacheWxs(String mapName, MbElement rConf) {
		boolean cleared = Boolean.FALSE;
		//try {
			//String mapName = rName.getFirstChild().getValueAsString();
			cleared = cache._clearCache(mapName, rConf);
		//} catch (MbException e) {
		//	System.out.println(e.getMessage());
		//	e.printStackTrace(System.err);
		//}
		return cleared;
	}

	public Boolean _setCache(String mapName, MbElement rConf) {
		boolean set = Boolean.FALSE;
		synchronized (cache) {
			try {
				MbGlobalMap cacheMap = MbGlobalMap.getGlobalMap(EaiCoreCacheWxs);
				HashMap<String, String> map = null;
				MbElement elem = rConf.getFirstElementByPath(mapName);
				if (null != elem) {
					elem = elem.getFirstChild();
				}
				while (null != elem) {
					if (null == map) {
						map = new HashMap<String, String>();
					}
					String key = elem.getName();
					String value = elem.getValueAsString();
					map.put(key, value);
					set = Boolean.TRUE;
					elem = elem.getNextSibling();
				}
				if (true == set) {
					cacheMap.put(mapName, map);
				}
			} catch (MbException e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		return set;
	}
	@SuppressWarnings("unchecked")
	public Boolean _getCache(String mapName, MbElement rConf) {
		boolean got = Boolean.FALSE;
		synchronized (cache) {
			try {
				MbGlobalMap globalMap = MbGlobalMap.getGlobalMap(EaiCoreCacheWxs);
				HashMap<String, String> map = (HashMap<String, String>)globalMap.get(mapName);
				if (null != map) {
					MbElement rMap = rConf.getFirstElementByPath(mapName);
					if (null == rMap) {
						rMap = rConf.createElementAsLastChild(MbElement.TYPE_NAME);
						rMap.setName(mapName);
					}
					for (String key: map.keySet()) {
						MbElement elem = rMap.createElementAsLastChild(MbElement.TYPE_NAME_VALUE);
						elem.setName(key);
						String value = map.get(key);
						elem.setValue(value);
						got = Boolean.TRUE;
					}
				}
			} catch (MbException e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		return got;
	}
	public Boolean _clearCache(String mapName, MbElement rConf) {
		boolean cleared = Boolean.FALSE;
		synchronized (cache) {
			try {
				MbGlobalMap globalMap = MbGlobalMap.getGlobalMap(EaiCoreCacheWxs);
				if (true == globalMap.containsKey(mapName)) {
					/*HashMap<String, String> map = */globalMap.remove(mapName);
					MbElement elem = rConf.getFirstElementByPath(mapName);
					if (null != elem) {
						elem.detach();
					}
					cleared = Boolean.TRUE;
				}
			} catch (MbException e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		return cleared;
	}
}
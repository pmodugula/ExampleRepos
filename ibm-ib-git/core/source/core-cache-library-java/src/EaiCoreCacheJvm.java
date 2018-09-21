import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;

public class EaiCoreCacheJvm {
	private static HashMap<String, HashMap<String, Object>> cacheMap = null;
	private static EaiCoreCacheJvm cache = null;

	static {
		cache = new EaiCoreCacheJvm();
	}

	// public static Boolean SetCacheJvm(MbElement rName, MbElement rConf) {
	public static Boolean SetCacheJvm(String mapName, MbElement element) {
		boolean set = Boolean.FALSE;
		// try {
		// String mapName = rName.getFirstChild().getValueAsString();
		set = cache._setCache(mapName, element);
		// } catch (MbException e) {
		// System.out.println(e.getMessage());
		// e.printStackTrace(System.err);
		// }
		return set;
	}

	// public static Boolean GetCacheJvm(MbElement rName, MbElement rConf) {
	public static Boolean GetCacheJvm(String mapName, MbElement element) {
		boolean got = Boolean.FALSE;
		// try {
		// String mapName = rName.getFirstChild().getValueAsString();
		got = cache._getCache(mapName, element);
		// } catch (MbException e) {
		// System.out.println(e.getMessage());
		// e.printStackTrace(System.err);
		// }
		return got;
	}

	// public static Boolean ClearCacheJvm(MbElement rName, MbElement rConf) {
	public static Boolean ClearCacheJvm(String mapName, MbElement element) {
		boolean cleared = Boolean.FALSE;
		// try {
		// String mapName = rName.getFirstChild().getValueAsString();
		cleared = cache._clearCache(mapName, element);
		// } catch (MbException e) {
		// System.out.println(e.getMessage());
		// e.printStackTrace(System.err);
		// }
		return cleared;
	}

	private static Boolean _setCacheRecurse(HashMap<String, Object> map, MbElement elem) throws MbException {
		boolean set = Boolean.FALSE;
		if (null != elem) {
			elem = elem.getFirstChild();
		}
		while (null != elem) {
			int type = elem.getType();
			if (MbElement.TYPE_NAME == type) {
				String key = elem.getName();
				HashMap<String, Object> _map = new HashMap<>(); 
				set = EaiCoreCacheJvm._setCacheRecurse(_map, elem);
				map.put(key, _map);
			} else if (MbElement.TYPE_NAME_VALUE == type) {
				String key = elem.getName();
				String value = elem.getValueAsString();
				map.put(key, value);
			} else {
				// Silently ignore..
			}
			set = Boolean.TRUE;
			elem = elem.getNextSibling();
		}
		return set;
	}
	public Boolean _setCache(String mapName, Object element) { // mapName="XPATHS" element=Environment.Configuration
		boolean set = Boolean.FALSE;
		synchronized (cache) {
			try {
				if (null == cacheMap) {
					cacheMap = new HashMap<String, HashMap<String, Object>>();
				}
				HashMap<String, Object> map = new HashMap<String, Object>();
				if (true == element instanceof ObjectWrapper) {
					map.put(mapName, ((ObjectWrapper)element).get());
					set = Boolean.TRUE;
				} else if (true == element instanceof MbElement) {
					set = EaiCoreCacheJvm._setCacheRecurse(map, ((MbElement)element).getFirstElementByPath(mapName)); // Environment.Configuration.XPATHS.a=1,b=2,c=3,A={a=1,b=2}
				} else {
					// Silently ignore??!
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
	private static Boolean _getCacheRecurse(HashMap<String, ?> map, MbElement elem) throws MbException { // elem=Environment.Configuration
		boolean got = Boolean.FALSE;
		for (String key: map.keySet()) {
			Object value = map.get(key);
			// Clear-down elem's children?..
			if (true == value instanceof String) {
				MbElement _elem = elem.createElementAsLastChild(MbElement.TYPE_NAME_VALUE);
				_elem.setName(key);
				_elem.setValue((String)value);
			} else { // HashMap<String, Object>
				MbElement _elem = elem.createElementAsLastChild(MbElement.TYPE_NAME);
				_elem.setName(key);
				got = EaiCoreCacheJvm._getCacheRecurse((HashMap<String, ?>)value, _elem);
			}
		}
		return got;
	}
	public Boolean _getCache(String mapName, Object element) {
		boolean got = Boolean.FALSE;
		synchronized (cache) {
			try {
				if (null != cacheMap) {
					HashMap<String, Object> map = cacheMap.get(mapName);
					if (true == element instanceof ObjectWrapper) {
						got = Boolean.TRUE;
						((ObjectWrapper)element).set(map.get(mapName));
					} else if (true == element instanceof MbElement) {
						if (null != map) {
							got = EaiCoreCacheJvm._getCacheRecurse(map, (MbElement)element);
						}
					} else {
						// Silently ignore??!
					}
				}
			} catch (MbException e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		return got;
	}

	public Boolean _clearCache(String mapName, Object element) {
		boolean cleared = Boolean.FALSE;
		synchronized (cache) {
			try {
				if (null != cacheMap) {
					if (true == cacheMap.containsKey(mapName)) {
						/* HashMap<String, String> map = */
						cacheMap.remove(mapName);
						if (element instanceof MbElement) { 
							MbElement elem = ((MbElement)element).getFirstElementByPath(mapName);
							if (null != elem) {
								elem.detach();
							}
						}
						cleared = Boolean.TRUE;
					}
				}
			} catch (MbException e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		return cleared;
	}
	/*
	public static void main(String[] args) {
		Boolean returnStatus;
		Object returnValue = new Object();
		List<String> someData = new ArrayList<String>();
		someData.add("Hello");
		someData.add(" ");
		someData.add("World");
		someData.add("!!!");
		returnStatus = EaiCoreCacheJvm.SetCacheJvm("xPath", someData);
		if (returnStatus) {
			returnStatus = EaiCoreCacheJvm.GetCacheJvm("xPath", returnValue);
		}
		System.out.println("Value returned is " + returnValue.getClass());
	}*/
}

class ObjectWrapper {
	Object object = null;
	
	ObjectWrapper() {
	}
	
	public void set(Object object) {
		this.object = object;
	}
	public Object get() {
		return this.object;
	}
}
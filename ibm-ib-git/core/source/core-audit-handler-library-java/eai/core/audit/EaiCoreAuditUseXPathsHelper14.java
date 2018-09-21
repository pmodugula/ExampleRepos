package eai.core.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbXPath;

public class EaiCoreAuditUseXPathsHelper14 {/*
	protected static void evaluateSetOrResetElement(MbElement r, final String path, String value, boolean replace) throws MbException {
		MbElement firstE = r.getFirstElementByPath(path);
		if (null != firstE) { // Element already exists..
			if (true == replace) { // Overwrite existing value..
				if (MbElement.TYPE_NAME == firstE.getType()) { // We can't, because we're not of type name+value - so detach the element so we re-create it..
					firstE.detach();
					firstE = null;
				}
			} else { // Don't overwrite - create a new one..
				firstE = null;
			}
		}
		if (null == firstE) {
			firstE = r.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, path, value);
		} else {
			firstE.setValue(value);
		}
	}*/
	protected static Map<String, String> evaluateGetAuditXPathNs(MbElement rEnv) throws MbException {
		Map<String, String> map = null;
		MbElement rIIB_AUDIT_XPATH_NS = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH_NS__");
		MbElement rAuditXPathNs = null;
		MbElement rAuditXPathNsUrl = null;
		String __auditXPathNs__ = (null == rIIB_AUDIT_XPATH_NS ? null : rIIB_AUDIT_XPATH_NS.getName());
		while (null != rIIB_AUDIT_XPATH_NS) {
			//MbElement rAuditXPathNsId = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS_ID");
			rAuditXPathNs = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS");
			rAuditXPathNsUrl = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS_URL");
			if (null == map) {
				map = new HashMap<String, String>();
			}
			String auditXPathNsUrl = (null == rAuditXPathNsUrl ? "" : (null == rAuditXPathNsUrl.getValueAsString() ? "" : rAuditXPathNsUrl.getValueAsString()));
			String auditXPathNs = (null == rAuditXPathNsUrl ? "" : (null == rAuditXPathNs.getValueAsString() ? "" : rAuditXPathNs.getValueAsString())).trim();
			map.put(auditXPathNs+":"+auditXPathNsUrl, auditXPathNsUrl);
			rIIB_AUDIT_XPATH_NS = rIIB_AUDIT_XPATH_NS.getNextSibling();
			if (null == rIIB_AUDIT_XPATH_NS) break;
			if (false == __auditXPathNs__.equals(rIIB_AUDIT_XPATH_NS.getName())) break;
		}
		return map;
	}
	protected static Map<String, String> evaluateGetAuditXPathNsRelevantToAuditXPathId(MbElement rEnv, String auditXPathId) throws MbException {
		Map<String, String> map = null;
		MbElement rIIB_AUDIT_XPATH_XREF = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH_XREF__");
		String __auditXPathXRef__ = (null == rIIB_AUDIT_XPATH_XREF ? null : rIIB_AUDIT_XPATH_XREF.getName());
		while (null != rIIB_AUDIT_XPATH_XREF) {
			//MbElement rAuditXPathXRefId = rIIB_AUDIT_XPATH_XREF.getFirstElementByPath("AUDIT_XPATH_XREF_ID");
			MbElement rAuditXPathId = rIIB_AUDIT_XPATH_XREF.getFirstElementByPath("AUDIT_XPATH_ID");
			String _auditXPathId = (null == rAuditXPathId ? null : rAuditXPathId.getValueAsString());
			if (true == auditXPathId.equals(_auditXPathId)) {
				MbElement rAuditXPathNsId = rIIB_AUDIT_XPATH_XREF.getFirstElementByPath("AUDIT_XPATH_NS_ID");
				String auditXPathNsId = (null == rAuditXPathNsId ? null : rAuditXPathNsId.getValueAsString());
				MbElement rIIB_AUDIT_XPATH_NS = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH_NS__");
				String __auditXPathNs__ = (null == rIIB_AUDIT_XPATH_NS ? null : rIIB_AUDIT_XPATH_NS.getName());
				while (null != rIIB_AUDIT_XPATH_NS) {
					rAuditXPathNsId = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS_ID");
					String _auditXPathNsId = (null == rAuditXPathNsId ? null : rAuditXPathNsId.getValueAsString());
					if (true == auditXPathNsId.equals(_auditXPathNsId)) {
						MbElement rAuditXPathNs = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS");
						MbElement rAuditXPathNsUrl = rIIB_AUDIT_XPATH_NS.getFirstElementByPath("AUDIT_XPATH_NS_URL");
						if (null == map) {
							map = new HashMap<String, String>();
						}
						String auditXPathNsUrl = (null == rAuditXPathNsUrl ? "" : (null == rAuditXPathNsUrl.getValueAsString() ? "" : rAuditXPathNsUrl.getValueAsString()));
						String auditXPathNs = (null == rAuditXPathNsUrl ? "" : (null == rAuditXPathNs.getValueAsString() ? "" : rAuditXPathNs.getValueAsString())).trim();
						map.put(auditXPathNs+":"+auditXPathNsUrl, auditXPathNsUrl);
					}
					rIIB_AUDIT_XPATH_NS = rIIB_AUDIT_XPATH_NS.getNextSibling();
					if (null == rIIB_AUDIT_XPATH_NS) break;
					if (false == __auditXPathNs__.equals(rIIB_AUDIT_XPATH_NS.getName())) break;
				}
			}
			rIIB_AUDIT_XPATH_XREF = rIIB_AUDIT_XPATH_XREF.getNextSibling();
			if (null == rIIB_AUDIT_XPATH_XREF) break;
			if (false == __auditXPathXRef__.equals(rIIB_AUDIT_XPATH_XREF.getName())) break;
		}
		return map;
	}
	protected static void evaluateSetAuditXPathMbXPath(MbXPath mbXPath, Map<String, String> map) throws MbException {
		if (null != map) {
			for (String ns: map.keySet()) {
				String url = map.get(ns);
				int indexOf = ns.indexOf(":");
				ns = ns.substring(0, indexOf);
				if (null == ns || 0 == ns.trim().length()) {
					mbXPath.setDefaultNamespace(url);
				} else {
					mbXPath.addNamespacePrefix(ns, url);
				}
			}
		}
	}
	protected static String evaluateGetAuditXPathForAuditXPathId(MbElement rEnv, String auditXPathId) throws MbException {
		String auditXPath = null;
		MbElement rIIB_AUDIT_XPATH = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH__");
		String auditXPathName = (null == rIIB_AUDIT_XPATH ? null : rIIB_AUDIT_XPATH.getName());
		while (null != rIIB_AUDIT_XPATH) {
			MbElement rAuditXPathId = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_ID");
			String __auditXPathId__ = rAuditXPathId.getValueAsString();
			if (null != __auditXPathId__) {
				if (true == __auditXPathId__.equals(auditXPathId)) {
					MbElement rAuditXPath = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH");
					auditXPath = (null == rAuditXPath ? null : rAuditXPath.getValueAsString());
					break;
				}
			}
			rIIB_AUDIT_XPATH = rIIB_AUDIT_XPATH.getNextSibling();
			if (null == rIIB_AUDIT_XPATH) break;
			if (false == auditXPathName.equals(rIIB_AUDIT_XPATH.getName())) break;
		}
		return auditXPath;
	}
	protected static List<String> evaluateGetAuditXPathIdsWithXPathAuditParentId(MbElement rEnv, String auditXPathParentId) throws MbException {
		List<String> __auditXPathIds__ = null;
		MbElement rIIB_AUDIT_XPATH = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH__");
		String __auditXPath__ = (null == rIIB_AUDIT_XPATH ? null : rIIB_AUDIT_XPATH.getName());
		while (null != rIIB_AUDIT_XPATH) {
			MbElement rAuditXPathParentId = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_PARENT_ID");
			String __auditXPathParentId__ = (null == rAuditXPathParentId ? null : rAuditXPathParentId.getValueAsString());
			if (true == auditXPathParentId.equals(__auditXPathParentId__)) {
				if (null == __auditXPathIds__) {
					__auditXPathIds__ = new ArrayList<String>();
				}
				MbElement rAuditXPathId = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_ID");
				String __auditXPathId__ = (null == rAuditXPathId ? null : rAuditXPathId.getValueAsString());
				__auditXPathIds__.add(__auditXPathId__);
			}
			rIIB_AUDIT_XPATH = rIIB_AUDIT_XPATH.getNextSibling();
			if (null == rIIB_AUDIT_XPATH) break;
			if (false == __auditXPath__.equals(rIIB_AUDIT_XPATH.getName())) break;
		}
		return __auditXPathIds__;
	}
	protected static List<MbElement> evaluateGetAuditXPathsWithNoXPathAuditParentId(MbElement rEnv) throws MbException {
		List<MbElement> __auditXPaths__ = null;
		MbElement rIIB_AUDIT_XPATH = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH__");
		String __auditXPath__ = (null == rIIB_AUDIT_XPATH ? null : rIIB_AUDIT_XPATH.getName());
		while (null != rIIB_AUDIT_XPATH) {
			MbElement rAuditXPathParentId = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_PARENT_ID");
			String __auditXPathParentId__ = (null == rAuditXPathParentId ? null : rAuditXPathParentId.getValueAsString());
			if (null == __auditXPathParentId__ || 0 == __auditXPathParentId__.length()) {
				if (null == __auditXPaths__) {
					__auditXPaths__ = new ArrayList<MbElement>();
				}
				__auditXPaths__.add(rIIB_AUDIT_XPATH);
			}
			rIIB_AUDIT_XPATH = rIIB_AUDIT_XPATH.getNextSibling();
			if (null == rIIB_AUDIT_XPATH) break;
			if (false == __auditXPath__.equals(rIIB_AUDIT_XPATH.getName())) break;
		}
		return __auditXPaths__;
	}
}
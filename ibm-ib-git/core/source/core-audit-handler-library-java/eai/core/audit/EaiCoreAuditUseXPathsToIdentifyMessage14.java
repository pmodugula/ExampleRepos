package eai.core.audit;

import java.util.List;
import java.util.Map;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXPath;

public class EaiCoreAuditUseXPathsToIdentifyMessage14 extends MbJavaComputeNode {
	private static void _evaluate(MbElement rEnv, MbElement ref, boolean replace) throws MbException {
		List<MbElement> rIIB_AUDIT_XPATHs = EaiCoreAuditUseXPathsHelper14.evaluateGetAuditXPathsWithNoXPathAuditParentId(rEnv);
		if (null != rIIB_AUDIT_XPATHs) {
			for (MbElement rIIB_AUDIT_XPATH: rIIB_AUDIT_XPATHs) {
				MbElement rAuditXPath = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH");
				String _auditXPath = rAuditXPath.getValueAsString();
				if (null != _auditXPath) {
					MbElement rAuditXPathId = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_ID");
					String auditXPathId = (null == rAuditXPathId ? null : rAuditXPathId.getValueAsString());
					MbXPath mbXPath = new MbXPath(_auditXPath);
					Map<String, String> map = EaiCoreAuditUseXPathsHelper14.evaluateGetAuditXPathNsRelevantToAuditXPathId(rEnv, auditXPathId);
					EaiCoreAuditUseXPathsHelper14.evaluateSetAuditXPathMbXPath(mbXPath, map);
					@SuppressWarnings("unchecked")
					List<MbElement> list = (List<MbElement>)ref.evaluateXPath(mbXPath);
					if (null == list || 0 == list.size()) {
						// No Match (Okay)
					} else if (1 == list.size()) {
						MbElement rConf = rEnv.getFirstElementByPath("Configuration");
						if (null == rConf) rConf = rEnv.createElementAsLastChild(MbElement.TYPE_NAME, "Configuration", null);
						MbElement rCoreAudit = rConf.getFirstElementByPath("CoreAudit");
						if (null == rCoreAudit) rCoreAudit = rConf.createElementAsLastChild(MbElement.TYPE_NAME, "CoreAudit", null);
						rCoreAudit.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "AUDIT_XPATH_ID", auditXPathId);
						String auditXPath = (null == rAuditXPath ? "" : rAuditXPath.getValueAsString());
						rCoreAudit.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "AUDIT_XPATH", auditXPath);
						MbElement rAuditXPathDescr = rIIB_AUDIT_XPATH.getFirstElementByPath("AUDIT_XPATH_DESCR");
						String auditXPathDescr = (null == rAuditXPathDescr ? "" : rAuditXPathDescr.getValueAsString());
						rCoreAudit.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "AUDIT_XPATH_DESCR", auditXPathDescr);
						break;
					} else {
						// Ambiguous!
					}
				}
			}
		}
	}
	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbMessage inMessage = inAssembly.getMessage();
		try {
			MbElement body = inMessage.getRootElement().getLastChild();
			String bodyNameUpperCase = body.getName().toUpperCase();
			if (bodyNameUpperCase.equals("XML") || bodyNameUpperCase.equals("XMLNS") || bodyNameUpperCase.equals("XMLNSC")) {
				MbElement rEnv = inAssembly.getGlobalEnvironment().getRootElement();
				EaiCoreAuditUseXPathsToIdentifyMessage14._evaluate(rEnv, body, true);
			}
		} catch (MbException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new MbUserException(this, "evaluate()", "", "", e.toString(),	null);
		}
		this.getOutputTerminal("out").propagate(
			new MbMessageAssembly(inAssembly, new MbMessage(inMessage))
		);
	}
}
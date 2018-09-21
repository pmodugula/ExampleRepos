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

public class EaiCoreAuditUseXPathsToExtractMessageFields14 extends MbJavaComputeNode {
	private static void _evaluate(MbElement rEnv, MbElement ref, String auditXPathParentId, Map<String, String> map) throws MbException {
		List<String> auditXPathIds = EaiCoreAuditUseXPathsHelper14.evaluateGetAuditXPathIdsWithXPathAuditParentId(rEnv, auditXPathParentId);
		if (null != auditXPathIds) {
			if (null == map) {
				map = EaiCoreAuditUseXPathsHelper14.evaluateGetAuditXPathNsRelevantToAuditXPathId(rEnv, auditXPathParentId);
			}
			for (String _auditXPathId: auditXPathIds) {
				String _auditXPath = EaiCoreAuditUseXPathsHelper14.evaluateGetAuditXPathForAuditXPathId(rEnv, _auditXPathId);
				if (null != _auditXPath) {
					MbXPath mbXPath = new MbXPath(_auditXPath);
					EaiCoreAuditUseXPathsHelper14.evaluateSetAuditXPathMbXPath(mbXPath, map);
					@SuppressWarnings("unchecked")
					List<MbElement> list = (List<MbElement>)ref.evaluateXPath(mbXPath);
					if (null != list) {
						for (MbElement elem: list) {
							int type = elem.getType();
							if (MbElement.TYPE_NAME == type) {
								EaiCoreAuditUseXPathsToExtractMessageFields14._evaluate(rEnv, elem, _auditXPathId, map);
							} else if (MbElement.TYPE_NAME_VALUE == type) {
								rEnv.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "__CoreAuditBusKeyNm__", elem.getName());
								rEnv.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "__CoreAuditBusValCd__", elem.getValueAsString());
							}
						}
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
				MbElement rIIB_AUDIT_XPATH_XREF = rEnv.getFirstElementByPath("__SelectFromIIB_AUDIT_XPATH_XREF__"); // This is the XPath of our 'identified message'..
				MbElement rAuditXPathParentId = rIIB_AUDIT_XPATH_XREF.getFirstElementByPath("AUDIT_XPATH_ID");
				EaiCoreAuditUseXPathsToExtractMessageFields14._evaluate(rEnv, body, (null == rAuditXPathParentId ? null : rAuditXPathParentId.getValueAsString()), (Map<String, String>)null);
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
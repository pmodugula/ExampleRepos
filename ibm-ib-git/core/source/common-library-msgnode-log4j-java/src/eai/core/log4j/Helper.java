package eai.core.log4j;

import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;

/*
 * This utility provides the ability to deal with the Logical Message Tree 
 * that IBM provides. Since the message is stored in a LMT we can render 
 * the data in any format. I.e. even though the message is JSON we can get 
 * a DOM root node and render it as XML. 
 */
public class Helper {
	private final static String START_TOKEN = "\\$\\{";
	private final static String END_TOKEN = "\\}";
	private final static Set<String> folders = new HashSet<String>(Arrays.asList(
		"Environment",
		"Root", // InputRoot and OutputRoot are not in context unless you're in a Compute Node and it's an ESQL construct anyway..
		"Body", // InputBody/OutputBody..?
		"LocalEnvironment", // InputLocalEnvironment/OutputLocalEnvironment..?
		"ExceptionList", // InputExceptionList/OutputExceptionList..?
		"Exception" // Deprecated (not a real message root folder name)
	));
	private static Logger log = Logger.getLogger(Helper.class);
	
	public static boolean valueIsNullOrBlank(String value) {
		return (null == value || 0 == value.trim().length());
	}
	
	/**
	 * The message body is in a certain folder (depending on how it was parsed). 
	 * The folders could be XMLNSC, XMLNS, XML, BLOB etc. Parser is a misnomer 
	 * since what we really return is the folder name.
	 *   
	 * @param mbMessage
	 * @return
	 * @throws MbException
	 */
	static String getParser(MbMessage mbMessage) throws MbException {
		log.trace("Starting getParser: " + mbMessage.getClass());
		@SuppressWarnings("unchecked")
		List<MbElement> list = (List<MbElement>) mbMessage.evaluateXPath("/"); // .getRootElement().getLastChild().getName() --?
		return list.get(0).getName();
	}
	
	/**
	 * If we pass a message (MbMessage) this method returns DOMNode. We should 
	 * be able to use Document instead but that does not include the root element!!! 
	 * 
	 * @param mbMessage
	 * @return
	 * @throws MbException
	 */
	static Node folder2RootNode(MbMessage mbMessage) throws MbException {
		return mbMessage.getRootElement().getDOMNode();
	}
	
	/**
	 * Currently unused.
	 * 
	 * @param document
	 * @return
	 */
	static String document2Xml(Document document) {
		return domSource2Xml(new DOMSource(document));
	}
	
	/**
	 * Given a DOMNode it returns a XML string 
	 * 
	 * @param node
	 * @return
	 */
	static String rootNode2Xml(Node node) {
		return domSource2Xml(new DOMSource(node));
	}
	
	/**
	 * Given a DOMSource it returns a XML string 
	 * 
	 * @param node
	 * @return
	 */
	private static String domSource2Xml(DOMSource domSource) {
		String returnValue = "";
        Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			log.error(e);
		}
		if (null != transformer) {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
           	StringWriter writer = new StringWriter();
            try {
            	transformer.transform(domSource, new StreamResult(writer));
            	writer.flush();
            	returnValue = writer.toString();
            } catch (TransformerException e) {
            	log.error(e);
            }
		}
        return returnValue;
	}

	static String doXPath(MbElement element, String pathExpression)	throws MbException {
		String returnValue = null;
		if (true == Helper.valueIsNullOrBlank(pathExpression)) {
			returnValue = Helper.rootNode2Xml(element.getDOMNode());
		} else {
			Object xpathReturn = null;
			if (pathExpression.endsWith("/text()")) {
				pathExpression = pathExpression.replace("/text()", "");
				log.trace("XPath expression ends with text() new pathExpression is " + pathExpression);
				xpathReturn = element.evaluateXPath("string(" + pathExpression + ")");
			} else {
				log.trace("XPath expression does not end with text()");
				xpathReturn = element.evaluateXPath(pathExpression);
			}
			log.trace("xpathReturn: " + xpathReturn);
			if (xpathReturn instanceof String) {
				returnValue = (String)xpathReturn;
			} else if (xpathReturn instanceof MbElement) {
				returnValue = Helper.rootNode2Xml(((MbElement)xpathReturn).getDOMNode());
			} else if (xpathReturn instanceof List) {
				log.trace("We have a list");
				List<?> listOfNodes = (List<?>) xpathReturn;
				if (0 < listOfNodes.size()) {
					log.trace("List has " + listOfNodes.size() + " elements");
					MbElement firstElement = (MbElement)((List<?>)xpathReturn).get(0);
					if (1 == ((List<?>)xpathReturn).size()) {
						log.trace("List has exactly one element");
						if (null == firstElement.getFirstChild()) {
							log.trace("Element does not have any children");
							returnValue = ((MbElement)((List<?>)xpathReturn).get(0)).getValueAsString();
						} else {
							log.trace("Element has children");
							returnValue = Helper.rootNode2Xml((firstElement.getDOMNode()));
						}
					} else if (1 < ((List<?>) xpathReturn).size()) {
						// Create new result root node to hold the results
						MbElement debug1 = firstElement;
						log.trace("debug1=" + debug1);
						MbElement debug2 = debug1.getParent();
						log.trace("debug2=" + debug2);
						Node debug3 = debug1.getDOMNode();
						log.trace("debug3=" + debug3);
						Document debug4 = debug3.getOwnerDocument();
						log.trace("debug4=" + debug4);
						Node resultRootNode = debug4.createElement("ResultRootNode");
						log.trace("debug5=" + resultRootNode);
						// Node resultRootNode = ((MbElement)((List<?>)xpathReturn).get(0)).getParent().getDOMNode().getOwnerDocument().createElement("ResultRootNode");
						for (Object o : (List<?>)xpathReturn) {
							log.trace("Current MBElement: " + o);
							resultRootNode.appendChild(((MbElement)o).getDOMNode());
							if (null == returnValue) {
								returnValue = Helper.rootNode2Xml(((MbElement)o).getDOMNode());
							} else {
								returnValue = returnValue + Helper.rootNode2Xml(((MbElement)o).getDOMNode());
							}
						}
						returnValue = Helper.rootNode2Xml(resultRootNode);
					} else {
						log.trace("Xpath list contains no elements");
					}
				}
			} else {
				log.error("Unknown xpath result");
			}
		}
		log.trace("doXpath returning " + returnValue);
		return returnValue;
	}

	/**
	 * Replace token with values from the message.
	 * 
	 * @param folder
	 * @param pathExpression
	 * @return
	 */
	private static String getTokenValue(String folder, String pathExpression, MbMessageAssembly inAssembly) {
		log.trace("Starting getTokenValue");
		log.trace("XPathExpression passed in is " + pathExpression);
		pathExpression = pathExpression.replaceAll("\\.", "/");
		log.trace("XPathExpression replace . with / is " + pathExpression);
		String tokenAsIs = "${" + folder + "}";
		String returnValue = null;
		boolean hasFolder = folders.contains(folder);
		if (true == hasFolder) {
			log.trace("folder="+folder);
			try {
				if ("Environment".equals(folder)) {
					returnValue = doXPath(inAssembly.getGlobalEnvironment().getRootElement(), pathExpression);					
				} else if ("Root".equals(folder)) {
					returnValue = doXPath(inAssembly.getMessage().getRootElement(), pathExpression);					
				} else if ("Body".equals(folder)) {
					String parser = Helper.getParser(inAssembly.getMessage());
					log.trace("parser="+parser);
					if ("XML".equals(parser) || "XMLNS".equals(parser) || "XMLNSC".equals(parser)) {
						MbElement rootElement = inAssembly.getMessage().getRootElement(); 
						MbElement bodyElement = rootElement.getFirstElementByPath("/" + parser).getLastChild();
						//log.trace(parser+"="+Helper.rootNode2Xml(bodyElement.getDOMNode()));
						if (false == Helper.valueIsNullOrBlank(pathExpression) && !pathExpression.startsWith("/"))
							pathExpression = "/" + pathExpression; // Prefix / if it does not exist for XPATH
						returnValue = doXPath(bodyElement, pathExpression);					
					} else if ("JSON".equals(parser)) {
						MbElement rootElement = inAssembly.getMessage().getRootElement(); 
						MbElement bodyElement = rootElement.getFirstElementByPath("/" + parser).getLastChild();
						if (false == Helper.valueIsNullOrBlank(pathExpression) && !pathExpression.startsWith("/"))
							pathExpression = "/" + pathExpression; // Prefix / if it does not exist for XPATH
						returnValue = doXPath(bodyElement, pathExpression);					
					} else if ("BLOB".equals(parser)) {
						// TODO: What if the blob is really huge? what if it is binary?
						MbElement rootElement = inAssembly.getMessage().getRootElement(); 
						MbElement msgElement = rootElement.getFirstElementByPath("/"+parser+"/*");
						returnValue = new String((byte[])msgElement.getValue());
					}
				} else if ("LocalEnvironment".equals(folder)) {
					returnValue = doXPath(inAssembly.getLocalEnvironment().getRootElement(), pathExpression);
				} else if ("ExceptionList".equals(folder) || "Exception".equals(folder)) {
					returnValue = doXPath(inAssembly.getExceptionList().getRootElement(), pathExpression);					
				}
			} catch (MbException e) {
				// Log the error and return token
				log.error(e);
				returnValue = tokenAsIs;
			}
		} else {
			log.trace("folder="+folder+" not supported");
			returnValue = tokenAsIs;
		}
		// If all else fails return token
		// log.trace("No conditions matched!!! returning token as is");
		return returnValue;
	}

	/**
	 * Given a string extract tokens. Examples of tokens are "${body}" or
	 * "${exception}". You can suffix a xpath expression or have a simple dot
	 * (esql like) notation to pick pieces of a message. Examples of tokens with
	 * suffixes are
	 * <ul>
	 * <li>${exception.UserException.File}</li>
	 * <li>${exception.UserException.File/text()}</li>
	 * <li>${exception.//File/text()}</li>
	 * </ul>
	 * 
	 * The first piece of the token is treated as the folder.
	 * 
	 * @param logMessage
	 * @return we return a set of pairs (Using Map.Entry for convenience)
	 */
	private static Set<Map.Entry<String, String>> extractTokens(
			String logMessage) {
		// Pattern e.g body.something
		Pattern pattern = Pattern.compile("\\$\\{([A-Za-z]*)(\\.)?([^}]*)?\\}",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(logMessage);
		Set<Map.Entry<String, String>> tokens = new HashSet<Map.Entry<String, String>>();

		// Loop through tokens and get distinct supported tokens
		while (matcher.find()) {
			log.trace("matcher.group(1): " + matcher.group(1));
			log.trace("matcher.group(3): " + matcher.group(3));
			if (folders.contains(matcher.group(1))) {
				Map.Entry<String, String> tokenPair = new AbstractMap.SimpleEntry<String, String>(
						matcher.group(1), matcher.group(3));
				tokens.add(tokenPair);
			}
		}
		return tokens;
	}

	/**
	 * Tokens are stripped out so before we can use them in a regex scenario
	 * Each token must be properly escaped. For now we are escaping the
	 * following symbols ${}()[]
	 * 
	 * @param token
	 * @return
	 */
	private static String getEscapedToken(Map.Entry<String, String> token) {
		String escapedToken;
		if (true == Helper.valueIsNullOrBlank(null == token ? null : token.getValue())) {
			log.trace("XPath=<null>");
			escapedToken = START_TOKEN + token.getKey() + END_TOKEN;
		} else {
			log.trace("XPath='" + token.getValue() + "'");
			escapedToken = START_TOKEN + token.getKey() + "." + token.getValue().replaceAll("\\(", "\\\\(")
				.replaceAll("\\)", "\\\\)")
				.replaceAll("\\[", "\\\\[")
				.replaceAll("\\]", "\\\\]") + END_TOKEN;
		}
		return escapedToken;
	}

	/*
	 * Parse tokens from the logMessage with information from inAssembly
	 */
	public static String replace(String logMessage, MbMessageAssembly inAssembly) {
		String localStr = logMessage;
		Set<Map.Entry<String, String>> tokens = extractTokens(logMessage);
		// Replace the tokens with values
		for (Map.Entry<String, String> token : tokens) {
			String tokenValue = getTokenValue(token.getKey(), token.getValue(), inAssembly);
			log.trace("localStr: " + localStr);
			String escapedToken = getEscapedToken(token);
			log.trace("escapedToken: " + escapedToken);
			log.trace("tokenValue: " + tokenValue);
			if (null != tokenValue)
				localStr = localStr.replaceAll(escapedToken, tokenValue);
		}
		log.trace("Return string: " + localStr);
		return localStr;
	}
}
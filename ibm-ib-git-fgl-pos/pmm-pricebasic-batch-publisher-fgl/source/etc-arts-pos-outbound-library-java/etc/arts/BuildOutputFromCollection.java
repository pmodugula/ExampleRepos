package etc.arts;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;


public class BuildOutputFromCollection extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		//MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();

		// create new empty message
		MbMessage outMessage = new MbMessage();
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly,
				outMessage);

		try {
			// copy message headers
			// copyMessageHeaders(inMessage, outMessage);
			
			//rOutRoot points to OutputRoot.XMLNSC
			MbElement rOutRoot = outMessage.getRootElement().createElementAsLastChild("XMLNSC");
			//rInCollection points to InputRoot.XMLNSC.Collections
			MbElement rInCollection = inMessage.getRootElement().getLastChild();
			//rInPayload point to InputRoot.XMLNSC.Collection.MsgIn[1]
			MbElement rInPayload = rInCollection.getFirstElementByPath("MsgIn");
			//Copy InputRoot.XMLNSC.Collection.MsgIn[1].XMLNSC to OutputRoot.XMLNSC
			rOutRoot.addAsLastChild(rInPayload.getLastChild().getFirstChild().copy());
			// set its MsgIndex to 1 exclusively
//			rOutRoot.getFirstElementByPath("Exchange/MessageRouting/MessageIndex").setValue(1);
			//rEnv points to Environment
			MbElement rEnv = inAssembly.getGlobalEnvironment().getRootElement();
			MbElement rEnvMsgIds = rEnv.createElementAsLastChild(MbElement.TYPE_NAME, "MsgIdsForLogging",null);
			//copy MQMD.MsgId to Environment
			MbElement msgId =  rInPayload.getFirstElementByPath("MQMD/MsgId").copy();
			//rEnvMsgIds.createElementAsLastChild(MbElement.TYPE_VALUE, "MsgId",msgId);
			rEnvMsgIds.addAsLastChild(msgId);
			
			MbElement rOutMessages = rOutRoot.getLastChild().getFirstElementByPath("Messages");
			rInPayload = rInPayload.getNextSibling();
			int count = 2;
			
			// add rest of the payloads to OutputRoot.XMLNSC.Exchange.Messages
			while(rInPayload != null) {
				//String payloadName = rInPayload.getLastChild().getFirstChild().getFirstElementByPath("Messages").getLastChild().getName();
				MbElement currentPayload = rInPayload.getLastChild().getFirstChild().getFirstElementByPath("Messages").getLastChild().copy();
				currentPayload.getFirstElementByPath("Index").setValue(count);
				rOutMessages.addAsLastChild(currentPayload);
				//copy MQMD.MsgId to Environment
				msgId =  rInPayload.getFirstElementByPath("MQMD/MsgId").copy();
				rEnvMsgIds.addAsLastChild(msgId);
				rInPayload = rInPayload.getNextSibling();				
				count++;
			}
			// update final count in OutputRoot.XMLNSC.Exchange.Messages.Count
			rOutMessages.getFirstElementByPath("Count").setValue(count-1);
		} 
		catch (MbException e) {
			// Re-throw to allow Broker handling of MbException
			throw e;
		} 
		catch (RuntimeException e) {
			// Re-throw to allow Broker handling of RuntimeException
			throw e;
		} 
		catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be handled in the flow
			/*throw new MbUserException(this, "evaluate()", "", "", e.toString(),
					null);*/
			throw e;
		}

		out.propagate(outAssembly);
	}

	public void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage)
			throws MbException {
		MbElement outRoot = outMessage.getRootElement();

		// iterate though the headers starting with the first child of the root
		// element
		MbElement header = inMessage.getRootElement().getFirstChild();
		while (header != null && header.getNextSibling() != null) // stop before
																	// the last
																	// child
																	// (body)
		{
			// copy the header and add it to the out message
			outRoot.addAsLastChild(header.copy());
			// move along to next header
			header = header.getNextSibling();
		}
	}

}

package eai.core.log4j;

import org.apache.log4j.Logger;

/**
 * This class does not replace tokens because
 * 1) ESQL will format the message (It has enough context)
 * 2) It does not make sense to send the entire tree all the way here 
 *  
 * @author nkhanna, arussell
 */
public class Esql {
	public static void LogMessage(String brokerName, String executionGroupName, String loggerName, String loggerLevel, String message) {
		try {
			Manager.init(brokerName, executionGroupName);
			Manager.LogMessage(loggerName, loggerLevel, message);
		} catch (Exception e) {
			Logger.getRootLogger().fatal("Logger('root') silently ignored exception", e);
		}
	}
}
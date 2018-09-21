package com.fglsports.custom.node;

import org.apache.log4j.Logger;

import eai.core.log4j.Manager;

/**
 * Deprecated in favour of eai.core.log4j.Esql.LogMessage(String brokerName, String executionGroupName, String loggerName, String loggerLevel, String message)
 */
@Deprecated
public class EsqlLog4j {
	{
	}
	
	private EsqlLog4j() {
	}
	
	public static void logMessage(String brokerName, String executionGroupName, String loggerName, String loggerLevel, String message) {
		try {
			Manager.init(brokerName, executionGroupName);
			Manager.LogMessage(loggerName, loggerLevel, message);
		} catch (Exception e) {
			Logger.getRootLogger().fatal("Logger('root') silently ignored exception", e);
		}
	}
}
package eai.core.log4j;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Manager {
	public static final String LOG4J_PROPERTY_PREFIX_PATH;// = System.getProperty("user.home")+File.separator;;
	
	static {
		if (true == System.getProperty("os.name").toLowerCase().startsWith("win")) {
			LOG4J_PROPERTY_PREFIX_PATH = "C:\\eai\\ibm-ib";			
		} else {
			LOG4J_PROPERTY_PREFIX_PATH = "/var/mqsi";
		}
	}
	
	private static boolean log4JIsInited = false;

	// Don't instantiate me
	private Manager(){
	}
	
	public static synchronized void init(String brokerName, String executionGroupName) {
		if (false == Manager.log4JIsInited) {
			String[] log4jPropertiesArray = new String[]{
				LOG4J_PROPERTY_PREFIX_PATH+File.separator+"log4j",
				brokerName,
				executionGroupName
			};
			String log4jPropertiesFilename = null;
			boolean found = false;
			for (int j = log4jPropertiesArray.length; j > 0; j--) {
				StringBuffer buffer = new StringBuffer();
				for (int i = 1; i <= j; i++) {
					buffer.append(log4jPropertiesArray[i-1]);
					if (i == j) {
						buffer.append(".properties");
					} else {
						buffer.append("-");
					}
				}
				log4jPropertiesFilename = buffer.toString();
				found = new File(log4jPropertiesFilename).exists();
				if (true == found) {
					break;
				} else {
					System.out.println("Broker '"+brokerName+"' Server '"+executionGroupName+"' log4j*.properties file not found: "+log4jPropertiesFilename);
					if (1 == j) {
						System.err.println("Broker '"+brokerName+"' Server '"+executionGroupName+"' log4j*.properties file not found: "+log4jPropertiesFilename);
						break;
					}
				}
			}
			if (true == found) {
				System.out.println("Broker '"+brokerName+"' Server '"+executionGroupName+"' log4j*.properties file found: "+log4jPropertiesFilename);
				PropertyConfigurator.configureAndWatch(log4jPropertiesFilename, 60000L);
			}
			Manager.log4JIsInited = true;
		}
	}

	public static Level getLevel(String loggerLevel) {
		return (null != loggerLevel ? Level.toLevel(loggerLevel) : Level.toLevel(Level.FATAL_INT));//ALL_INT));
	}
	public static Level getLevel(Logger logger) {
		Level level = (null != logger.getLevel() ? logger.getLevel() : Logger.getRootLogger().getLevel());
		return (null != level ? level : Level.toLevel(Level.FATAL_INT));//ALL_INT));
	}
	public static void LogMessage(String loggerName, String loggerLevel, String message) {
		Logger logger = Logger.getLogger(loggerName);
		Manager.LogMessage(logger, Manager.getLevel(loggerLevel), Manager.getLevel(logger), message);
	}
	public static void LogMessage(Logger logger, Level iLevel, Level jLevel, String message) {
		switch (iLevel.toInt()) {/*
		case Level.ALL_INT:
			logger.log(iLevel, message);
			break;*/
		case Level.TRACE_INT:
			if (Level.TRACE_INT >= jLevel.toInt())
				logger.trace(message);
			break;
		case Level.DEBUG_INT:
			if (Level.DEBUG_INT >= jLevel.toInt())
				logger.debug(message);
			break;
		case Level.INFO_INT:
			if (Level.INFO_INT >= jLevel.toInt())
				logger.info(message);
			break;
		case Level.WARN_INT:
			if (Level.WARN_INT >= jLevel.toInt())
				logger.warn(message);
			break;
		case Level.ERROR_INT:
			if (Level.ERROR_INT >= jLevel.toInt())
				logger.error(message);
			break;
		case Level.FATAL_INT:
			if (Level.FATAL_INT >= jLevel.toInt())
				logger.fatal(message);
			break;/*
		case Level.OFF_INT:
			logger.log(iLevel, message);
			break;*/
		default:
			logger.fatal("Level '"+iLevel.toString()+"' not recognised. Message="+message);
			break;
		}
	}
}
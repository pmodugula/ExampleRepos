import org.apache.log4j.Logger;

import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;

import eai.core.log4j.Helper;
import eai.core.log4j.Manager;

public class EaiCoreLog4JNode extends MbNode implements MbNodeInterface {
	public static String getNodeName()
	{
		return EaiCoreLog4JNode.class.getSimpleName();//"EaiCoreLog4JNode";
	}
	
	private String loggerName;
	private String loggerLevel;
	private String message;

	{
		Manager.init(this.getBroker().getName(), this.getExecutionGroup().getName()); // Ideally this should happen during broker start-up..
	}
	
	public EaiCoreLog4JNode() throws MbException {
		this.createInputTerminal("in");
		this.createOutputTerminal("out");
		//this.createOutputTerminal("failure");
	}

	@Override
	public void evaluate(MbMessageAssembly inAssembly, MbInputTerminal inTerminal) throws MbException {
		if (true == Helper.valueIsNullOrBlank(this.getLogger()))
			this.setLogger(this.getMessageFlow().getApplicationName()); // Default to message flow name
		String loggerLevel = this.getLogLevel();
		String loggerName = this.getLogger();
		try {
			if (Manager.getLevel(loggerLevel).toInt() >= Manager.getLevel(loggerName).toInt())
				Manager.LogMessage(loggerName, loggerLevel, Helper.replace(message, inAssembly));
		} catch (Exception e) {
			Logger.getRootLogger().fatal("Logger('root') silently ignored exception", e);
		}
		this.getOutputTerminal("out").propagate(inAssembly);
	}

	public String getLogger() {
		return this.loggerName;
	}
	public void setLogger(String loggerName) {
		this.loggerName = loggerName;
	}

	public String getLogLevel() {
		return this.loggerLevel;
	}
	public void setLogLevel(String loggerLevel) {
		this.loggerLevel = loggerLevel;
	}
	
	public String getLogMessage() {
		return this.message;
	}
	public void setLogMessage(String message) {
		this.message = message;
	}
}
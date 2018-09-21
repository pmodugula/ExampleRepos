import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.MessageFlowProxy;

public class EaiCoreMessageFlowStop {
	public static Long MessageFlowStop(String brokerName, String executionGroupName, String messageFlowName, String applicationName, String libraryName) {
		BrokerProxy brokerProxy = null;
		long rc = 1;
		try {
			brokerProxy = BrokerProxy.getLocalInstance();
		} catch (ConfigManagerProxyLoggedException exc) {
			exc.printStackTrace(System.err);
		}
		ExecutionGroupProxy executionGroupProxy = null;
		if (null != brokerProxy) {
			rc = 2;
			try {
				executionGroupProxy = brokerProxy.getExecutionGroupByName(executionGroupName);
			} catch (ConfigManagerProxyPropertyNotInitializedException exc) {
				exc.printStackTrace(System.err);
			}
		}
		MessageFlowProxy messageFlowProxy = null;
		if (null != executionGroupProxy) {
			rc = 3;
			try {
				messageFlowProxy = executionGroupProxy.getMessageFlowByName(messageFlowName, applicationName, libraryName);
			} catch (ConfigManagerProxyPropertyNotInitializedException exc) {
				exc.printStackTrace(System.err);
			}
		}
		if (null != messageFlowProxy) {
			rc = 4;
			try {
				messageFlowProxy.stop();
				rc = 0;
			} catch (ConfigManagerProxyLoggedException exc) {
				exc.printStackTrace(System.err);
			}
		}
		return rc;
	}
}
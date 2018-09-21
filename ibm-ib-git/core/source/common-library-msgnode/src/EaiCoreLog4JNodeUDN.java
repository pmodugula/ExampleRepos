import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>EaiCoreLog4JNodeUDN</I> instance</p>
 * <p></p>
 */
public class EaiCoreLog4JNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "EaiCoreLog4JNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/common-library-msgnode/icons/full/obj16/EaiCoreLog4J.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/common-library-msgnode/icons/full/obj30/EaiCoreLog4J.gif";

	protected final static String PROPERTY_LOGGER = "logger";
	protected final static String PROPERTY_LOGLEVEL = "logLevel";
	protected final static String PROPERTY_LOGMESSAGE = "logMessage";


	/**
	 * <I>ENUM_EAICORELOG4J_LOGLEVEL</I>
	 * <pre>
	 * ENUM_EAICORELOG4J_LOGLEVEL.SELECT_A_LEVEL = SELECT_A_LEVEL
	 * ENUM_EAICORELOG4J_LOGLEVEL.TRACE = TRACE
	 * ENUM_EAICORELOG4J_LOGLEVEL.DEBUG = DEBUG
	 * ENUM_EAICORELOG4J_LOGLEVEL.INFO = INFO
	 * ENUM_EAICORELOG4J_LOGLEVEL.WARN = WARN
	 * ENUM_EAICORELOG4J_LOGLEVEL.ERROR = ERROR
	 * ENUM_EAICORELOG4J_LOGLEVEL.FATAL = FATAL
	 * </pre>
	 */
	public static class ENUM_EAICORELOG4J_LOGLEVEL {
		private String value;

		public static final ENUM_EAICORELOG4J_LOGLEVEL SELECT_A_LEVEL = new ENUM_EAICORELOG4J_LOGLEVEL("SELECT_A_LEVEL");
		public static final ENUM_EAICORELOG4J_LOGLEVEL TRACE = new ENUM_EAICORELOG4J_LOGLEVEL("TRACE");
		public static final ENUM_EAICORELOG4J_LOGLEVEL DEBUG = new ENUM_EAICORELOG4J_LOGLEVEL("DEBUG");
		public static final ENUM_EAICORELOG4J_LOGLEVEL INFO = new ENUM_EAICORELOG4J_LOGLEVEL("INFO");
		public static final ENUM_EAICORELOG4J_LOGLEVEL WARN = new ENUM_EAICORELOG4J_LOGLEVEL("WARN");
		public static final ENUM_EAICORELOG4J_LOGLEVEL ERROR = new ENUM_EAICORELOG4J_LOGLEVEL("ERROR");
		public static final ENUM_EAICORELOG4J_LOGLEVEL FATAL = new ENUM_EAICORELOG4J_LOGLEVEL("FATAL");

		protected ENUM_EAICORELOG4J_LOGLEVEL(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_EAICORELOG4J_LOGLEVEL getEnumFromString(String enumValue) {
			ENUM_EAICORELOG4J_LOGLEVEL enumConst = ENUM_EAICORELOG4J_LOGLEVEL.SELECT_A_LEVEL;
			if (ENUM_EAICORELOG4J_LOGLEVEL.TRACE.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.TRACE;
			if (ENUM_EAICORELOG4J_LOGLEVEL.DEBUG.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.DEBUG;
			if (ENUM_EAICORELOG4J_LOGLEVEL.INFO.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.INFO;
			if (ENUM_EAICORELOG4J_LOGLEVEL.WARN.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.WARN;
			if (ENUM_EAICORELOG4J_LOGLEVEL.ERROR.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.ERROR;
			if (ENUM_EAICORELOG4J_LOGLEVEL.FATAL.value.equals(enumValue)) enumConst = ENUM_EAICORELOG4J_LOGLEVEL.FATAL;
			return enumConst;
		}

		public static String[] values = new String[]{ "SELECT_A_LEVEL", "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL" };

	}
	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGGER,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"EaiCoreLog4J",	"common-library-msgnode"),
			new NodeProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGLEVEL,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.ENUMERATION, "SELECT_A_LEVEL", ENUM_EAICORELOG4J_LOGLEVEL.class,"","",	"EaiCoreLog4J",	"common-library-msgnode"),
			new NodeProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGMESSAGE,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"EaiCoreLog4J",	"common-library-msgnode")
		};
	}

	public EaiCoreLog4JNodeUDN() {
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_OUT = new OutputTerminal(this,"OutTerminal.out");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
			OUTPUT_TERMINAL_OUT
		};
	}

	@Override
	public String getTypeName() {
		return NODE_TYPE_NAME;
	}

	protected String getGraphic16() {
		return NODE_GRAPHIC_16;
	}

	protected String getGraphic32() {
		return NODE_GRAPHIC_32;
	}

	/**
	 * Set the <I>EaiCoreLog4JNodeUDN</I> "<I>logger</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>logger</I>"
	 */
	public EaiCoreLog4JNodeUDN setLogger(String value) {
		setProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGGER, value);
		return this;
	}

	/**
	 * Get the <I>EaiCoreLog4JNodeUDN</I> "<I>logger</I>" property
	 * 
	 * @return String; the value of the property "<I>logger</I>"
	 */
	public String getLogger() {
		return (String)getPropertyValue(EaiCoreLog4JNodeUDN.PROPERTY_LOGGER);
	}

	/**
	 * Set the <I>EaiCoreLog4JNodeUDN</I> "<I>logLevel</I>" property
	 * 
	 * @param value ENUM_EAICORELOG4J_LOGLEVEL ; the value to set the property "<I>logLevel</I>"
	 */
	public EaiCoreLog4JNodeUDN setLogLevel(ENUM_EAICORELOG4J_LOGLEVEL value) {
		setProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGLEVEL, value.toString());
		return this;
	}

	/**
	 * Get the <I>EaiCoreLog4JNodeUDN</I> "<I>logLevel</I>" property
	 * 
	 * @return ENUM_EAICORELOG4J_LOGLEVEL; the value of the property "<I>logLevel</I>"
	 */
	public ENUM_EAICORELOG4J_LOGLEVEL getLogLevel() {
		ENUM_EAICORELOG4J_LOGLEVEL value = ENUM_EAICORELOG4J_LOGLEVEL.getEnumFromString((String)getPropertyValue(EaiCoreLog4JNodeUDN.PROPERTY_LOGLEVEL));
		return value;
	}

	/**
	 * Set the <I>EaiCoreLog4JNodeUDN</I> "<I>logMessage</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>logMessage</I>"
	 */
	public EaiCoreLog4JNodeUDN setLogMessage(String value) {
		setProperty(EaiCoreLog4JNodeUDN.PROPERTY_LOGMESSAGE, value);
		return this;
	}

	/**
	 * Get the <I>EaiCoreLog4JNodeUDN</I> "<I>logMessage</I>" property
	 * 
	 * @return String; the value of the property "<I>logMessage</I>"
	 */
	public String getLogMessage() {
		return (String)getPropertyValue(EaiCoreLog4JNodeUDN.PROPERTY_LOGMESSAGE);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "EaiCoreLog4J";
		return retVal;
	};
}

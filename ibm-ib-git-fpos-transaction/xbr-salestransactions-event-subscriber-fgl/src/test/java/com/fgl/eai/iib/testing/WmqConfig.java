package com.fgl.eai.iib.testing;

import javax.jms.JMSException;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

/**
 * Created by dmarley on 2015-05-19.
 */
@Configuration
@PropertySource("classpath:/wmq.properties")
public class WmqConfig {
    @Autowired
    private Environment env;

    @Bean(name = "wmq")
	public JmsComponent wmq() throws JMSException, Exception {
		return JmsComponent.jmsComponent(createWMQ1());
		//return null;
	}


//    @Bean(name = "jms")
//    public JmsComponent amq() throws JMSException, Exception {
//        return JmsComponent.jmsComponent(createJmsConnectionFactory());
//    }



	/**
	 * To override properties, add beanID.property in the properties file
	 *
	 * @return a configured MQConnectionFactory
	 * @throws JMSException
	 */
	@Bean(name = "qmgr")
	public com.ibm.mq.jms.MQConnectionFactory createWMQ1() throws JMSException {

		MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
		mqConnectionFactory.setHostName(env.getProperty("qmgr.hostName", "localhost"));
		mqConnectionFactory.setPort(Integer.parseInt(env.getProperty("qmgr.port", "1414")));
		mqConnectionFactory.setQueueManager(env.getProperty("qmgr.queueManager", ""));
		mqConnectionFactory.setChannel(env.getProperty("qmgr.channel", ""));
		mqConnectionFactory.setTransportType(Integer.parseInt(env.getProperty("qmgr.transportType", String.valueOf(WMQConstants.WMQ_CM_BINDINGS_THEN_CLIENT))));

		return mqConnectionFactory;
	}


//    @Bean(name = "jmsBroker")
//    public org.apache.activemq.xbean.XBeanBrokerService createAMQBroker() throws Exception {
//        org.apache.activemq.xbean.XBeanBrokerService service = new org.apache.activemq.xbean.XBeanBrokerService();
//        TransportConnector tc = new TransportConnector();
//        tc.setUri(new URI("tcp://localhost:61616"));
//        List<TransportConnector> transportConnectors = new ArrayList<TransportConnector>();
//        transportConnectors.add(tc);
//        service.setUseJmx(true);
//        service.setPersistent(false);
//        service.setTransportConnectors(transportConnectors);
//        return service;
//    }

//    @Bean(name="jmsConnectionFactory")
//    public org.springframework.jms.connection.CachingConnectionFactory createJmsConnectionFactory() throws Exception {
//        org.springframework.jms.connection.CachingConnectionFactory cachingConnectionFactory = new org.springframework.jms.connection.CachingConnectionFactory();
//        cachingConnectionFactory.setClientId("sample");
//        org.apache.activemq.ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
//        return cachingConnectionFactory;
//    }

}

package com.marks.eai.iib.testing

import org.springframework.context.annotation.Profile;

import javax.jms.JMSException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.jms.JmsComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.ibm.mq.jms.*;
import com.ibm.mq.*;
import com.ibm.msg.client.wmq.*;

@Configuration

public class WmqConfig {

    // change this to switch between environments (ie. putting messages on manually in QA)
    static def env = "ci"

    @Bean(name = "wmq")
	public JmsComponent wmq() throws JMSException, Exception {
		return JmsComponent.jmsComponent(createWMQ1());

	}

	@Bean(name = "qmgr")
	public com.ibm.mq.jms.MQConnectionFactory createWMQ1() throws JMSException {
        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
        def config = new ConfigSlurper(env).parse(new File('src/test/resources/Config.groovy').toURL())
        mqConnectionFactory.setHostName(config.mq.hostName.toString());
		mqConnectionFactory.setPort(config.mq.port);
		mqConnectionFactory.setQueueManager(config.mq.queueManager.toString());
		mqConnectionFactory.setChannel(config.mq.channel.toString());
		mqConnectionFactory.setTransportType(Integer.parseInt(String.valueOf(WMQConstants.WMQ_CM_BINDINGS_THEN_CLIENT)));
        return mqConnectionFactory;
	}



}

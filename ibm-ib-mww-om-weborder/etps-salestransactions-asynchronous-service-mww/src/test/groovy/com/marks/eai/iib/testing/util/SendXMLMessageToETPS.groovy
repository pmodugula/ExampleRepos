package com.marks.eai.iib.testing.util

import com.marks.eai.iib.testing.EmptyRouteCamelConfiguration
import com.marks.eai.iib.testing.WmqConfig
import groovy.xml.XmlUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by rhett on 2016-12-31.
 */
public class SendXMLMessageToETPS {



    @Autowired
    CamelContext camelContext = null;

    public static void main(String[] args) {

        WmqConfig.setEnv("qa")

        ApplicationContext context = new AnnotationConfigApplicationContext(EmptyRouteCamelConfiguration.class);

        CamelContext camelContext = (CamelContext) context.getBean("camelContext");
        ProducerTemplate producer = camelContext.createProducerTemplate();

        def iibInputXMLFile = "5517820170112100037.xml"
        def iibInputXMLFileFull = "./src/test/resources/data/transaction/iib_input/${iibInputXMLFile}"

        // read the iib input file
        String inputMessage = XmlUtil.serialize(new XmlSlurper().parse(iibInputXMLFileFull))

        String inputQueue = "MWW.ETPS.SALES_IN.IBM_IB.QA"

        // send message using Camel producer template
        producer.sendBody("wmq:$inputQueue",inputMessage)


    }




}

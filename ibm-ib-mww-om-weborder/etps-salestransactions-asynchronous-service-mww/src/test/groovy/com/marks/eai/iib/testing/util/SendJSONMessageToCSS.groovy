package com.marks.eai.iib.testing.util

import com.marks.eai.iib.testing.EmptyRouteCamelConfiguration
import com.marks.eai.iib.testing.WmqConfig
import groovy.xml.XmlUtil
import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.Environment

/**
 * Created by rhett on 2016-12-31.
 */
public class SendJSONMessageToCSS {



    @Autowired
    CamelContext camelContext = null;

    public static void main(String[] args) {

        // controls which environment to send it to
        ApplicationContext context = new AnnotationConfigApplicationContext(EmptyRouteCamelConfiguration.class);
        WmqConfig.setEnv("ci")

        CamelContext camelContext = (CamelContext) context.getBean("camelContext");
        ProducerTemplate producer = camelContext.createProducerTemplate();

        def jsonInput = "./src/test/resources/data/transaction/iib_output_manual/AX54_IIB_ARTS_multiple_address_lines.json"

        // read the file
        String inputMessage = new File(jsonInput).text

        //String inputQueue = "SEND.MWW.ETPS.SALES_OUT.IBM_IB.QA"

        // local CSS input
        String inputQueue = "MARKS.CSS.TEST.Q.DEV_IN"

        // send message using Camel producer template
        producer.sendBody("wmq:$inputQueue",inputMessage)


    }




}

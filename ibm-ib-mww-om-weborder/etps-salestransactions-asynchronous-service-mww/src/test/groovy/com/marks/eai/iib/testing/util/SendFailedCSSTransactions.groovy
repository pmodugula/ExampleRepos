package com.marks.eai.iib.testing.util

import com.marks.eai.iib.testing.EmptyRouteCamelConfiguration
import com.marks.eai.iib.testing.WmqConfig
import groovy.io.FileType
import groovy.json.JsonSlurper
import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

public class SendFailedCSSTransactions {


    CamelContext camelContext = null;

    public static void main(String[] args) {

        String inputQueue = ""

        if (!args) {
            throw new Exception("No arguments passed in!!")
        }

        def fileName = args[0]
        def environment = args[1]

        if (!fileName) {
            throw new Exception("No fileName passed in")
        }

        if (!environment) {
           "No environment passed in, defaulting to CI"
            WmqConfig.setEnv("ci")
        }
        else {
            println "Setting configuration to ${environment}"

            WmqConfig.setEnv(environment)
        }

        inputQueue = new ConfigSlurper(environment).parse(new File('src/test/resources/Config.groovy').toURL()).mq.cssQueue.toString()

        File file = new File(fileName)

        if (!file.exists())
            throw new Exception("File is not valid")

        def jsonSlurper = new JsonSlurper()

        def transactions = []

        file.splitEachLine("#@#@#") { items ->
            transactions << items
        }

        println "Processing ${transactions[0].size()} transactions"

        ApplicationContext context = new AnnotationConfigApplicationContext(EmptyRouteCamelConfiguration.class);

        CamelContext camelContext = (CamelContext) context.getBean("camelContext");
        ProducerTemplate producer = camelContext.createProducerTemplate();

        transactions[0].eachWithIndex{transation,index->
            def object = jsonSlurper.parseText(transation)
            println "Sending transaction ${object.transactionId} (${index+1} of ${transactions[0].size()})"
            producer.sendBody("wmq:$inputQueue", transation)
        }

    }

}
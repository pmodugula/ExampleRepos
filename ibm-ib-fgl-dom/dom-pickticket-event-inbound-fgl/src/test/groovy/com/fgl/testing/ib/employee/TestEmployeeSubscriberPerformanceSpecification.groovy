package com.fgl.testing.ib.employee

import com.fgl.stub.payload.employee.EmployeeSubscriberStub
import com.fgl.testing.ib.BaseSpecification
import groovy.xml.XmlUtil
import spock.lang.*


/**
 * Created by mderek on 10/08/2015.
 */
class TestEmployeeSubscriberPerformanceSpecification extends BaseSpecification {
	public static boolean isCiServer() {
         boolean isCiServer
		if (System.getProperty("isCiServer")){
			isCiServer == System.getProperty("isCiServer")
            }
    }
                
     @IgnoreIf({isCiServer()})
		def "Send single largest message - assert messaging layer does not fall over"(){
        setup:
        XmlParser p = new XmlParser()
        p.setFeature("http://xml.org/sax/features/namespaces", false)
        groovy.util.Node body = p.parseText EmployeeSubscriberStub.getLargestXml("INSERT")
        setMockConsumer "jms:$rcvQ", "mock:result"
        setMockConsumer "jms:$bkOutQ", "mock:backout"

        when: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
        resultEndpoint.reset()
        backoutEndpoint.reset()

        backoutEndpoint.setExpectedMessageCount bkOutQExpectedCount
        resultEndpoint.setExpectedMessageCount rcvQExpectedCount

        println "SOURCE INPUT BODY " + XmlUtil.serialize(body)
        (1..sendCount).each{
            producer.sendBody "jms:$pubQ?timeToLive=10000&deliveryPersistent=false",XmlUtil.serialize(body)
        }

        then:
        resultEndpoint.assertIsSatisfied()

        where:
        JIRA_ID	    | pubQ 										   | rcvQ  									 | bkOutQ         						  | sendCount   | rcvQExpectedCount | bkOutQExpectedCount
        'EAI-107'   | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV'           | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV.BO'  | 1           | 1                 | 0

    }

	@IgnoreIf({isCiServer()})
		def "Send average set of large messages and assert they are received within specified time frame"(){
        setup:
        XmlParser p = new XmlParser()
        p.setFeature("http://xml.org/sax/features/namespaces", false)
        groovy.util.Node body = p.parseText EmployeeSubscriberStub.getAverageLargeXml("INSERT")
        setMockConsumer "jms:$rcvQ", "mock:result"
        setMockConsumer "jms:$bkOutQ", "mock:backout"

        when: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
        resultEndpoint.reset()
        backoutEndpoint.reset()

        backoutEndpoint.setExpectedMessageCount bkOutQExpectedCount
        resultEndpoint.setExpectedMessageCount rcvQExpectedCount
        resultEndpoint.setResultWaitTime expectedRecieveMillesec

        println "SOURCE INPUT BODY " + XmlUtil.serialize(body)
        (1..sendCount).each{
            producer.sendBody "jms:$pubQ?timeToLive=10000&deliveryPersistent=false",XmlUtil.serialize(body)
        }

        then:
        resultEndpoint.assertIsSatisfied()

        where:
        JIRA_ID	    | pubQ 										   | rcvQ  									 | bkOutQ         						  | sendCount   | rcvQExpectedCount | bkOutQExpectedCount | expectedRecieveMillesec
        'EAI-107'   | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV'  	   	   | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV.BO'  | 10          | 10                | 0                   | 2000  //2 seconds

    }

}

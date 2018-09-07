/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.testing.ib.employee

import com.fgl.stub.payload.employee.EmployeeSubscriberStub
import com.fgl.testing.ib.BaseSpecification
import groovy.xml.XmlUtil
import org.junit.Assert
import spock.lang.Unroll

class TestEmployeeSubscriberFlowSpecification extends BaseSpecification {
    @Unroll
    def "Send messages to queues and assert they are delivered"(){
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)
            groovy.util.Node body = p.parseText EmployeeSubscriberStub.getXml("INSERT")
            setMockConsumer "jms:$rcvQ", "mock:result"
            setMockConsumer "jms:$bkOutQ", "mock:backout"

        when: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
            resultEndpoint.reset()
            backoutEndpoint.reset()

            backoutEndpoint.setExpectedMessageCount bkOutQExpectedCount
            resultEndpoint.setExpectedMessageCount rcvQExpectedCount

            println "SOURCE INPUT BODY " + XmlUtil.serialize(body)
            (1..sendCount).each{
                producer.sendBody "jms:$pubQ?timeToLive=10000&deliveryPersistent=false",  XmlUtil.serialize(body)
            }

        then:
            resultEndpoint.assertIsSatisfied()

        where:
            JIRA_ID	    | pubQ 										   | rcvQ  									 | bkOutQ         						  | sendCount   | rcvQExpectedCount | bkOutQExpectedCount
            'EAI-107'   | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV'   	       | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV.BO'  | 1           | 1                 | 0
           
    }

    //Scenarios
     // 1. Expect drop message(S) --- derek to mock -- and discuss with offshores
    //  2. BackoutQueue  -- malformed queue ,  date issues, (no xsd validation), number to string scenario
}

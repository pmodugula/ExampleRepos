/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.testing.ib.aw

import com.fgl.stub.payload.aw.AWTransactionPublisherStub
import com.fgl.testing.ib.BaseSpecification
import spock.lang.Unroll

/*
Test a message which goes on the input queue of trx-sales-pos-publisher-fgl makes it to the output queue
 */
class TestAWTransactionPublisherFlowSpecification extends BaseSpecification {

    String inputQueue = "FGL.IIB.FPOS.SALESRAW_IN.IIB.CI"
    String outputQueue = "FGL.IIB.FPOS.SALESRAW_IN.IIB.CI.SUPP"
    String backoutQueue = ""
    def inputXML


    @Unroll
    def "Send messages to queues and assert they are delivered"(){
        setup:
            XmlParser p = new XmlParser()
            setMockConsumer "jms:$outputQueue", "mock:result"
            inputXML = AWTransactionPublisherStub.getXml("")

        when: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
            resultEndpoint.reset()
            resultEndpoint.setExpectedMessageCount rcvQExpectedCount

            (1..sendCount).each{
                producer.sendBody "jms:$inputQueue?timeToLive=10000&deliveryPersistent=false",  inputXML
            }

        then:

            //def result = consumer.receive "jms:$outputQueue"

            //println result
            resultEndpoint.assertIsSatisfied()

        where:
            JIRA_ID	        | sendCount      | rcvQExpectedCount
            'FPOE-10487'       |  1             | 1
           
    }

    }

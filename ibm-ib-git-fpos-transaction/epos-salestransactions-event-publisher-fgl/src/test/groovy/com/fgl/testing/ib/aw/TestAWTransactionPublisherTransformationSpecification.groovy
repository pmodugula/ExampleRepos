/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.testing.ib.aw

import com.fgl.stub.payload.aw.AWTransactionPublisherStub
import com.fgl.testing.ib.BaseSpecification
import groovy.xml.XmlUtil
import spock.lang.Unroll

/*
Test an ARTS input message is modified appropriately by trx-sales-pos-publisher-fgl. Specifically, certain headers and message routing elements are added
 */

import com.fgl.testing.ib.aw.transform.scenarios.AWTransactionPublisherScenarios as SCENARIO
import com.fgl.testing.ib.aw.transform.scenarios.InputBean as S

/**
 * TestAWTransactionPublisherTransformationSpecification
 */
class TestAWTransactionPublisherTransformationSpecification extends BaseSpecification {


    def scenarioNum
    def assertType
    def pubQ = "FGL.IIB.FPOS.SALESRAW_IN.IIB.CI"
    def rcvQ = "FGL.IIB.FPOS.SALESRAW_IN.IIB.CI.SUPP"

    @Unroll
    def "AW Transaction Publisher Validate XML Node Values"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)
            groovy.util.Node body = p.parseText AWTransactionPublisherStub.getXml("I")
            setMockConsumer "jms:$rcvQ", "mock:result"

        when: "Set XML Node to known Value"
            S scenario = SCENARIO.specList[scenarioNum]
            println "Executing Scenario ${scenarioNum}"
            println "\tJIRA ID: ${scenario?.jiraID}"
            println "\tDESCRIPTION: ${scenario?.description}"

            scenario.getSourceXpaths().each { sourceXpath, sourceValue  -> body = setNodeValue(body, sourceXpath, sourceValue) }

        and: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
            resultEndpoint.reset()
            resultEndpoint.setExpectedMessageCount 1

            println "SOURCE INPUT BODY (scenario#: $scenarioNum) : " + XmlUtil.serialize(body)
            producer.sendBody "jms:$pubQ?timeToLive=10000&deliveryPersistent=false",XmlUtil.serialize(body)

        then:
            resultEndpoint.assertIsSatisfied()
            String resultXml = resultEndpoint.getReceivedExchanges().get(0).getIn().getBody(String.class)

			println "RESULT OUTPUT BODY (scenario#: $scenarioNum): $resultXml"
            // check if the output contains a certain string
            if(assertType==ASSERT_TYPE_TEXT){
                assertOutputContains(scenario.getTargetStrings(), resultXml)
            }
                // check if the output exactly matches a certain string
            else if (assertType==ASSERT_TYPE_XML){
                scenario.getTargetXpaths().each{ targetXpath, targetValue -> assertNodeValue(resultXml, targetXpath, targetValue); }
            }
                // check if the output doe not match a certain string (ie. indicate it was changed to something new)
            else if (assertType==ASSERT_TYPE_XML_IS_NOT){
                scenario.getTargetXpaths().each{ targetXpath, targetValue -> assertNodeValueNotEqual(resultXml, targetXpath, targetValue); }
            }

        where:
            assertType                | scenarioNum
			ASSERT_TYPE_XML            | 0
            ASSERT_TYPE_XML            | 1
            ASSERT_TYPE_XML_IS_NOT     | 2

	}
}

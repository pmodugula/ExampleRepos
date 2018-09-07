package com.fgl.eai.iib.testing

import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberNonMerchCodeScenarios
import com.fgl.eai.iib.testing.scenarios.InputBean
import com.fgl.eai.iib.testing.stub.AWTransactionSubscriberStub
import groovy.xml.XmlUtil
import spock.lang.Unroll

/*
Test an ARTS input message is modified appropriately by trx-sales-aw-subscriber-fgl. Mainly tests 1->1 mapping of ARTS->header
 */
/**
 * TestAWTransactionSubscriberTransformationSpecification
 */
class TestAWTransactionSubscriberNonMerchTransformationSpecification extends BaseSpecification {


    def assertType
    def scenarioNum
    def resultXML

    static def stubs = new AWTransactionSubscriberStub()

    static def scenarios = new AWTransactionSubscriberNonMerchCodeScenarios()

    @Unroll
    def "AW Transaction Subscriber Non Merch Items Validate XML Node Values"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)

            groovy.util.Node body = p.parseText stubs.getXmlNonMerch()


        when: "Set XML Node to known Value"
            InputBean scenario = scenarios.specList[scenarioNum]
            println "Executing Scenario ${scenarioNum}"
            println "\tJIRA ID: ${scenario?.jiraID}"
            println "\tDESCRIPTION: ${scenario?.description}"

            scenario.getSourceXpaths().each { sourceXpath, sourceValue  -> body = setNodeValue(body, sourceXpath, sourceValue) }

        and: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
            resultEndpoint.reset()

            def input = XmlUtil.serialize(body)
            println "SOURCE INPUT BODY (scenario#: $scenarioNum) : " + input

            resultXML = producer.requestBodyAndHeader("jms:$inputQueue?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=10000&asyncConsumer=true&asyncStartListener=true&replyToType=Shared", input, 'TestingHeader', 'True', String.class)


        then:

			println "RESULT OUTPUT BODY (scenario#: $scenarioNum): $resultXML"
            // check if the output contains a certain string
            if(assertType==ASSERT_TYPE_TEXT){
                assertOutputContains(scenario.getTargetStrings(), resultXML)
            }
                // check if the output exactly matches a certain string
            else if (assertType==ASSERT_TYPE_XML){
                scenario.getTargetXpaths().each{ targetXpath, targetValue -> assertNodeValue(resultXML, targetXpath, targetValue); }
            }


        where:
            assertType                | scenarioNum

			// basic tests for non merch code translation
            ASSERT_TYPE_XML            | 0
            ASSERT_TYPE_XML            | 1
            ASSERT_TYPE_XML            | 2
            ASSERT_TYPE_XML            | 3
            ASSERT_TYPE_XML            | 4

            ASSERT_TYPE_XML            | 5
            ASSERT_TYPE_XML            | 6
            ASSERT_TYPE_XML            | 7
            ASSERT_TYPE_XML            | 8
            ASSERT_TYPE_XML            | 9





    }
}

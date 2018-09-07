package com.fgl.eai.iib.testing

import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberTenderScenarios
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
class TestAWTransactionSubscriberTenderTransformationSpecification extends BaseSpecification {


    def assertType
    def scenarioNum
    def resultXML
    static def stubInstance

    protected static final NORMAL_STUB = "NORMAL_STUB" // used for most tender scenarios
    protected static final STUB_NO_TENDER_SUBTYPE = "STUB_NO_TENDER_SUBTYPE" // a special stub with no SubTenderType attribute (for debit and gift card testing)

    static def stubs = new AWTransactionSubscriberStub()

    static def scenarios = new AWTransactionSubscriberTenderScenarios()

    @Unroll
    def "AW Transaction Subscriber Tenders Validate XML Node Values"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)


            groovy.util.Node body
            if (stubInstance==NORMAL_STUB)
                body = p.parseText stubs.getXml()
            else if (stubInstance==STUB_NO_TENDER_SUBTYPE)
                body = p.parseText stubs.getXmlNoSubTender()



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
            assertType                | scenarioNum     | stubInstance

			// basic tests for tender translation

            // cash
            ASSERT_TYPE_XML            | 0              | NORMAL_STUB
            ASSERT_TYPE_XML            | 1              | NORMAL_STUB
            ASSERT_TYPE_XML            | 2              | NORMAL_STUB

            // credit/debit
            ASSERT_TYPE_XML            | 3              | NORMAL_STUB
            ASSERT_TYPE_XML            | 4              | STUB_NO_TENDER_SUBTYPE // this is a credit debit so no subtendertype
            ASSERT_TYPE_XML            | 5              | NORMAL_STUB
            ASSERT_TYPE_XML            | 6              | NORMAL_STUB
            ASSERT_TYPE_XML            | 7              | NORMAL_STUB

            ASSERT_TYPE_XML            | 8              | NORMAL_STUB

            // gift cards
            ASSERT_TYPE_XML            | 9              | NORMAL_STUB
            ASSERT_TYPE_XML            | 10             | NORMAL_STUB
            ASSERT_TYPE_XML            | 11             | NORMAL_STUB
            ASSERT_TYPE_XML            | 12             | NORMAL_STUB
            ASSERT_TYPE_XML            | 13             | STUB_NO_TENDER_SUBTYPE

            // others
            ASSERT_TYPE_XML            | 14             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 15             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 16             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 17             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 18             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 19             | STUB_NO_TENDER_SUBTYPE
            ASSERT_TYPE_XML            | 20             | STUB_NO_TENDER_SUBTYPE





    }
}

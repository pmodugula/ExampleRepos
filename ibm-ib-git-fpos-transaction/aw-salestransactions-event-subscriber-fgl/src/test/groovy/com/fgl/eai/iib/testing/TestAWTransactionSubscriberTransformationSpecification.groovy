package com.fgl.eai.iib.testing

import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberItemDiscountScenarios
import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberReturnReasonScenarios
import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberScenarios
import com.fgl.eai.iib.testing.scenarios.AWTransactionSubscriberTransactionDiscountScenarios
import com.fgl.eai.iib.testing.scenarios.InputBean
import com.fgl.eai.iib.testing.stub.AWTransactionSubscriberReturnReasonStub
import com.fgl.eai.iib.testing.stub.AWTransactionSubscriberStub
import com.fgl.eai.iib.testing.stub.AWTransactionSubscriberItemDiscountStub
import com.fgl.eai.iib.testing.stub.AWTransactionSubscriberTransactionDiscountStub
import groovy.xml.XmlUtil
import spock.lang.Unroll

/*
Test an ARTS input message is modified appropriately by trx-sales-aw-subscriber-fgl. Mainly tests 1->1 mapping of ARTS->header
 */
/**
 * TestAWTransactionSubscriberTransformationSpecification
 */
class TestAWTransactionSubscriberTransformationSpecification extends BaseSpecification {


    def assertType
    def scenarioNum
    def resultXML
    static def stubInstance
    static def scenarioInstance

    static def stubs = ['mainStub':new AWTransactionSubscriberStub(),
                        'discountStub':new AWTransactionSubscriberItemDiscountStub(),
                        'transactionDiscountStub':new AWTransactionSubscriberTransactionDiscountStub(),
                        'returnReasonStub':new AWTransactionSubscriberReturnReasonStub()]

    static def scenarios = ['mainScenario':new AWTransactionSubscriberScenarios(),
                            'discountScenario':new AWTransactionSubscriberItemDiscountScenarios(),
                            'transactionDiscountScenario':new AWTransactionSubscriberTransactionDiscountScenarios(),
                            'returnReasonScenario':new AWTransactionSubscriberReturnReasonScenarios()]

    @Unroll
    def "AW Transaction Subscriber Validate XML Node Values"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)
            groovy.util.Node body = p.parseText stubs[stubInstance].getXml()

        when: "Set XML Node to known Value"
            InputBean scenario = scenarios[scenarioInstance].specList[scenarioNum]
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
            assertType                | scenarioNum     | stubInstance                      | scenarioInstance

			// basic tests for header information
            ASSERT_TYPE_XML            | 0              | 'mainStub'                          | 'mainScenario'
            ASSERT_TYPE_XML            | 1              | 'mainStub'                            | 'mainScenario'
            ASSERT_TYPE_XML            | 2              | 'mainStub'                            | 'mainScenario'
            ASSERT_TYPE_XML            | 3              | 'mainStub'                            | 'mainScenario'
            ASSERT_TYPE_XML            | 4              | 'mainStub'                            | 'mainScenario'
            ASSERT_TYPE_XML            | 5              | 'mainStub'                            | 'mainScenario'

            // tests specifically for the LineItem/Sale/RetailPriceModifier discount codes
            ASSERT_TYPE_XML            | 0              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 1              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 2              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 3              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 4              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 5              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 6              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 7              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 8              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 9              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 10              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 11              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 12              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 13              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 14              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 15              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 16              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 17              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 18              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 19              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 20              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 21              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 22              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 23              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 24              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 25              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 26              | 'discountStub'                      | 'discountScenario'
            ASSERT_TYPE_XML            | 27              | 'discountStub'                      | 'discountScenario'


        // tests for the /LineItem/Discount codes
//            ASSERT_TYPE_XML            | 0              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 1              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 2              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 3              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 4              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 5              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
//            ASSERT_TYPE_XML            | 6              | 'transactionDiscountStub'            | 'transactionDiscountScenario'
            //ASSERT_TYPE_XML          | 7              | 'transactionDiscountStub'          | 'transactionDiscountScenario'

        // tests for the /Return/Reason codes
            ASSERT_TYPE_XML            | 0              | 'returnReasonStub'                  | 'returnReasonScenario'
            ASSERT_TYPE_XML            | 1              | 'returnReasonStub'                  | 'returnReasonScenario'



    }
}

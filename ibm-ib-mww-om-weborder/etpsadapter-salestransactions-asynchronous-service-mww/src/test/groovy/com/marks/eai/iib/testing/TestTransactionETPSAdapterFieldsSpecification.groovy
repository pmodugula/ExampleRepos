/**
 * Created by Rhett Whaley (rhett.whaley@ness.com)
 */
package com.marks.eai.iib.testing

import com.marks.eai.iib.testing.stub.ETPSAdapterStub
import groovy.xml.XmlUtil
import spock.lang.Unroll
import com.marks.eai.iib.testing.scenarios.ETPSAdapterScenarios as SCENARIO
import com.marks.eai.iib.testing.scenarios.InputBean as S

/**
 * TestAsnPublisherTransformationSpecification
 */
class TestTransactionETPSAdapterFieldsSpecification extends BaseSpecification {

    /**
     * NOTE:
     * The Following Testing Scaffold is ONLY to be used to test single value input to single value output equality
     * testing.  Used to test any node value input-to-mapping output testing
     */


	def scenarioNum
	def response
    def pubQ
    S scenario
    def description

    @Unroll
    def "Transaction eTPS Adapter Validate XML Node Values via XPath - scenario# #scenarioNum"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)
            groovy.util.Node body = p.parseText ETPSAdapterStub.getXml("I")

        when: "Set XML Node to known Value"
            scenario = SCENARIO.specList[scenarioNum]
            description = scenario?.description
            println "Executing Scenario ${scenarioNum}"
            println "\tJIRA ID: ${scenario?.jiraID}"
            println "\tDESCRIPTION: ${scenario?.description}"

            scenario.getSourceXpaths().each { sourceXpath, sourceValue  -> body = setNodeValue(body, sourceXpath, sourceValue) }

        and: "Set Message routing expectations and publish XML Message to Flow Entry Endpoint"
            resultEndpoint.reset()
            resultEndpoint.setExpectedMessageCount 1

            println "SOURCE INPUT BODY (scenario#: $scenarioNum) : " + XmlUtil.serialize(body)
            response = producer.requestBodyAndHeader("jms:$pubQ?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=80000",XmlUtil.serialize(body), 'TestingHeader', 'True', String.class)


        then:
            //resultEndpoint.assertIsSatisfied()

			println "RESULT OUTPUT BODY (scenario#: $scenarioNum): ${XmlUtil.serialize(response)}"
            scenario.getTargetXpaths().each{ targetXpath, targetValue -> assertNodeValue(response, targetXpath, targetValue); }

        where:
            pubQ  										 | scenarioNum
			'RECV.MWW.ETPSADAPTER.SALES_IN.IBM_IB.CI' 	 | 0
           // 'RECV.MWW.ETPSADAPTER.SALES_IN.IBM_IB.CI' 	 | 1



	}
}

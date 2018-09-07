/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.testing.ib.employee

import com.fgl.stub.payload.employee.EmployeeSubscriberStub
import com.fgl.testing.ib.BaseSpecification
import groovy.xml.XmlUtil
import org.custommonkey.xmlunit.*
import spock.lang.Unroll

import static org.custommonkey.xmlunit.XMLAssert.*

import com.fgl.testing.ib.employee.transform.scenarios.EmployeeSubsriberScenarios as SCENARIO
import com.fgl.testing.ib.employee.transform.scenarios.InputBean as S

/**
 * TestEmployeeSubscriberTransformationSpecification
 */
class TestEmployeeSubscriberTransformationSpecification extends BaseSpecification {

    /**
     * NOTE:
     * The Following Testing Scaffold is ONLY to be used to test single value input to single value output equality
     * testing.  Used to test any node value input-to-mapping output testing
     */
    @Unroll
    def "Employee Subscriber Validate XML Node Values"() {
        setup:
            XmlParser p = new XmlParser()
            p.setFeature("http://xml.org/sax/features/namespaces", false)
            groovy.util.Node body = p.parseText EmployeeSubscriberStub.getXml("I")
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
            if(ASSERT_TYPE_TEXT){
                assertOutputContains(scenario.getTargetStrings(), resultXml)
            }
            else if (ASSERT_TYPE_XML){
                scenario.getTargetXpaths().each{ targetXpath, targetValue -> assertNodeValue(resultXml, targetXpath, targetValue); }
            }

        where:
            pubQ  								     | rcvQ  								   | assertType       | scenarioNum
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 0
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 1
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 2
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 3
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 4
			'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 5
            'FGL.CRM.STORE.EMPLOYEE.IBM_IB.DV' 	 	 | 'FGL.CRM.STORE.EMPLOYEE.IBM_IB.TEST.DV' | ASSERT_TYPE_TEXT | 6
			
	}
}

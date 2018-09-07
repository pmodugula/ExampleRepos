package com.marks.eai.iib.testing

import groovy.xml.XmlUtil
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit
import spock.lang.Unroll

/**
 * Transaction integration test which uses the Spock framework (https://code.google.com/p/spock/) to test different transaction scenarios for trx-sales-banking-subscriber IIB component
 * @author wrhett
 *
 */
class TestTransactionETPSAdapterFullXMLSpecification extends BaseSpecification{

	// used by each test
	String transactionNumber
	def jira
	def scenarioDesc
	def scenarioNum
	def iibInputXMLFile
	def iibInputXMLFileFull
	def iibOutputFile
	def iibOutputFileFull
    def response
	def fulfilmentStore
    String inputQueue = "RECV.MWW.ETPSADAPTER.SALES_IN.IBM_IB.CI"

	@Unroll("Testing JIRA #jira - scenario #scenarioNum - #scenarioDesc - output matches expected file after proccessing through IIB with input file #iibInputXMLFile")
	def "Read XML from IIB response and assert the data is valid"(){

		setup:

        // input and expected result files are within this project
        iibInputXMLFileFull = "./src/test/resources/data/transaction/iib_input/${iibInputXMLFile}"
		iibOutputFileFull = "./src/test/resources/data/transaction/iib_output/${iibInputXMLFile}"

        // reserverd for these tests so we can have a consistent transaction number produced
		DBHelper.cleanSequenceTable(999)
        DBHelper.cleanSequenceTable(997)

		when: "A message is put on the IIB input queue"

			// read the iib input file
			String inputMessage = XmlUtil.serialize(new XmlSlurper().parse(iibInputXMLFileFull))
			println("Putting a message on the queue")
            // send the message to IIB with the test header set and a temporary reply queue created so IIB can response back
            response = producer.requestBodyAndHeader("jms:$inputQueue?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=90000&asyncConsumer=true&asyncStartListener=true&replyToType=Shared",inputMessage, 'TestingHeader', 'True', String.class)

		then: "Message on reply queue matches expected XML results"

            // parse the string response
            def outputXML = new XmlParser().parseText(response)
            println("Raw XML response output ${response}")

            // parse the expected output contained in the file
			def expectedStr = new File(iibOutputFileFull).text
			def expectedXML = XmlUtil.serialize(new XmlParser().parseText(expectedStr))
			
			println("Raw Expected XML output ${expectedXML}")

            // set XMLUnit props
			XMLUnit.setIgnoreWhitespace(true)
			XMLUnit.setIgnoreComments(true)
			XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true)
			XMLUnit.setNormalizeWhitespace(true)

            // compare the 2 XML strings
			Diff diff = new Diff(expectedXML, XmlUtil.serialize(outputXML));
			assert(diff.identical());
			

		where:
			// Spock data table, each row becomes a new test
			jira		        |	scenarioNum | scenarioDesc									|	iibInputXMLFile		                     | fulfilmentStore


            // Fast Find Fulfilments

			"POE-eTPS-adapter"	|		"2"		| "FF fulfilment legacy message with SK tax"	|	"FF_fulfilment_2607327_SK.xml"		    | 999
            "POE-eTPS-adapter"	|		"4"		| "FF fulfilment legacy message with BC tax"	|	"FF_fulfilment_2615090_BC.xml"		    | 999
            "POE-eTPS-adapter"	|		"5"		| "FF fulfilment legacy message"	            |	"FF_fulfilment_2611109.xml"	            | 999

            // qUniform Fulfilments
             "POE-eTPS-adapter"	|		"6"		| "IONS fulfilment legacy message"	|	"IONS_fulfilment_1.xml"		                         | 997


	}

}

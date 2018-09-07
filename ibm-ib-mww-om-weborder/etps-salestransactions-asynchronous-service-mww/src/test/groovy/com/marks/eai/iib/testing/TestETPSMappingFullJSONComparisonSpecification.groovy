package com.marks.eai.iib.testing

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.xml.XmlUtil
import spock.lang.Unroll
import org.skyscreamer.jsonassert.JSONAssert

/**
 * Transaction integration test which uses the Spock framework (https://code.google.com/p/spock/) to test different transaction scenarios for trx-sales-etps IIB component
 * @author wrhett
 *
 */
class TestETPSMappingFullJSONComparisonSpecification extends BaseSpecification {

    // used by each test
    String transactionNumber
    def jira
    def scenarioDesc
    def scenarioNum
    def iibInputFile
    def iibInputFileFull
    def iibOutputFile
    def iibOutputFileFull
    def response
    def strictMode = false
    String inputQueue = "RECV.MWW.ETPS.SALES_IN.IBM_IB.CI"

    @Unroll("Testing JIRA #jira - scenario #scenarioNum - #scenarioDesc - Transaction number #transactionNumber in eTPS output matches expected file after proccessing through IIB with input file #iibInputFileFull")
    def "Read XML from IIB response and assert the data is valid"() {

        setup:


        when: "A message is put on the IIB input queue"

            // read the iib input file
            String inputMessage = XmlUtil.serialize(new XmlSlurper().parse(iibInputFileFull))
            println("Putting a message on the queue")
            // send the message to IIB with the test header set and a temporary reply queue created so IIB can response back
            response = producer.requestBodyAndHeader("jms:$inputQueue?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=40000&asyncConsumer=true&asyncStartListener=true&replyToType=Shared", inputMessage, 'TestingHeader', 'True', String.class)

        then: "Message on reply queue matches expected results"

            println("Raw JSON response output ${response}")

            // explicitly set any tenders.successfulPedTransactionUuid AND allPedTransactions.uuid to 12345
            def response = JSONHelper.setUUIDs(response)

            def expected = new File(iibOutputFileFull).text
            JSONAssert.assertEquals(expected, response, strictMode);





        where:
            // Spock data table, each row becomes a new test
            jira     | scenarioNum | scenarioDesc                       	| transactionNumber 			| iibInputFileFull                                            					 | iibOutputFileFull                                                                                      | strictMode

            "POE-27" | "1"         | "Simple sale"                     		 | "271000"         			 | "./source/trx-sales-arts-samples/iib_input/POE-27-1.xml"   					  | "./src/test/resources/data/transaction/iib_output/POE-27-1.json"                                      |  false

            "POE-??" | "2"         | "Sample output from legacy FF"     		| "100418"        			  | "./src/test/resources/data/transaction/iib_input/100418.xml"				 | "./src/test/resources/data/transaction/iib_output/100418.json"                                         |  false

            "POE-??" | "3"         | "Sample output from legacy FF new" 		| "100243"         				 | "./src/test/resources/data/transaction/iib_input/100243.xml"				 | "./src/test/resources/data/transaction/iib_output/100243.json"                                         |  false

            // this one has credit payments so the generated UUID makes it tricky
            "POE-??" | "4"         | "sample eTPS input with US Cash"   		| "100938"         				 | "./src/test/resources/data/transaction/iib_input/100938.xml"				 | "./src/test/resources/data/transaction/iib_output/100938.json"                                         |  false

			"POE-??" | "5"         | "Sample output from legacy FF " 			| "0136120160222100234"          | "./src/test/resources/data/transaction/iib_input/0136120160222100234.xml" | "./src/test/resources/data/transaction/iib_output/0136120160222100234.json"                            |  false

            "POE-??" | "6"         | "IONS test" 			                    | "9976220151029100000"          | "./src/test/resources/data/transaction/iib_input/IONS_fulfilment_1.xml"   | "./src/test/resources/data/transaction/iib_output/IONS_fulfilment_1.json"                              |  true

            "POE-??" | "7"         | "AX test" 			                        | "100004"                     | "./src/test/resources/data/transaction/iib_input/AX54_IIB_ARTS.xml"         | "./src/test/resources/data/transaction/iib_output/AX54_IIB_ARTS.json"                                  |  true

            "POE-??" | "8"         | "AX test with no customer name"            | "100034"                     | "./src/test/resources/data/transaction/iib_input/AX_test_missing_cust_name.xml"         | "./src/test/resources/data/transaction/iib_output/AX_test_missing_cust_name.json"          |  true

            "POE-??" | "9"         | "Prod from OM-instore to eTPS "            | "100143"                     | "./src/test/resources/data/transaction/iib_input/eTPS_prod_0425_100143.xml"         | "./src/test/resources/data/transaction/iib_output/eTPS_prod_0425_100143.json"                 |  true

            "POE-??" | "10"         | "AX test with line tax exemption "        | "100004"                     | "./src/test/resources/data/transaction/iib_input/AX_line_tax_exemption.xml"         | "./src/test/resources/data/transaction/iib_output/AX_line_tax_exemption.json"                             |  true

            "POE-??" | "11"         | "AX test with txn tax exemption "         | "13301"                     | "./src/test/resources/data/transaction/iib_input/AX_txn_tax_exemption.xml"         | "./src/test/resources/data/transaction/iib_output/AX_txn_tax_exemption.json"                             |  true

            "POE-??" | "12"         | "AX test with multiple address lines"     | "100005"                     | "./src/test/resources/data/transaction/iib_input/AX54_IIB_ARTS_multiple_address_lines.xml"         | "./src/test/resources/data/transaction/iib_output/AX54_IIB_ARTS_multiple_address_lines.json"                             |  true

            "EL-232" | "1"         | "LoyaltyRedemption TPSIn"          | "326534"                     | "./src/test/resources/data/transaction/iib_input/EL-232-1.xml"         | "./src/test/resources/data/transaction/iib_output/EL-232-1.json"                             |  true
            "EL-232" | "2"         | "LoyaltyRedemption CashIn"         | "320838"                     | "./src/test/resources/data/transaction/iib_input/EL-232-2.xml"         | "./src/test/resources/data/transaction/iib_output/EL-232-2.json"                             |  true
            "EL-232" | "3"         | "LoyaltyRedemption CCeTPSIn"       | "320838"                     | "./src/test/resources/data/transaction/iib_input/EL-232-3.xml"         | "./src/test/resources/data/transaction/iib_output/EL-232-3.json"                             |  true
            "EL-232" | "4"         | "LoyaltyRedemption SucceseTPSIn"   | "320838"                     | "./src/test/resources/data/transaction/iib_input/EL-232-4.xml"         | "./src/test/resources/data/transaction/iib_output/EL-232-4.json"                             |  true

        // TODO change, other tenders
    }

}

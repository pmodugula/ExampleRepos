package com.fgl.eai.iib.testing

import groovy.xml.XmlUtil
import junit.framework.Assert
import org.apache.camel.ConsumerTemplate
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.diff.Diff
import org.xmlunit.diff.Difference
import org.xmlunit.util.Nodes
import org.xmlunit.util.Predicate
import spock.lang.Unroll

/**
 * Transaction integration test which uses the Spock framework (https://code.google.com/p/spock/) to test different transaction scenarios for trx-sales-aw-subscriber-fgl IIB component
 * Specifically puts sample ARTS XML messages on the input queue and checked response (XPOLD-like XML format) which is returned from IIB
 * @author wrhett
 *
 */
class TestAWTransactionSubscriberSpecification extends BaseSpecification {

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

    @Unroll("Testing JIRA #jira - scenario #scenarioNum - #scenarioDesc - Transaction number #transactionNumber in trx-sales-aw-subscriber-fgl output matches expected file #iibInputXMLFile")
    def "Read XML from IIB response and assert the data is valid"() {

        setup:

        // input and expected result files are within this project
        iibInputXMLFileFull = "./source/trx-sales-all-samples-fgl/arts/${iibInputXMLFile}"
        iibOutputFileFull = "./source/trx-sales-all-samples-fgl/xpolld/xml/${iibOutputFile}"

        when: "A message is put on the IIB input queue"

        // read the iib input file
        String inputMessage = XmlUtil.serialize(new XmlSlurper().parse(iibInputXMLFileFull))
        println("Putting a message on the queue")
        // send the message to IIB with the test header set and a temporary reply queue created so IIB can response back
        response = producer.requestBodyAndHeader("jms:$inputQueue?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=90000&asyncConsumer=true&asyncStartListener=true&replyToType=Shared", inputMessage, 'TestingHeader', 'True', String.class)

        then: "Message on reply queue matches expected XML results"

        println("Raw XML response output ${response}")

        if (!response)
            throw new Exception("No response from IIB")

        // parse the expected output contained in the file
        def expectedStr = new File(iibOutputFileFull).text

        // ignore all these elements which have dynamically generated keys
        def elementsToIgnore = []

        // construct a XMLUnit2 Diff using the DiffBuilder
        final Diff documentDiff = DiffBuilder
                .compare(expectedStr)
                .withTest(response)

// filter not required here unless you have dynamically generated elements
//                .withNodeFilter(new Predicate<Node>() {
//                    @Override
//                    public boolean test(Node n) {
//
//                        boolean result = !(n instanceof Element && elementsToIgnore.contains(Nodes.getQName(n).getLocalPart()));
//                        return result;
//                    }
//                })
                .ignoreWhitespace()
                .build();

        // compare the 2 XML strings using the Diff object
         Assert.assertFalse(documentDiff.toString(), documentDiff.hasDifferences());

        where:
        // Spock data table, each row becomes a new test
        jira                | scenarioNum  | scenarioDesc                                      | transactionNumber           | iibInputXMLFile                             | iibOutputFile

          "FPOE-3857"       | "1"         | "Slice 1: Basic Sale"                              | "000004"                    | 'FPOE-3857-1.xml'                           | 'FPOE-3857-1.xml'

          "FPOE-6237"       | "1"         | "Slice 1a: Basic Sale + gift card item included"   | "000019"                    | 'FPOE-6237-1.xml'                           | 'FPOE-6237-1.xml'
          "FPOE-6237"       | "2"         | "Slice 1a: Basic Sale + 2 tax lines GST PST"       | "000008"                    | 'FPOE-6237-2.xml'                           | 'FPOE-6237-2.xml'

          "FPOE-2203"       | "1"         | "Slice 2: Control Transaction - Login"             | "000074"                    | 'FPOE-2203-1-Login.xml'                     | 'FPOE-2203-1.xml'
          "FPOE-2203"       | "2"         | "Slice 2: Control Transaction - Logout"            | "000077"                    | 'FPOE-2203-2-Logout.xml'                    | 'FPOE-2203-2.xml'
          "FPOE-2203"       | "3"         | "Slice 2: Control Transaction - No Sale"           | "000068"                    | 'FPOE-2203-3-No_sale.xml'                   | 'FPOE-2203-3.xml'
          "FPOE-2203"       | "4"         | "Slice 2: Control Transaction - EOD"               | "1"                         | 'FPOE-2203-4-EOD.xml'                       | 'FPOE-2203-4.xml'
          "FPOE-2203"       | "5"         | "Slice 2: Control Transaction - Void"              | "000065"                    | 'FPOE-2203-5-Void.xml'                      | 'FPOE-2203-5.xml'
          "FPOE-2203"       | "6"         | "Slice 2: Control Transaction - Unsettled"         | "000136"                    | 'FPOE-2203-6-Unsettled.xml'                 | 'FPOE-2203-6.xml'
          "FPOE-2203"       | "7"         | "Slice 2: Control Transaction - X Read"            | "000079"                    | 'FPOE-2203-7-X_Read.xml'                    | 'FPOE-2203-7.xml'
          "FPOE-2203"       | "8"         | "Slice 2: Control Transaction - Z Read"            | "000084"                    | 'FPOE-2203-8-Z_Read.xml'                    | 'FPOE-2203-8.xml'

          "FPOE-2204"       | "1"         | "Slice 3: Tender Credit"                           | "000249"                    | 'FPOE-2204-1-Credit.xml'                    | 'FPOE-2204-1.xml'
          "FPOE-2204"       | "2"         | "Slice 3: Tender Debit"                            | "000248"                    | 'FPOE-2204-2-Debit.xml'                     | 'FPOE-2204-2.xml'
          "FPOE-2204"       | "3"         | "Slice 3: Tender Gift Cert"                        | "000041"                    | 'FPOE-2204-3-GiftCert.xml'                  | 'FPOE-2204-3.xml'
          "FPOE-2204"       | "4"         | "Slice 3: Tender Gift Card"                        | "000026"                    | 'FPOE-2204-4-Gift_card.xml'                 | 'FPOE-2204-4.xml'
          "FPOE-2204"       | "5"         | "Slice 3: Tender Scene"                            | "000025"                    | 'FPOE-2204-5-Scene.xml'                     | 'FPOE-2204-5.xml'
          "FPOE-2204"       | "6"         | "Slice 3: Tender Store Promo GC"                   | "000024"                    | 'FPOE-2204-6-StorePromoGC.xml'              | 'FPOE-2204-6.xml'
          "FPOE-2204"       | "7"         | "Slice 3: Tender US"                               | "000043"                    | 'FPOE-2204-7-US.xml'                        | 'FPOE-2204-7.xml'
          "FPOE-2204"       | "8"         | "Slice 3: Tender Vendor Coupon"                    | "000042"                    | 'FPOE-2204-8-VendorCoupon.xml'              | 'FPOE-2204-8.xml'

          "FPOE-6330"       | "1"         | "Slice 4: Manual Iem Level Price Override"                                       | "000169"                    | 'FPOE-6330-1-Manual-Price-OverRide.xml'                                                | 'FPOE-6330-1.xml'
          "FPOE-6330"       | "2"         | "Slice 4: Transaction Level Price Matching"                                      | "000020"                    | 'FPOE-6330-2-Transaction-Discount-Price-Matching.xml'                                  | 'FPOE-6330-2.xml'
          "FPOE-6330"       | "3"         | "Slice 4: Coupon Subtotal Discount"                                              | "8980"                      | 'FPOE-6330-3-COUPON-SubTotal-Discount.xml'                                             | 'FPOE-6330-3.xml'
          "FPOE-6330"       | "4"         | "Slice 4: Competitor Name Note Type SubTotal Discount with Approval"             | "8980"                      | 'FPOE-6330-4-Competitor Name Note Type SubTotal Discount with Approval.xml'            | 'FPOE-6330-4.xml'
          "FPOE-6330"       | "5"         | "Slice 4: DealGroup Discount with Data"                                          | "2677"                      | 'FPOE-6330-5-DealGroup Discount with Data.xml'                                         | 'FPOE-6330-5.xml'
          "FPOE-6330"       | "6"         | "Slice 4: Manual Item Discount with Approval with Data"                          | "8980"                      | 'FPOE-6330-6-Manual Item Discount with Approval with Data.xml'                         | 'FPOE-6330-6.xml'
          "FPOE-6330"       | "7"         | "Slice 4: Manual Item Discount with Data"                                        | "8980"                      | 'FPOE-6330-7-Manual Item Discount with Data.xml'                                       | 'FPOE-6330-7.xml'
          "FPOE-6330"       | "8"         | "Slice 4: Manual Item Mark Up with Approval with Data"                           | "8980"                      | 'FPOE-6330-8-Manual Item Mark Up with Approval with Data.xml'                          | 'FPOE-6330-8.xml'
          "FPOE-6330"       | "9"         | "Slice 4: SubTotal Discount with Approval"                                       | "8980"                      | 'FPOE-6330-9-SubTotal Discount with Approval.xml'                                      | 'FPOE-6330-9.xml'
          "FPOE-6330"       | "10"        | "Slice 4: SubTotal Discount with Data"                                           | "8980"                      | 'FPOE-6330-10-SubTotal Discount with Data.xml'                                         | 'FPOE-6330-10.xml'
          "FPOE-6330"       | "11"        | "Slice 4: Discount BOGO plus Temporary Price with data"                          | "2741"                      | 'FPOE-6330-11-Discount BOGO plus Temporary Price with data.xml'                        | 'FPOE-6330-11.xml'
          "FPOE-6330"       | "12"        | "Slice 4: Discount BOGO Set Price with data"                                     | "2741"                      | 'FPOE-6330-12-Discount BOGO Set Price with data.xml'                                   | 'FPOE-6330-12.xml'
          "FPOE-6330"       | "13"        | "Slice 4: Discount BOGO with data"                                               | "2618"                      | 'FPOE-6330-13-Discount BOGO with data.xml'                                             | 'FPOE-6330-13.xml'

          "FPOE-6331"       | "1"         | "Slice 5: Federal Tax Exemption"                                                      | "000046"                    | 'FPOE-6331-1-Federal-Tax-exempt.xml'                                                      | 'FPOE-6331-1.xml'
          "FPOE-6331"       | "2"         | "Slice 5: Tax Override with data"                                                     | "2517"                      | 'FPOE-6331-2-Tax-Override-with-data.xml'                                                  | 'FPOE-6331-2.xml'
          "FPOE-6331"       | "3"         | "Slice 5: Exception Tax Override with data"                                           | "2517"                      | 'FPOE-6331-3-Exception-Tax-Override-with-data.xml'                                        | 'FPOE-6331-3.xml'
          "FPOE-6331"       | "4"         | "Slice 5: Item and Transaction Tax Exemptions with data"                              | "2504"                      | 'FPOE-6331-4-Item and Transaction Tax Exemptions with data.xml'                           | 'FPOE-6331-4.xml'
          "FPOE-6331"       | "5"         | "Slice 5: Manual GovernmentTax Exemption with data"                                   | "2504"                      | 'FPOE-6331-5-Manual GovernmentTax Exemption with data.xml'                                | 'FPOE-6331-5.xml'
          "FPOE-6331"       | "6"         | "Slice 5: Manual Native Tax Exemption Shipping Ref Number with data"                  | "2504"                      | 'FPOE-6331-6-Manual Native Tax Exemption Shipping Ref Number with data.xml'               | 'FPOE-6331-6.xml'
          "FPOE-6331"       | "7"         | "Slice 5: Manual Native Tax Exemption with data"                                      | "2504"                      | 'FPOE-6331-7-Manual Native Tax Exemption with data.xml'                                   | 'FPOE-6331-7.xml'
          "FPOE-6331"       | "8"         | "Slice 5: Manual Non Native Tax Exemption with data"                                  | "2504"                      | 'FPOE-6331-8-Manual Non Native Tax Exemption with data.xml'                               | 'FPOE-6331-8.xml'
          "FPOE-6331"       | "9"         | "Slice 5: Multiple Tax Exemptions both System and Manual"                             | "8937"                      | 'FPOE-6331-9-Multiple Tax Exemptions both System and Manual.xml'                          | 'FPOE-6331-9.xml'
          "FPOE-6331"       | "10"        | "Slice 5: Multiple TransactionTax Exemptions Federal and Provincial with data"        | "2504"                      | 'FPOE-6331-10-Multiple TransactionTax Exemptions Federal and Provincial with data.xml'    | 'FPOE-6331-10.xml'
          "FPOE-6331"       | "11"        | "Slice 5: System Item Tax Exemption with data"                                        | "8937"                      | 'FPOE-6331-11-System Item Tax Exemption with data.xml'                                    | 'FPOE-6331-11.xml'
          "FPOE-6331"       | "12"        | "Slice 5: Two Items One System Tax Exempt One Not with data"                          | "8937"                      | 'FPOE-6331-12-Two Items One System Tax Exempt One Not with data.xml'                      | 'FPOE-6331-12.xml'

          "FPOE-6332"       | "1"         | "Slice 6: LineVoid with data"                      | "1826"                      | 'FPOE-6332-1-LineVoid-with-data.xml'        | 'FPOE-6332-1.xml'

          "FPOE-6333"       | "1"         | "Slice 7: Receipted Return"                        | "000035"                    | 'FPOE-6333-1-Receipted-Return.xml'        | 'FPOE-6333-1.xml'
          "FPOE-6333"       | "2"         | "Slice 7: Return Exception"                        | "000035"                    | 'FPOE-6333-2-ReturnException.xml'         | 'FPOE-6333-2.xml'

          "FPOE-6334"       | "1"         | "Slice 8: Employee Sale with Data"                 | "8980"                      | 'FPOE-6334-1-Employee Sale with Data.xml'                    | 'FPOE-6334-1.xml'
          "FPOE-6334"       | "2"         | "Slice 8: Employee Sale and Return with data"      | "2522"                      | 'FPOE-6334-2-Employee Sale and Return with data.xml'         | 'FPOE-6334-2.xml'

          "FPOE-6335"       | "1"         | "Slice 9: Serial Number Note Type with data"                               | "1826"           | 'FPOE-6335-1-Serial Number Note Type with data.xml'                                 | 'FPOE-6335-1.xml'
          "FPOE-6335"       | "2"         | "Slice 9: SubTotal Discount with Approval"                                 | "8980"           | 'FPOE-6335-2-SubTotal Discount with Approval.xml'                                   | 'FPOE-6335-2.xml'
          "FPOE-6335"       | "3"         | "Slice 9: SCENE Points Earned with data"                                   | "1826"           | 'FPOE-6335-3-SCENE Points Earned with data.xml'                                     | 'FPOE-6335-3.xml'
          "FPOE-6335"       | "4"         | "Slice 9: Non Receipted Return with data"                                  | "2522"           | 'FPOE-6335-4-Non Receipted Return with data.xml'                                    | 'FPOE-6335-4.xml'
          "FPOE-6335"       | "5"         | "Slice 9: Manual Item Discount with Approval with Data"                    | "8980"           | 'FPOE-6335-5-Manual Item Discount with Approval with Data.xml'                      | 'FPOE-6335-5.xml'
          "FPOE-6335"       | "6"         | "Slice 9: Control TransactionVoid with data"                               | "1826"           | 'FPOE-6335-6-Control TransactionVoid with data.xml'                                 | 'FPOE-6335-6.xml'
          "FPOE-6335"       | "7"         | "Slice 9: Control NoSale with data"                                        | "1826"           | 'FPOE-6335-7-Control NoSale with data.xml'                                          | 'FPOE-6335-7.xml'
          "FPOE-6335"       | "8"         | "Slice 9: Competitor Name Note Type with Data"                             | "8980"           | 'FPOE-6335-8-Competitor Name Note Type with Data.xml'                               | 'FPOE-6335-8.xml'
          "FPOE-6335"       | "9"         | "Slice 9: Competitor Name Note Type SubTotal Discount with Approval"       | "8980"           | 'FPOE-6335-9-Competitor Name Note Type SubTotal Discount with Approval.xml'         | 'FPOE-6335-9.xml'

          "FPOE-6336"       | "1"         | "Slice 10: Repair_taxable"                                                 | "000460"         | 'FPOE-6336-1-Repair_taxable.xml'                                                    | 'FPOE-6336-1.xml'
          "FPOE-6336"       | "2"         | "Slice 10: Non Merch Taxable with data"                                    | "2080"           | 'FPOE-6336-2-Non Merch Taxable with data.xml'                                       | 'FPOE-6336-2.xml'
          "FPOE-6336"       | "3"         | "Slice 10: Admin SuspendedTransaction"                                     | "000438"         | 'FPOE-6336-3-Admin - SuspendedTransaction.xml'                                      | 'FPOE-6336-3.xml'

          "FPOE-6337"       | "1"         | "Slice 11: Charge Sale with data"                                          | "2082"           | 'FPOE-6337-1-Charge Sale with data.xml'                                             | 'FPOE-6337-1.xml'
          "FPOE-6337"       | "2"         | "Slice 11: Receipted Return to Charge Tender with data"                    | "2422"           | 'FPOE-6337-2-Receipted Return to Charge Tender with data.xml'                       | 'FPOE-6337-2.xml'

          "FPOE-6338"       | "1"         | "Slice 12: PayIn TenderControlTransacation with data"                      | "2069"           | 'FPOE-6338-1-PayIn TenderControlTransacation with data.xml'                         | 'FPOE-6338-1.xml'
          "FPOE-6338"       | "2"         | "Slice 12: PayOut TenderControlTransacation with data"                     | "2073"           | 'FPOE-6338-2-PayOut TenderControlTransaction with data.xml'                         | 'FPOE-6338-2.xml'

          "FPOE-6339"       | "1"         | "Slice 13: Slice_13a_earn"                                                 | "000015"         | 'FPOE-6339-1-Slice_13a_earn.xml'                                                    | 'FPOE-6339-1.xml'
          "FPOE-6339"       | "2"         | "Slice 13: Slice_13b_Return_earn"                                          | "000032"         | 'FPOE-6339-2-Slice_13b_Return_earn.xml'                                             | 'FPOE-6339-2.xml'
          "FPOE-6339"       | "3"         | "Slice 13: Slice_13_Error"                                                 | "000032"         | 'FPOE-6339-3-Slice_13_Error.xml'                                                    | 'FPOE-6339-3.xml'

          "FPOE-6341"       | "1"         | "Slice 15: Tender Exchange Transaction with data"                          | "2096"           | 'FPOE-6341-1-Tender Exchange Transaction with data.xml'                             | 'FPOE-6341-1.xml'
		  "FPOE-20316"		| "1"		  | "Slice 9:  CTFS Credit/Debit response - Token"							   | "1003"			  | 'FPOE-20316-CreditDebit Visa Tendered with Token.xml'								| 'FPOE-20316.xml'

          "EL-167"          | "1"         | "EL-167-1-Control POSEOD with CTMONEY and SCENE"                           | "2096"           | 'EL-167-1-Control POSEOD with CTMONEY and SCENE.xml'                                | 'EL-167-1.xml'
          "EL-167"          | "2"         | "EL-167-2-SCENE Points Earned and Redemption on CTMONEY AND SCENE"         | "2096"           | 'EL-167-2-SCENE Points Earned and Redemption on CTMONEY AND SCENE.xml'              | 'EL-167-2.xml'
          "EL-167"          | "3"         | "EL-167-3-Return(Receipted)_Transaction_CTMONEY_ as_only_Tender"           | "2096"           | 'EL-167-3-Return(Receipted)_Transaction_CTMONEY_ as_only_Tender.xml'                | 'EL-167-3.xml'
          "EL-167"          | "4"         | "EL-167-4-Slice_13b_Return_SCENE"                                          | "2096"           | 'EL-167-4-Slice_13b_Return_SCENE.xml'                                               | 'EL-167-4.xml'
          "EL-167"          | "5"         | "EL-167-5-SCENE Points Decremented (return) Transaction"                   | "2096"           | 'EL-167-5-SCENE Points Decremented (return) Transaction.xml'                        | 'EL-167-5.xml'

    }

}

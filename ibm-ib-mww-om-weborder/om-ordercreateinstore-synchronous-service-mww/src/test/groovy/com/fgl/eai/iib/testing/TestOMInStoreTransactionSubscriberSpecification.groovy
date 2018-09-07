package com.fgl.eai.iib.testing

import groovy.xml.XmlUtil
import org.junit.Assert
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.util.Nodes
import org.xmlunit.util.Predicate
import spock.lang.Unroll
import org.xmlunit.diff.Diff



/**
 * Transaction integration test which uses the Spock framework (https://code.google.com/p/spock/) to test different transaction scenarios for trx-instoreordercreation-hyb-inbound-mww IIB component
 * Specifically puts sample ARTS CustomerOrder messages on the input queue and checks response which is returned from OM instore IIB inbound component
 * @author wrhett
 *
 */
class TestOMInStoreTransactionSubscriberSpecification extends BaseSpecification {

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

    @Unroll("Testing JIRA #jira - scenario #scenarioNum - #scenarioDesc - Transaction number #transactionNumber in trx-instoreordercreation-hyb-inbound-mww output matches expected file #iibInputXMLFile")
    def "Read XML from IIB response and assert the data is valid"() {

        setup:

        // input and expected result files are within this project
        iibInputXMLFileFull = "./source/trx-omstore-all-samples-fgl/artsCustInput/${iibInputXMLFile}"
        iibOutputFileFull = "./source/trx-omstore-all-samples-fgl/artsCustOutput/${iibOutputFile}"
        when: "A message is put on the IIB input queue"

        def rootxml=new XmlSlurper().parse(iibInputXMLFileFull);

        def p = rootxml.'**'.find{ it.name() == "CustomerOrderNumber" }
        println("Putting increamented value on xml ----- ${p}")
        String value1 = p.text();
        Random random=new Random();
        int num =  4000000+random.nextInt(2000000) ;
        String orderNum = String.format("%07d", num);
        String sourceOrderKy = orderNum;

        rootxml.'**'.find { if (it.CustomerOrderNumber==value1) {
            println("Putting increamented value on xml ----- ${it.name()}")
            it.CustomerOrderNumber = orderNum;
            println("Putting increamented value on xml ----- ${it.CustomerOrderNumber}")
            it.SourceOrderKey = sourceOrderKy;
        }
        }
        println("input  xml ----- ${XmlUtil.serialize(rootxml)}")
        String inputMessage= XmlUtil.serialize(rootxml)

        println("Putting a message on the queue")
        // send the message to IIB with the test header set and a temporary reply queue created so IIB can response back
        response = producer.requestBodyAndHeader("jms:$inputQueue?deliveryPersistent=false&exchangePattern=InOut&requestTimeout=90000&asyncConsumer=true&asyncStartListener=true&replyToType=Shared", inputMessage, 'TestingHeader', 'True', String.class)

        then: "Message on reply queue matches expected results"

        println("Raw response output ${response}")
        if (!response)
            throw new Exception("No response from IIB")


        def expectedStr = new File(iibOutputFileFull).text

        // ignore all these elements which have dynamically generated keys
        def elementsToIgnore = ['CustomerOrderNumber']

        // construct a XMLUnit2 Diff using the DiffBuilder
        final Diff documentDiff = DiffBuilder
                .compare(expectedStr)
                .withTest(response)

        ///filter not required here unless you have dynamically generated elements  filter not required here unless you have dynamically generated elements
                .withNodeFilter(new Predicate<Node>() {
            @Override
            public boolean test(Node n) {

               boolean result = !(n instanceof Element && elementsToIgnore.contains(Nodes.getQName(n).getLocalPart()));

                return result;
            }
       })
                .ignoreWhitespace()
                .build();



        // compare the 2 XML strings using the Diff object
        Assert.assertFalse(documentDiff.toString(), documentDiff.hasDifferences());

        where:
        // Spock data table, each row becomes a new test
        jira               | scenarioNum  |                 scenarioDesc                                | transactionNumber     | iibInputXMLFile                                                                 | iibOutputFile
        "EL-484"           |     "1"      |             "Validate Loyalty redeem"                       | "000004"              | 'Validate  Loyalty Redeem and CreditDebit Card Transaction MasterCard.xml'      | 'Validate  Loyalty Redeem and CreditDebit Card Transaction MasterCard_out.xml'
        "EL-484"           |     "2"      |             "Validate Loyalty redeem"                       | "000004"              | 'Validate  Loyalty Redeem and Cash Transaction.xml'                             | 'Validate  Loyalty Redeem and Cash Transaction_out.xml'
        "EL-484"           |     "3"      |             "Validate Loyalty redeem"                       | "000004"              | 'Validate  Loyalty Redeem and GiftCard Transaction.xml'                         | 'Validate  Loyalty Redeem and GiftCard Transaction_out.xml'
        "EL-484"           |     "4"      |             "Validate Loyalty redeem"                       | "000004"              | 'Validate  Loyalty Redeem Transaction with Single Loyalty Card.xml'             | 'Validate  Loyalty Redeem Transaction with Single Loyalty Card_out.xml'
        "EL-484"           |     "5"      |             "Validate Loyalty redeem"                       | "000004"              | 'Validate -Cash-GiftCard Transaction.xml'                                       | 'Validate -Cash-GiftCard Transaction_out.xml'

    }

}

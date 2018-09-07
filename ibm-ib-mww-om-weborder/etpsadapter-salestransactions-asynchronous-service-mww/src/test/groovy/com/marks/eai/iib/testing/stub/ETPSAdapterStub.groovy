/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.marks.eai.iib.testing.stub

import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil
import groovy.xml.MarkupBuilder


class ETPSAdapterStub {
    /**
     * Returns a stubbed version of the legacy FF fulfillment record
     * getXml
     * @return String of XML
     */
    public static String getXml(String crudOperation) {
		
    return """<submitTxnRequest>
    <store>999</store>
    <consumerid>FF</consumerid>
    <createddate>2016-01-10T14:27:48.138Z</createddate>
    <submitteddate>2016-01-10T14:27:48.138Z</submitteddate>
    <purchaseditems>
        <sku>400001629314</sku>
        <originalprice>34.99</originalprice>
        <lineitemdetail>
            <soldprice>34.99</soldprice>
            <federaltaxamount>1.75</federaltaxamount>
            <provincialtaxamount>0.00</provincialtaxamount>
        </lineitemdetail>
        <regionaltaxexemptid>WAIVED</regionaltaxexemptid>
    </purchaseditems>
    <purchaseditems>
        <sku>400003826124</sku>
        <originalprice>2.80</originalprice>
        <lineitemdetail>
            <soldprice>2.80</soldprice>
            <federaltaxamount>0.00</federaltaxamount>
            <provincialtaxamount>0.00</provincialtaxamount>
        </lineitemdetail>
    </purchaseditems>
    <netamount>34.99</netamount>
    <totalamount>39.54</totalamount>
    <gcpayments>
        <id>9028884584</id>
        <amount>39.54</amount>
        <comment>FF order 2888458 fulfillment transaction</comment>
    </gcpayments>
</submitTxnRequest>"""
	}
}
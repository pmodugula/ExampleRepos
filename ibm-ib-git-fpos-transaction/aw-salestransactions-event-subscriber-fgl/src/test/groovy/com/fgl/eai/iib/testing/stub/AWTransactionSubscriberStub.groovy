/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.eai.iib.testing.stub

class AWTransactionSubscriberStub {

    public static String getXml() {
		
    return """
<eai:Exchange MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.fgl.com/exchange/ file:///C:/Users/IULIIA_SHVETS/Desktop/EAI%20ARTS%20Message%20Schemas%20V1_21/arts/ARTSTransactionMessageV2_00_00.xsd">
    <eai:MessageRouting>
        <eai:Guid>2D5CCC7BDB6944B9AF7506CD142D2C08</eai:Guid>
        <eai:MessageId/>
        <eai:MessageType>PointToPoint</eai:MessageType>
        <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
        <eai:SbuNumber>3</eai:SbuNumber>
        <eai:SbuCode>FGL</eai:SbuCode>
        <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
        <eai:DataSourceCode>POE</eai:DataSourceCode>
        <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
        <eai:MessageIndex>1</eai:MessageIndex>
        <eai:MessageCount>1</eai:MessageCount>
        <eai:SubjectAreaRunName>salestransactions-280-20161004-040400</eai:SubjectAreaRunName>
    </eai:MessageRouting>
    <eai:Messages>
        <eai:Count>1</eai:Count>
        <eai:SourceSystems>
            <eai:Count>1</eai:Count>
            <eai:SourceSystem>
                <eai:Index>1</eai:Index>
                <eai:SbuNumber>3</eai:SbuNumber>
                <eai:SbuCode>FGL</eai:SbuCode>
                <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
                <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
                <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
            </eai:SourceSystem>
        </eai:SourceSystems>
        <eai:POSLog>
            <arts:Count>1</arts:Count>
            <arts:Transaction TypeCode="SaleTransaction">
                <arts:BusinessUnit>
                    <arts:Index>1</arts:Index>
                    <arts:BusinessUnitNumber>280</arts:BusinessUnitNumber>
                    <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
                    <arts:BusinessUnitShortName>SC FORT MCMURRAY</arts:BusinessUnitShortName>
                    <arts:BusinessUnitName>SC280</arts:BusinessUnitName>
                </arts:BusinessUnit>
                <arts:WorkstationID>101</arts:WorkstationID>
                <arts:SequenceNumber>000004</arts:SequenceNumber>
                <arts:BeginDateTime>2016-10-04T04:02:51-06:00</arts:BeginDateTime>
                <arts:EndDateTime>2016-10-04T04:03:01-06:00</arts:EndDateTime>
                <arts:OperatorID OperatorName="ishvets Cashier" LastName="Cashier" FirstName="ishvets">000002016</arts:OperatorID>
                <arts:RetailTransaction>
                    <arts:LineItemCount>3</arts:LineItemCount>
                    <arts:CustomerCount>0</arts:CustomerCount>
                    <arts:AssociateCount>0</arts:AssociateCount>
                    <arts:LineItem VoidFlag="false">
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>1</arts:SequenceNumber>
                        <arts:Sale TaxableFlag="true" ItemType="Stock">
                            <arts:TaxCount>1</arts:TaxCount>
                            <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                            <arts:Item>
                                <arts:ItemNumber>330613396</arts:ItemNumber>
                                <arts:ItemName>DEMO - NIKE DYMO2 FWY WOOD 92 00</arts:ItemName>
                                <arts:UPCNumber>440004353472</arts:UPCNumber>
                            </arts:Item>
                            <arts:MerchandiseHierarchy Level="4" ID="9232"/>
                            <arts:RegularSalesUnitPrice>89.00</arts:RegularSalesUnitPrice>
                            <arts:ActualSalesUnitPrice>89.00</arts:ActualSalesUnitPrice>
                            <arts:DiscountAmount>0.00</arts:DiscountAmount>
                            <arts:Quantity>1</arts:Quantity>
                            <arts:Tax TypeCode="Sale" TaxType="GST" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>1</arts:Index>
                                <arts:Amount>4.45</arts:Amount>
                                <arts:Percent>5</arts:Percent>
                            </arts:Tax>
                        </arts:Sale>
                        <arts:LineItemSubtypeCode>Sale</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>2</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Tax TypeCode="Sale" TaxType="GST">
                            <arts:Index>1</arts:Index>
                            <arts:Amount>4.45</arts:Amount>
                            <arts:Percent>5</arts:Percent>
                        </arts:Tax>
                        <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>3</arts:Index>
                        <arts:SequenceNumber>3</arts:SequenceNumber>
                        <arts:Tender TypeCode="Sale" TenderType="Cash" SubTenderType="CAD">
                            <arts:Amount>93.45</arts:Amount>
                        </arts:Tender>
                        <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:Total TotalType="TransactionGrossAmount">93.45</arts:Total>
                    <arts:Total TotalType="TransactionSubtotal">89.00</arts:Total>
                </arts:RetailTransaction>
                <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
            </arts:Transaction>
        </eai:POSLog>
    </eai:Messages>
</eai:Exchange>
"""
	}

    public static String getXmlNoSubTender() {

        return """
<eai:Exchange MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.fgl.com/exchange/ file:///C:/Users/IULIIA_SHVETS/Desktop/EAI%20ARTS%20Message%20Schemas%20V1_21/arts/ARTSTransactionMessageV2_00_00.xsd">
    <eai:MessageRouting>
        <eai:Guid>2D5CCC7BDB6944B9AF7506CD142D2C08</eai:Guid>
        <eai:MessageId/>
        <eai:MessageType>PointToPoint</eai:MessageType>
        <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
        <eai:SbuNumber>3</eai:SbuNumber>
        <eai:SbuCode>FGL</eai:SbuCode>
        <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
        <eai:DataSourceCode>POE</eai:DataSourceCode>
        <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
        <eai:MessageIndex>1</eai:MessageIndex>
        <eai:MessageCount>1</eai:MessageCount>
        <eai:SubjectAreaRunName>salestransactions-280-20161004-040400</eai:SubjectAreaRunName>
    </eai:MessageRouting>
    <eai:Messages>
        <eai:Count>1</eai:Count>
        <eai:SourceSystems>
            <eai:Count>1</eai:Count>
            <eai:SourceSystem>
                <eai:Index>1</eai:Index>
                <eai:SbuNumber>3</eai:SbuNumber>
                <eai:SbuCode>FGL</eai:SbuCode>
                <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
                <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
                <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
            </eai:SourceSystem>
        </eai:SourceSystems>
        <eai:POSLog>
            <arts:Count>1</arts:Count>
            <arts:Transaction TypeCode="SaleTransaction">
                <arts:BusinessUnit>
                    <arts:Index>1</arts:Index>
                    <arts:BusinessUnitNumber>280</arts:BusinessUnitNumber>
                    <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
                    <arts:BusinessUnitShortName>SC FORT MCMURRAY</arts:BusinessUnitShortName>
                    <arts:BusinessUnitName>SC280</arts:BusinessUnitName>
                </arts:BusinessUnit>
                <arts:WorkstationID>101</arts:WorkstationID>
                <arts:SequenceNumber>000004</arts:SequenceNumber>
                <arts:BeginDateTime>2016-10-04T04:02:51-06:00</arts:BeginDateTime>
                <arts:EndDateTime>2016-10-04T04:03:01-06:00</arts:EndDateTime>
                <arts:OperatorID OperatorName="ishvets Cashier" LastName="Cashier" FirstName="ishvets">000002016</arts:OperatorID>
                <arts:RetailTransaction>
                    <arts:LineItemCount>3</arts:LineItemCount>
                    <arts:CustomerCount>0</arts:CustomerCount>
                    <arts:AssociateCount>0</arts:AssociateCount>
                    <arts:LineItem VoidFlag="false">
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>1</arts:SequenceNumber>
                        <arts:Sale TaxableFlag="true" ItemType="Stock">
                            <arts:TaxCount>1</arts:TaxCount>
                            <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                            <arts:Item>
                                <arts:ItemNumber>330613396</arts:ItemNumber>
                                <arts:ItemName>DEMO - NIKE DYMO2 FWY WOOD 92 00</arts:ItemName>
                                <arts:UPCNumber>440004353472</arts:UPCNumber>
                            </arts:Item>
                            <arts:MerchandiseHierarchy Level="4" ID="9232"/>
                            <arts:RegularSalesUnitPrice>89.00</arts:RegularSalesUnitPrice>
                            <arts:ActualSalesUnitPrice>89.00</arts:ActualSalesUnitPrice>
                            <arts:DiscountAmount>0.00</arts:DiscountAmount>
                            <arts:Quantity>1</arts:Quantity>
                            <arts:Tax TypeCode="Sale" TaxType="GST" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>1</arts:Index>
                                <arts:Amount>4.45</arts:Amount>
                                <arts:Percent>5</arts:Percent>
                            </arts:Tax>
                        </arts:Sale>
                        <arts:LineItemSubtypeCode>Sale</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>2</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Tax TypeCode="Sale" TaxType="GST">
                            <arts:Index>1</arts:Index>
                            <arts:Amount>4.45</arts:Amount>
                            <arts:Percent>5</arts:Percent>
                        </arts:Tax>
                        <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>3</arts:Index>
                        <arts:SequenceNumber>3</arts:SequenceNumber>
                        <arts:Tender TypeCode="Sale" TenderType="CreditDebit">
                            <arts:Amount>93.45</arts:Amount>
                        </arts:Tender>
                        <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:Total TotalType="TransactionGrossAmount">93.45</arts:Total>
                    <arts:Total TotalType="TransactionSubtotal">89.00</arts:Total>
                </arts:RetailTransaction>
                <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
            </arts:Transaction>
        </eai:POSLog>
    </eai:Messages>
</eai:Exchange>
"""
    }

    public static String getXmlNoSale() {

        return """
       <eai:Exchange
MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>6225D04E3F7C4EAFAC3D9B0B8AB3DD88</eai:Guid>
      <eai:MessageId/>
      <eai:MessageType>PointToPoint</eai:MessageType>
      <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
      <eai:SbuNumber>3</eai:SbuNumber>
      <eai:SbuCode>FGL</eai:SbuCode>
      <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
      <eai:DataSourceCode>POE</eai:DataSourceCode>
      <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
      <eai:MessageIndex>1</eai:MessageIndex>
      <eai:MessageCount>1</eai:MessageCount>
      <eai:SubjectAreaRunName>salestransactions-380-20161031-045200</eai:SubjectAreaRunName>
   </eai:MessageRouting>
   <eai:Messages>
      <eai:Count>1</eai:Count>
      <eai:SourceSystems>
         <eai:Count>1</eai:Count>
         <eai:SourceSystem>
            <eai:Index>1</eai:Index>
            <eai:SbuNumber>3</eai:SbuNumber>
            <eai:SbuCode>FGL</eai:SbuCode>
            <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
            <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
            <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
         </eai:SourceSystem>
      </eai:SourceSystems>
      <eai:POSLog>
         <arts:Count>1</arts:Count>
         <arts:Transaction TypeCode="NoSaleTransaction">
            <arts:BusinessUnit>
               <arts:Index>1</arts:Index>
               <arts:BusinessUnitNumber>380</arts:BusinessUnitNumber>
               <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
               <arts:BusinessUnitShortName>SC380</arts:BusinessUnitShortName>
               <arts:BusinessUnitName>SC CHARLOTTETOWN</arts:BusinessUnitName>
            </arts:BusinessUnit>
            <arts:WorkstationID>110</arts:WorkstationID>
            <arts:SequenceNumber>000068</arts:SequenceNumber>
            <arts:BeginDateTime>2016-10-31T07:51:14</arts:BeginDateTime>
            <arts:EndDateTime>2016-10-31T07:51:14</arts:EndDateTime>
            <arts:OperatorID OperatorName="anovakova Manager" LastName="Manager" FirstName="anovakova">000002433</arts:OperatorID>
            <arts:TransactionApproval>
               <arts:ApproverID>000002436</arts:ApproverID>
            </arts:TransactionApproval>
            <arts:ControlTransaction>
               <arts:NoSale>
                  <arts:NoSaleReasonCode>DRAWER IN</arts:NoSaleReasonCode>
               </arts:NoSale>
            </arts:ControlTransaction>
            <arts:TransactionSubtypeCode>ControlTransaction</arts:TransactionSubtypeCode>
         </arts:Transaction>
      </eai:POSLog>
   </eai:Messages>
</eai:Exchange>

"""
    }

    public static String getXmlNonMerch() {

        return """
<eai:Exchange xmlns:arts="http://www.fgl.com/ARTS" xmlns:eai="http://www.fgl.com/exchange/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" MajorVersion="2" MinorVersion="0" FixVersion="0">
    <eai:MessageRouting>
        <eai:Guid>37984B5264DA008CE0530A6702465199</eai:Guid>
        <eai:MessageId/>
        <eai:MessageType>PubSub</eai:MessageType>
        <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
        <eai:SbuNumber>3</eai:SbuNumber>
        <eai:SbuCode>FGL</eai:SbuCode>
        <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
        <eai:DataSourceCode>POE</eai:DataSourceCode>
        <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
        <eai:MessageIndex>1</eai:MessageIndex>
        <eai:MessageCount>1</eai:MessageCount>
        <eai:SubjectAreaRunName>salestransactions-309-20160506-153047</eai:SubjectAreaRunName>
    </eai:MessageRouting>
    <eai:Messages>
        <eai:Count>1</eai:Count>
        <eai:SourceSystems>
            <eai:Count>1</eai:Count>
            <eai:SourceSystem>
                <eai:Index>1</eai:Index>
                <eai:SbuNumber>3</eai:SbuNumber>
                <eai:SbuCode>FGL</eai:SbuCode>
                <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
                <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
                <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
            </eai:SourceSystem>
        </eai:SourceSystems>
        <eai:POSLog>
            <arts:Count>1</arts:Count>
            <arts:Transaction TypeCode="SaleTransaction">
                <arts:BusinessUnit>
                    <arts:BusinessUnitNumber>309</arts:BusinessUnitNumber>
                    <arts:BusinessUnitTypeCode>RS</arts:BusinessUnitTypeCode>
                    <arts:BusinessUnitShortName>SC309</arts:BusinessUnitShortName>
                    <arts:BusinessUnitName>SC MARKET MALL</arts:BusinessUnitName>
                </arts:BusinessUnit>
                <arts:WorkstationID>1</arts:WorkstationID>
                <arts:SequenceNumber>2080</arts:SequenceNumber>
                <arts:BeginDateTime>2016-05-06T15:30:47Z</arts:BeginDateTime>
                <arts:EndDateTime>2016-05-06T15:30:47Z</arts:EndDateTime>
                <arts:OperatorID FirstName="Jane" MiddleName="" LastName="Doe" OperatorName="Jane Doe">15888</arts:OperatorID>
                <arts:RetailTransaction>
                    <arts:LineItemCount>3</arts:LineItemCount>
                    <arts:CustomerCount>0</arts:CustomerCount>
                    <arts:AssociateCount>0</arts:AssociateCount>
                    <arts:LineItem VoidFlag="false">
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>1</arts:SequenceNumber>
                        <arts:Sale ItemType="Service" TaxableFlag="true">
                            <arts:TaxCount>1</arts:TaxCount>
                            <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                            <arts:NonSKUItem>
                                <arts:ServiceItemTypeCode>asdfa</arts:ServiceItemTypeCode>
                                <arts:ServiceItemTypeDescriptionText>asdfa</arts:ServiceItemTypeDescriptionText>
                            </arts:NonSKUItem>
                            <arts:RegularSalesUnitPrice>40.00</arts:RegularSalesUnitPrice>
                            <arts:ActualSalesUnitPrice>40.00</arts:ActualSalesUnitPrice>
                            <arts:DiscountAmount>0</arts:DiscountAmount>
                            <arts:Quantity>1</arts:Quantity>
                            <arts:Associate>
                                <arts:AssociateID>3655</arts:AssociateID>
                            </arts:Associate>
                            <arts:Tax TaxType="GST" TypeCode="Sale" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>1</arts:Index>
                                <arts:Amount>2.00</arts:Amount>
                                <arts:Percent>5</arts:Percent>
                            </arts:Tax>
                        </arts:Sale>
                        <arts:LineItemSubtypeCode>Sale</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem VoidFlag="false">
                        <arts:Index>2</arts:Index>
                        <arts:SequenceNumber>1</arts:SequenceNumber>
                        <arts:Return ItemType="Service" TaxableFlag="true">
                            <arts:TaxCount>2</arts:TaxCount>
                            <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                            <arts:NonSKUItem>
                                <arts:ServiceItemTypeCode>qwerqwer</arts:ServiceItemTypeCode>
                                <arts:ServiceItemTypeDescriptionText>qwerqwer</arts:ServiceItemTypeDescriptionText>
                            </arts:NonSKUItem>
                            <arts:RegularSalesUnitPrice>41.99</arts:RegularSalesUnitPrice>
                            <arts:ActualSalesUnitPrice>41.99</arts:ActualSalesUnitPrice>
                            <arts:DiscountAmount>0</arts:DiscountAmount>
                            <arts:Quantity>1</arts:Quantity>
                            <arts:Associate>
                                <arts:AssociateID>6666</arts:AssociateID>
                            </arts:Associate>
                            <arts:Tax TaxType="PEHSTF" TypeCode="Refund" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>1</arts:Index>
                                <arts:Amount>2.10</arts:Amount>
                                <arts:Percent>5</arts:Percent>
                            </arts:Tax>
                            <arts:Tax TaxType="PEHSTP" TypeCode="Refund" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>2</arts:Index>
                                <arts:Amount>3.78</arts:Amount>
                                <arts:Percent>9</arts:Percent>
                            </arts:Tax>
                            <arts:TransactionLink>
                                <arts:BusinessUnit>
                                    <arts:BusinessUnitNumber>380</arts:BusinessUnitNumber>
                                    <arts:BusinessUnitTypeCode>RS</arts:BusinessUnitTypeCode>
                                    <arts:BusinessUnitShortName>SC380</arts:BusinessUnitShortName>
                                    <arts:BusinessUnitName>SC CHARLOTTETOWN</arts:BusinessUnitName>
                                </arts:BusinessUnit>
                                <arts:WorkstationID>1</arts:WorkstationID>
                                <arts:SequenceNumber>4856</arts:SequenceNumber>
                                <arts:BeginDateTime>2016-03-01T00:00:00Z</arts:BeginDateTime>
                                <arts:EndDateTime>2016-03-01T00:00:00Z</arts:EndDateTime>
                                <arts:OperatorID>1</arts:OperatorID>
                                <arts:AssociateID>6666</arts:AssociateID>
                            </arts:TransactionLink>
                            <arts:NonVerifiedReturnFlag>true</arts:NonVerifiedReturnFlag>
                            <arts:SaleReturnTypeCode>NVRTN</arts:SaleReturnTypeCode>
                            <arts:SaleReturnTypeDescriptionText>Non-Verified Return</arts:SaleReturnTypeDescriptionText>
                            <arts:ReasonCode>DEFECTIVE</arts:ReasonCode>
                            <arts:Description>Defective</arts:Description>
                        </arts:Return>
                        <arts:LineItemApproval>
                            <arts:ApproverID>3655</arts:ApproverID>
                        </arts:LineItemApproval>
                        <arts:LineItemSubtypeCode>Return</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>3</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Tax TaxType="GST" TypeCode="Sale">
                            <arts:Index>1</arts:Index>
                            <arts:Amount>2.00</arts:Amount>
                            <arts:Percent>5</arts:Percent>
                        </arts:Tax>
                        <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>4</arts:Index>
                        <arts:SequenceNumber>3</arts:SequenceNumber>
                        <arts:Tender TenderType="Cash" SubTenderType="CAD" TypeCode="Sale">
                            <arts:Amount>42.00</arts:Amount>
                        </arts:Tender>
                        <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:Total TotalType="TransactionGrossAmount">42.00</arts:Total>
                    <arts:Total TotalType="TransactionSubtotal">40.00</arts:Total>
                </arts:RetailTransaction>
                <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
            </arts:Transaction>
        </eai:POSLog>
    </eai:Messages>
</eai:Exchange>





"""
    }

    public static String getXmlNonMerchReturn() {

        return """
<eai:Exchange xmlns:arts="http://www.fgl.com/ARTS" xmlns:eai="http://www.fgl.com/exchange/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" MajorVersion="2" MinorVersion="0" FixVersion="0">
    <eai:MessageRouting>
        <eai:Guid>37984B5264DA008CE0530A6702465199</eai:Guid>
        <eai:MessageId></eai:MessageId>
        <eai:MessageType>PubSub</eai:MessageType>
        <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
        <eai:SbuNumber>3</eai:SbuNumber>
        <eai:SbuCode>FGL</eai:SbuCode>
        <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
        <eai:DataSourceCode>POE</eai:DataSourceCode>
        <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
        <eai:MessageIndex>1</eai:MessageIndex>
        <eai:MessageCount>1</eai:MessageCount>
        <eai:SubjectAreaRunName>salestransactions-380-20160810-134747</eai:SubjectAreaRunName>
    </eai:MessageRouting>
    <eai:Messages>
        <eai:Count>1</eai:Count>
        <eai:SourceSystems>
            <eai:Count>1</eai:Count>
            <eai:SourceSystem>
                <eai:Index>1</eai:Index>
                <eai:SbuNumber>3</eai:SbuNumber>
                <eai:SbuCode>FGL</eai:SbuCode>
                <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
                <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
                <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
            </eai:SourceSystem>
        </eai:SourceSystems>
        <eai:POSLog>
            <arts:Count>1</arts:Count>
            <arts:Transaction TypeCode="ReturnTransaction">
                <arts:BusinessUnit>
                    <arts:BusinessUnitNumber>380</arts:BusinessUnitNumber>
                    <arts:BusinessUnitTypeCode>RS</arts:BusinessUnitTypeCode>
                    <arts:BusinessUnitShortName>SC380</arts:BusinessUnitShortName>
                    <arts:BusinessUnitName>SC CHARLOTTETOWN</arts:BusinessUnitName>
                </arts:BusinessUnit>
                <arts:WorkstationID>1</arts:WorkstationID>
                <arts:SequenceNumber>2522</arts:SequenceNumber>
                <arts:BeginDateTime>2016-08-10T13:47:47Z</arts:BeginDateTime>
                <arts:EndDateTime>2016-08-10T13:47:47Z</arts:EndDateTime>
                <arts:OperatorID OperatorName="Jane Doe" FirstName="Jane" MiddleName="" LastName="Doe">1</arts:OperatorID>
                <arts:RetailTransaction>
                    <arts:LineItemCount>5</arts:LineItemCount>
                    <arts:CustomerCount>1</arts:CustomerCount>
                    <arts:AssociateCount>0</arts:AssociateCount>
                    <arts:LineItem VoidFlag="false">
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>1</arts:SequenceNumber>
                        <arts:Return ItemType="Service" TaxableFlag="true">
                            <arts:TaxCount>2</arts:TaxCount>
                            <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                            <arts:NonSKUItem>
                                <arts:ServiceItemTypeCode>REPAIRS AND ALTERATIONS</arts:ServiceItemTypeCode>
                                <arts:ServiceItemTypeDescriptionText>Repairs and Alterations</arts:ServiceItemTypeDescriptionText>
                            </arts:NonSKUItem>
                            <arts:RegularSalesUnitPrice>41.99</arts:RegularSalesUnitPrice>
                            <arts:ActualSalesUnitPrice>41.99</arts:ActualSalesUnitPrice>
                            <arts:DiscountAmount>0</arts:DiscountAmount>
                            <arts:Quantity>1</arts:Quantity>
                            <arts:Associate>
                                <arts:AssociateID>6666</arts:AssociateID>
                            </arts:Associate>
                            <arts:Tax TaxType="PEHSTF" TypeCode="Refund" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>1</arts:Index>
                                <arts:Amount>2.10</arts:Amount>
                                <arts:Percent>5</arts:Percent>
                            </arts:Tax>
                            <arts:Tax TaxType="PEHSTP" TypeCode="Refund" SourceSystemTaxExemptionFlag="false">
                                <arts:Index>2</arts:Index>
                                <arts:Amount>3.78</arts:Amount>
                                <arts:Percent>9</arts:Percent>
                            </arts:Tax>
                            <arts:TransactionLink>
                                <arts:BusinessUnit>
                                    <arts:BusinessUnitNumber>380</arts:BusinessUnitNumber>
                                    <arts:BusinessUnitTypeCode>RS</arts:BusinessUnitTypeCode>
                                    <arts:BusinessUnitShortName>SC380</arts:BusinessUnitShortName>
                                    <arts:BusinessUnitName>SC CHARLOTTETOWN</arts:BusinessUnitName>
                                </arts:BusinessUnit>
                                <arts:WorkstationID>1</arts:WorkstationID>
                                <arts:SequenceNumber>4856</arts:SequenceNumber>
                                <arts:BeginDateTime>2016-03-01T00:00:00Z</arts:BeginDateTime>
                                <arts:EndDateTime>2016-03-01T00:00:00Z</arts:EndDateTime>
                                <arts:OperatorID>1</arts:OperatorID>
                                <arts:AssociateID>6666</arts:AssociateID>
                            </arts:TransactionLink>
                            <arts:NonVerifiedReturnFlag>true</arts:NonVerifiedReturnFlag>
                            <arts:SaleReturnTypeCode>NVRTN</arts:SaleReturnTypeCode>
                            <arts:SaleReturnTypeDescriptionText>Non-Verified Return</arts:SaleReturnTypeDescriptionText>
                            <arts:ReasonCode>DEFECTIVE</arts:ReasonCode>
                            <arts:Description>Defective</arts:Description>
                        </arts:Return>
                        <arts:LineItemApproval>
                            <arts:ApproverID>3655</arts:ApproverID>
                        </arts:LineItemApproval>
                        <arts:LineItemSubtypeCode>Return</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>2</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Tax TaxType="PEHSTF" TypeCode="Refund">
                            <arts:Index>1</arts:Index>
                            <arts:Amount>2.10</arts:Amount>
                            <arts:Percent>5</arts:Percent>
                        </arts:Tax>
                        <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>3</arts:Index>
                        <arts:SequenceNumber>3</arts:SequenceNumber>
                        <arts:Tax TaxType="PEHSTP" TypeCode="Refund">
                            <arts:Index>1</arts:Index>
                            <arts:Amount>3.78</arts:Amount>
                            <arts:Percent>9</arts:Percent>
                        </arts:Tax>
                        <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>4</arts:Index>
                        <arts:SequenceNumber>4</arts:SequenceNumber>
                        <arts:Tender TenderType="Cash" SubTenderType="CAD" TypeCode="Refund">
                            <arts:Amount>47.85</arts:Amount>
                        </arts:Tender>
                        <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:LineItem>
                        <arts:Index>5</arts:Index>
                        <arts:SequenceNumber>5</arts:SequenceNumber>
                        <arts:Tender TenderType="PennyRounding" SubTenderType="CAD" TypeCode="Refund">
                            <arts:Amount>0.02</arts:Amount>
                        </arts:Tender>
                        <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
                    </arts:LineItem>
                    <arts:Total TotalType="TransactionGrossAmount">47.87</arts:Total>
                    <arts:Total TotalType="TransactionSubtotal">41.99</arts:Total>
                    <arts:Customer>
                        <arts:Index>1</arts:Index>
                        <arts:AddressCount>1</arts:AddressCount>
                        <arts:TelephoneCount>1</arts:TelephoneCount>
                        <arts:CustomerName>
                            <arts:Name TypeCode="FirstName">Amanda</arts:Name>
                            <arts:Name TypeCode="LastName">test</arts:Name>
                        </arts:CustomerName>
                        <arts:Address>
                            <arts:Index>1</arts:Index>
                            <arts:PostalCode>D9D 9D9</arts:PostalCode>
                        </arts:Address>
                        <arts:Telephone>
                            <arts:Index>1</arts:Index>
                            <arts:FullTelephoneNumber>4063333333</arts:FullTelephoneNumber>
                        </arts:Telephone>
                    </arts:Customer>
                </arts:RetailTransaction>
                <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
            </arts:Transaction>
        </eai:POSLog>
    </eai:Messages>
</eai:Exchange>





"""
    }

    public static String getXmlPayOut() {

        return """
<eai:Exchange MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>351DF6D845AD4585A3918B7A4FBCFD80</eai:Guid>
      <eai:MessageId/>
      <eai:MessageType>PointToPoint</eai:MessageType>
      <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
      <eai:SbuNumber>3</eai:SbuNumber>
      <eai:SbuCode>FGL</eai:SbuCode>
      <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
      <eai:DataSourceCode>POE</eai:DataSourceCode>
      <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
      <eai:MessageIndex>1</eai:MessageIndex>
      <eai:MessageCount>1</eai:MessageCount>
      <eai:SubjectAreaRunName>salestransactions-371-20161128-020700</eai:SubjectAreaRunName>
   </eai:MessageRouting>
   <eai:Messages>
      <eai:Count>1</eai:Count>
      <eai:SourceSystems>
         <eai:Count>1</eai:Count>
         <eai:SourceSystem>
            <eai:Index>1</eai:Index>
            <eai:SbuNumber>3</eai:SbuNumber>
            <eai:SbuCode>FGL</eai:SbuCode>
            <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
            <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
            <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
         </eai:SourceSystem>
      </eai:SourceSystems>
      <eai:POSLog>
         <arts:Count>1</arts:Count>
         <arts:Transaction TypeCode="PayOut">
            <arts:BusinessUnit>
               <arts:Index>1</arts:Index>
               <arts:BusinessUnitNumber>371</arts:BusinessUnitNumber>
               <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
               <arts:BusinessUnitShortName>SC371</arts:BusinessUnitShortName>
               <arts:BusinessUnitName>SC VILLAGE SHOPPING CENTRE</arts:BusinessUnitName>
            </arts:BusinessUnit>
            <arts:WorkstationID>105</arts:WorkstationID>
            <arts:SequenceNumber>000269</arts:SequenceNumber>
            <arts:BeginDateTime>2016-11-28T05:36:54</arts:BeginDateTime>
            <arts:EndDateTime>2016-11-28T05:36:54</arts:EndDateTime>
            <arts:OperatorID OperatorName="anovakova Cashier" LastName="Cashier" FirstName="anovakova">000002436</arts:OperatorID>
            <arts:TransactionApproval>
               <arts:ApproverID>000002433</arts:ApproverID>
            </arts:TransactionApproval>
            <arts:TenderControlTransaction>
               <arts:TenderControlTransactionSubtypeCode>PayOut</arts:TenderControlTransactionSubtypeCode>
               <arts:PayOut>
                  <arts:TenderAmount>1.00</arts:TenderAmount>
                  <arts:ReasonCode>CHANGEMETOSOMETHINGVALID</arts:ReasonCode>
                  <arts:ReasonDescriptionText>CHANGEMETOSOMETHINGVALID</arts:ReasonDescriptionText>
               </arts:PayOut>
            </arts:TenderControlTransaction>
            <arts:TransactionSubtypeCode>TenderControlTransaction</arts:TransactionSubtypeCode>
         </arts:Transaction>
      </eai:POSLog>
   </eai:Messages>
</eai:Exchange>
"""
    }

    public static String getXmlPayIn() {

        return """
<eai:Exchange MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>1BB5B727FB2F445A9D60DFB3AB8965F9</eai:Guid>
      <eai:MessageId/>
      <eai:MessageType>PointToPoint</eai:MessageType>
      <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
      <eai:SbuNumber>3</eai:SbuNumber>
      <eai:SbuCode>FGL</eai:SbuCode>
      <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
      <eai:DataSourceCode>POE</eai:DataSourceCode>
      <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
      <eai:MessageIndex>1</eai:MessageIndex>
      <eai:MessageCount>1</eai:MessageCount>
      <eai:SubjectAreaRunName>salestransactions-371-20161125-083400</eai:SubjectAreaRunName>
   </eai:MessageRouting>
   <eai:Messages>
      <eai:Count>1</eai:Count>
      <eai:SourceSystems>
         <eai:Count>1</eai:Count>
         <eai:SourceSystem>
            <eai:Index>1</eai:Index>
            <eai:SbuNumber>3</eai:SbuNumber>
            <eai:SbuCode>FGL</eai:SbuCode>
            <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
            <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
            <eai:MessageSourceCodeName>Central Sales Server</eai:MessageSourceCodeName>
         </eai:SourceSystem>
      </eai:SourceSystems>
      <eai:POSLog>
         <arts:Count>1</arts:Count>
         <arts:Transaction TypeCode="PayIn">
            <arts:BusinessUnit>
               <arts:Index>1</arts:Index>
               <arts:BusinessUnitNumber>371</arts:BusinessUnitNumber>
               <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
               <arts:BusinessUnitShortName>SC371</arts:BusinessUnitShortName>
               <arts:BusinessUnitName>SC VILLAGE SHOPPING CENTRE</arts:BusinessUnitName>
            </arts:BusinessUnit>
            <arts:WorkstationID>105</arts:WorkstationID>
            <arts:SequenceNumber>000253</arts:SequenceNumber>
            <arts:BeginDateTime>2016-11-25T12:03:04</arts:BeginDateTime>
            <arts:EndDateTime>2016-11-25T12:03:04</arts:EndDateTime>
            <arts:OperatorID OperatorName="anovakova Manager" LastName="Manager" FirstName="anovakova">000002433</arts:OperatorID>
            <arts:TransactionApproval>
               <arts:ApproverID>000002406</arts:ApproverID>
            </arts:TransactionApproval>
            <arts:TenderControlTransaction>
               <arts:TenderControlTransactionSubtypeCode>PayIn</arts:TenderControlTransactionSubtypeCode>
               <arts:PayIn>
                  <arts:TenderAmount>100.00</arts:TenderAmount>
                  <arts:ReasonCode>CHANGEMETOSOMETHINGVALID</arts:ReasonCode>
                  <arts:ReasonDescriptionText>CHANGEMETOSOMETHINGVALID</arts:ReasonDescriptionText>
               </arts:PayIn>
            </arts:TenderControlTransaction>
            <arts:TransactionSubtypeCode>TenderControlTransaction</arts:TransactionSubtypeCode>
         </arts:Transaction>
      </eai:POSLog>
   </eai:Messages>
</eai:Exchange>
"""
    }


}
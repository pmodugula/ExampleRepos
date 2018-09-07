/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.eai.iib.testing.stub

class AWTransactionSubscriberItemDiscountStub {

    public static String getXml(String crudOperation) {
		
    return """
<eai:Exchange FixVersion="1" MinorVersion="4" MajorVersion="1" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
    <eai:MessageRouting>
       <eai:Guid>b068cace-524e-47e6-82fa-f07185085365</eai:Guid>
       <eai:MessageId>String</eai:MessageId>
       <eai:MessageType>PubSub</eai:MessageType>
       <eai:SubjectArea>Normal Retail Sales Transactions</eai:SubjectArea>
       <eai:SbuNumber>3</eai:SbuNumber>
       <eai:SbuCode>FGL</eai:SbuCode>
       <eai:SbuName>FGL2 Sports Ltd.</eai:SbuName>
       <eai:DataSourceCode>POE</eai:DataSourceCode>
       <eai:DataSourceCodeName>POE POS FGL</eai:DataSourceCodeName>
       <eai:MessageIndex>1</eai:MessageIndex>
       <eai:MessageCount>1</eai:MessageCount>
       <eai:SubjectAreaRunName>Sting</eai:SubjectAreaRunName>
    </eai:MessageRouting>
    <eai:Messages>
       <eai:Count>0</eai:Count>
       <eai:SourceSystems>
          <eai:Count>0</eai:Count>
          <eai:SourceSystem>
             <eai:Index>0</eai:Index>
             <eai:SbuNumber>3</eai:SbuNumber>
             <eai:SbuCode>FGL</eai:SbuCode>
             <eai:SbuName>FGL Sports Ltd.</eai:SbuName>
             <eai:MessageSourceCode>CSS</eai:MessageSourceCode>
             <eai:MessageSourceCodeName>String</eai:MessageSourceCodeName>
          </eai:SourceSystem>
       </eai:SourceSystems>
       <eai:POSLog>
          <arts:Count>0</arts:Count>
          <arts:Transaction TypeCode="SaleTransaction">
             <arts:BusinessUnit>
                <arts:Index>0</arts:Index>
                <arts:BusinessUnitNumber>277</arts:BusinessUnitNumber>
                <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
                <arts:BusinessUnitShortName>SC EDMONTON CITY CENTRE</arts:BusinessUnitShortName>
                <arts:BusinessUnitName>SC277</arts:BusinessUnitName>
             </arts:BusinessUnit>
             <arts:WorkstationID>3</arts:WorkstationID>
             <arts:SequenceNumber>000169</arts:SequenceNumber>
             <arts:BeginDateTime>2016-09-01T10:56:55-06:00</arts:BeginDateTime>
             <arts:EndDateTime>2016-09-01T10:57:50-06:00</arts:EndDateTime>
             <arts:OperatorID OperatorName="GUEST3 Manager" LastName="Manager" FirstName="GUEST3">000002573</arts:OperatorID>
             <arts:RetailTransaction TypeCode="SaleTransaction">
                <arts:LineItem VoidFlag="false">
                   <arts:SequenceNumber>1</arts:SequenceNumber>
                   <arts:Sale TaxableFlag="true" ItemType="Stock">
                      <arts:Item>
                         <arts:ItemNumber>319192117990000</arts:ItemNumber>
                         <arts:ItemName>MISC</arts:ItemName>
                         <arts:UPCNumber>431900002472</arts:UPCNumber>
                      </arts:Item>
                      <arts:MerchandiseHierarchy ID="9810" Level="4"/>
                      <arts:RegularSalesUnitPrice>99.00</arts:RegularSalesUnitPrice>
                      <arts:ActualSalesUnitPrice>97.02</arts:ActualSalesUnitPrice>
                      <arts:DiscountAmount>1.98</arts:DiscountAmount>
                      <arts:Quantity>1</arts:Quantity>
                      <arts:Associate>
                         <arts:AssociateID>000002573</arts:AssociateID>
                      </arts:Associate>
                      <arts:Tax TypeCode="Sale" TaxType="GST">
                         <arts:Amount>4.85</arts:Amount>
                      </arts:Tax>
                      <arts:SerialNumber>String</arts:SerialNumber>
                      <arts:RetailPriceModifier>
                         <arts:SequenceNumber>2</arts:SequenceNumber>
                         <arts:Amount Action="Subtract">1.98</arts:Amount>
                         <arts:ReasonCode>CHANGEMEEEEEEE</arts:ReasonCode>
                      </arts:RetailPriceModifier>
                   </arts:Sale>
                </arts:LineItem>
                <arts:LineItem>
                   <arts:SequenceNumber>3</arts:SequenceNumber>
                   <arts:Tax TypeCode="Sale" TaxType="GST">
                      <arts:Amount>4.85</arts:Amount>
                      <arts:Percent>5.00</arts:Percent>
                   </arts:Tax>
                </arts:LineItem>
                <arts:LineItem>
                   <arts:SequenceNumber>4</arts:SequenceNumber>
                   <arts:Tender TypeCode="Sale" TenderType="PennyRounding">
                      <arts:Amount>0.02</arts:Amount>
                   </arts:Tender>
                </arts:LineItem>
                <arts:LineItem>
                   <arts:SequenceNumber>5</arts:SequenceNumber>
                   <arts:Tender TypeCode="Sale" SubTenderType="CAD" TenderType="Cash">
                      <arts:Amount>101.85</arts:Amount>
                   </arts:Tender>
                </arts:LineItem>
                <arts:Total TotalType="TransactionGrossAmount">101.85</arts:Total>
                <arts:Total TotalType="TransactionSubtotal">97.00</arts:Total>
                <arts:Total TotalType="TransactionDiscountTotal">0.00</arts:Total>
             </arts:RetailTransaction>
        <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
          </arts:Transaction>
       </eai:POSLog>
    </eai:Messages>
 </eai:Exchange>
"""
	}  
	

}
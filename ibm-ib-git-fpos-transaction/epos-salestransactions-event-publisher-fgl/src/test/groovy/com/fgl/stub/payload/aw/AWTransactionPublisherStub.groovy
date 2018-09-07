/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.stub.payload.aw

class AWTransactionPublisherStub {
    /**
     * Returns a stubbed version of the employee subscriber record
     * getXml
     * @return String of XML employee subscriber Stub Record
     */
    public static String getXml(String crudOperation) {
		
    return """
<eai:Exchange MinorVersion="4" MajorVersion="1" FixVersion="1" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>6D33CB9F9EF24DA58122A8E941C933D1</eai:Guid>
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
      <eai:SubjectAreaRunName>salestransactions-280-20160927-170200</eai:SubjectAreaRunName>
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
            <eai:MessageSourceCodeName></eai:MessageSourceCodeName>
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
            <arts:WorkstationID>023</arts:WorkstationID>
            <arts:SequenceNumber>000016</arts:SequenceNumber>
            <arts:BeginDateTime>2016-09-27T16:59:16+03:00</arts:BeginDateTime>
            <arts:EndDateTime>2016-09-27T17:01:18+03:00</arts:EndDateTime>
            <arts:OperatorID OperatorName="nkirichenko Manager" LastName="Manager" FirstName="nkirichenko">000002743</arts:OperatorID>
            <arts:RetailTransaction>
               <arts:CustomerCount>1</arts:CustomerCount>
               <arts:AssociateCount>0</arts:AssociateCount>
               <arts:LineItemCount>5</arts:LineItemCount>
               <arts:LineItem VoidFlag="false">
                  <arts:Index>1</arts:Index>
                  <arts:SequenceNumber>1</arts:SequenceNumber>
                  <arts:Sale TaxableFlag="true" SourceSystemTaxExemptionFlag="false" ItemType="Stock">
                     <arts:TaxCount>1</arts:TaxCount>
                     <arts:RetailPriceModifierCount>0</arts:RetailPriceModifierCount>
                     <arts:Item>
                        <arts:ItemNumber>331515792</arts:ItemNumber>
                        <arts:ItemName>SCOTT 350 TREAD HELMET 99 05</arts:ItemName>
                        <arts:UPCNumber>886118246116</arts:UPCNumber>
                     </arts:Item>
                     <arts:MerchandiseHierarchy Level="4" ID="3810"/>
                     <arts:RegularSalesUnitPrice>174.00</arts:RegularSalesUnitPrice>
                     <arts:ActualSalesUnitPrice>174.00</arts:ActualSalesUnitPrice>
                     <arts:DiscountAmount>0.00</arts:DiscountAmount>
                     <arts:Quantity>1</arts:Quantity>
                     <arts:Associate>
                        <arts:Index>1</arts:Index>
                        <arts:AssociateID OperatorName="nkirichenko Manager" LastName="Manager" FirstName="nkirichenko">000002743</arts:AssociateID>
                     </arts:Associate>
                     <arts:Tax TypeCode="Sale" TaxType="GST">
                        <arts:Index>1</arts:Index>
                        <arts:Amount>8.70</arts:Amount>
                        <arts:Percent>5.00</arts:Percent>
                     </arts:Tax>
                     <arts:SerialNumber/>
                  </arts:Sale>
               </arts:LineItem>
               <arts:LineItem VoidFlag="false">
                  <arts:Index>2</arts:Index>
                  <arts:SequenceNumber>2</arts:SequenceNumber>
                  <arts:Sale TaxableFlag="true" SourceSystemTaxExemptionFlag="false" ItemType="Stock">
                     <arts:TaxCount>1</arts:TaxCount>
                     <arts:RetailPriceModifierCount>1</arts:RetailPriceModifierCount>
                     <arts:Item>
                        <arts:ItemNumber>331711742</arts:ItemNumber>
                        <arts:ItemName>SCOTT RECOIL XI ROSE/BLK GOGG 99 00</arts:ItemName>
                        <arts:UPCNumber>886118061702</arts:UPCNumber>
                     </arts:Item>
                     <arts:MerchandiseHierarchy Level="4" ID="3812"/>
                     <arts:RegularSalesUnitPrice>51.00</arts:RegularSalesUnitPrice>
                     <arts:ActualSalesUnitPrice>49.98</arts:ActualSalesUnitPrice>
                     <arts:DiscountAmount>1.02</arts:DiscountAmount>
                     <arts:Quantity>1</arts:Quantity>
                     <arts:Associate>
                        <arts:Index>1</arts:Index>
                        <arts:AssociateID OperatorName="nkirichenko Manager" LastName="Manager" FirstName="nkirichenko">000002743</arts:AssociateID>
                     </arts:Associate>
                     <arts:RetailPriceModifier>
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>3</arts:SequenceNumber>
                        <arts:Amount Action="Subtract">1.02</arts:Amount>
                        <arts:ReasonCode>Ad Price Not in System</arts:ReasonCode>
                     </arts:RetailPriceModifier>
                     <arts:Tax TypeCode="Sale" TaxType="GST">
                        <arts:Index>1</arts:Index>
                        <arts:Amount>2.50</arts:Amount>
                        <arts:Percent>5.00</arts:Percent>
                     </arts:Tax>
                     <arts:SerialNumber/>
                  </arts:Sale>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>3</arts:Index>
                  <arts:SequenceNumber>4</arts:SequenceNumber>
                  <arts:Tax TypeCode="Sale" TaxType="GST">
                     <arts:Index>1</arts:Index>
                     <arts:Amount>11.20</arts:Amount>
                     <arts:Percent>5.00</arts:Percent>
                  </arts:Tax>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>4</arts:Index>
                  <arts:SequenceNumber>5</arts:SequenceNumber>
                  <arts:Tender TypeCode="Sale" TenderType="Cash" SubTenderType="CAD">
                     <arts:Amount>235.20</arts:Amount>
                  </arts:Tender>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>5</arts:Index>
                  <arts:SequenceNumber>6</arts:SequenceNumber>
                  <arts:Tender TypeCode="Refund" TenderType="PennyRounding">
                     <arts:Amount>0.02</arts:Amount>
                  </arts:Tender>
               </arts:LineItem>
               <arts:Total TotalType="TransactionGrossAmount">235.20</arts:Total>
               <arts:Total TotalType="TransactionSubtotal">224.00</arts:Total>
               <arts:Customer>
                  <arts:Index>1</arts:Index>
                  <arts:AddressCount>1</arts:AddressCount>
                  <arts:TelephoneCount>0</arts:TelephoneCount>
                  <arts:Address>
                     <arts:Index>1</arts:Index>
                     <arts:PostalCode>F8R5T3</arts:PostalCode>
                  </arts:Address>
               </arts:Customer>
            </arts:RetailTransaction>
            <arts:TransactionSubtypeCode>RetailTransaction</arts:TransactionSubtypeCode>
         </arts:Transaction>
      </eai:POSLog>
   </eai:Messages>
</eai:Exchange>
"""
	}  
	

}
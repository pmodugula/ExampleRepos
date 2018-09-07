/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.eai.iib.testing.stub

class AWTransactionSubscriberTransactionDiscountStub {

    public static String getXml(String crudOperation) {


        // transaction level discount sample
    return """
<eai:Exchange MinorVersion="0" MajorVersion="2" FixVersion="0" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>EAB42695A2C84AF7B073661F016583DA</eai:Guid>
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
      <eai:SubjectAreaRunName>salestransactions-280-20161019-151012</eai:SubjectAreaRunName>
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
               <arts:BusinessUnitShortName>SC280</arts:BusinessUnitShortName>
               <arts:BusinessUnitName>SC FORT MCMURRAY</arts:BusinessUnitName>
            </arts:BusinessUnit>
            <arts:WorkstationID>110</arts:WorkstationID>
            <arts:SequenceNumber>000009</arts:SequenceNumber>
            <arts:BeginDateTime>2016-10-19T15:02:57+03:00</arts:BeginDateTime>
            <arts:EndDateTime>2016-10-19T15:04:04+03:00</arts:EndDateTime>
            <arts:OperatorID OperatorName="ikurganova Cashier" LastName="Cashier" FirstName="ikurganova">000002446</arts:OperatorID>
            <arts:RetailTransaction>
               <arts:LineItemCount>4</arts:LineItemCount>
               <arts:CustomerCount>1</arts:CustomerCount>
               <arts:AssociateCount>0</arts:AssociateCount>
               <arts:LineItem VoidFlag="false">
                  <arts:Index>1</arts:Index>
                  <arts:SequenceNumber>1</arts:SequenceNumber>
                  <arts:Sale TaxableFlag="true" ItemType="Stock">
                     <arts:TaxCount>1</arts:TaxCount>
                     <arts:RetailPriceModifierCount>1</arts:RetailPriceModifierCount>
                     <arts:Item>
                        <arts:ItemNumber>916355</arts:ItemNumber>
                        <arts:ItemName>TOMMY.COM DIGITAL 01 00</arts:ItemName>
                        <arts:UPCNumber>440000618001</arts:UPCNumber>
                     </arts:Item>
                     <arts:MerchandiseHierarchy Level="4" ID="9810"/>
                     <arts:RegularSalesUnitPrice>49.00</arts:RegularSalesUnitPrice>
                     <arts:ActualSalesUnitPrice>36.50</arts:ActualSalesUnitPrice>
                     <arts:DiscountAmount>12.50</arts:DiscountAmount>
                     <arts:Quantity>4</arts:Quantity>
                     <arts:RetailPriceModifier>
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Amount Action="Subtract">12.50</arts:Amount>
                        <arts:ReasonCode>COUPON SUBTOTAL DISCOUNT</arts:ReasonCode>
                     </arts:RetailPriceModifier>
                     <arts:Tax TypeCode="Sale" TaxType="GST" SourceSystemTaxExemptionFlag="false">
                        <arts:Index>1</arts:Index>
                        <arts:Amount>7.30</arts:Amount>
                        <arts:Percent>5</arts:Percent>
                     </arts:Tax>
                  </arts:Sale>
                  <arts:LineItemSubtypeCode>Sale</arts:LineItemSubtypeCode>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>2</arts:Index>
                  <arts:SequenceNumber>3</arts:SequenceNumber>
                  <arts:Tax TypeCode="Sale" TaxType="GST">
                     <arts:Index>1</arts:Index>
                     <arts:Amount>7.30</arts:Amount>
                     <arts:Percent>5</arts:Percent>
                  </arts:Tax>
                  <arts:LineItemSubtypeCode>Tax</arts:LineItemSubtypeCode>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>3</arts:Index>
                  <arts:SequenceNumber>4</arts:SequenceNumber>
                  <arts:Discount>
                     <arts:Amount>50.00</arts:Amount>
                     <arts:ReasonCode>COUPON SUBTOTAL DISCOUNT</arts:ReasonCode>
                  </arts:Discount>
                  <arts:LineItemSubtypeCode>Discount</arts:LineItemSubtypeCode>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:Index>4</arts:Index>
                  <arts:SequenceNumber>5</arts:SequenceNumber>
                  <arts:Tender TypeCode="Sale" TenderType="Cash" SubTenderType="CAD">
                     <arts:Amount>153.30</arts:Amount>
                  </arts:Tender>
                  <arts:LineItemSubtypeCode>Tender</arts:LineItemSubtypeCode>
               </arts:LineItem>
               <arts:Total TotalType="TransactionGrossAmount">153.30</arts:Total>
               <arts:Total TotalType="TransactionSubtotal">146.00</arts:Total>
               <arts:Customer>
                  <arts:Index>1</arts:Index>
                  <arts:AddressCount>1</arts:AddressCount>
                  <arts:TelephoneCount>0</arts:TelephoneCount>
                  <arts:Address>
                     <arts:Index>1</arts:Index>
                     <arts:PostalCode>A8F 7W4</arts:PostalCode>
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
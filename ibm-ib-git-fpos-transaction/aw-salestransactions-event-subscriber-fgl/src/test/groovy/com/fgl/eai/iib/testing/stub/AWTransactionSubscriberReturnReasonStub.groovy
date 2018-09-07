/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.eai.iib.testing.stub

class AWTransactionSubscriberReturnReasonStub {

    public static String getXml(String crudOperation) {
		
    return """
<eai:Exchange FixVersion="1" MinorVersion="4" MajorVersion="1" xmlns:eai="http://www.fgl.com/exchange/" xmlns:arts="http://www.fgl.com/ARTS">
   <eai:MessageRouting>
      <eai:Guid>60bc06bf-b2f6-496c-982c-0584fc96db7f</eai:Guid>
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
         <arts:Transaction TypeCode="ReturnTransaction">
            <arts:BusinessUnit>
               <arts:Index>0</arts:Index>
               <arts:BusinessUnitNumber>283</arts:BusinessUnitNumber>
               <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
               <arts:BusinessUnitShortName>SC FORT MCMURRAY</arts:BusinessUnitShortName>
               <arts:BusinessUnitName>SC280</arts:BusinessUnitName>
            </arts:BusinessUnit>
            <arts:WorkstationID>2</arts:WorkstationID>
            <arts:SequenceNumber>000036</arts:SequenceNumber>
            <arts:BeginDateTime>2016-09-22T08:52:47-06:00</arts:BeginDateTime>
            <arts:EndDateTime>2016-09-22T08:53:46-06:00</arts:EndDateTime>
            <arts:OperatorID OperatorName="agrishanova Manager" LastName="Manager" FirstName="agrishanova">000002083</arts:OperatorID>
            <arts:RetailTransaction>
               <arts:LineItem VoidFlag="false">
                  <arts:SequenceNumber>1</arts:SequenceNumber>
                  <arts:Return SourceSystemTaxExemption="true" TaxableFlag="true" ItemType="Stock">
\t\t\t\t   <arts:RetailPriceModifierCount>1</arts:RetailPriceModifierCount>
                     <arts:Item>
                        <arts:Index>0</arts:Index>
                        <arts:ItemNumber>254150040990000</arts:ItemNumber>
                        <arts:ItemName>NORDICA N2S QS DEMO BINDING</arts:ItemName>
                        <arts:UPCNumber>47791227131</arts:UPCNumber>
                     </arts:Item>
                     <arts:MerchandiseHierarchy ID="9210" Level="4"/>
                     <arts:RegularSalesUnitPrice>149.00</arts:RegularSalesUnitPrice>
                     <arts:ActualSalesUnitPrice>149.00</arts:ActualSalesUnitPrice>
                     <arts:DiscountAmount>0.00</arts:DiscountAmount>
                     <arts:Quantity>1</arts:Quantity>
\t\t\t\t\t  <arts:RetailPriceModifier>
                        <arts:Index>1</arts:Index>
                        <arts:SequenceNumber>2</arts:SequenceNumber>
                        <arts:Amount Action="Add">15.00</arts:Amount>
                     </arts:RetailPriceModifier>
                     <arts:Tax TypeCode="Return" TaxType="GST">
                        <arts:Amount>7.45</arts:Amount>
                     </arts:Tax>
                     <arts:SerialNumber>String</arts:SerialNumber>
                     <arts:TransactionLink ReasonCode="Return" EntryMethod="Scanned">
                        <arts:BusinessUnit>
                           <arts:Index>0</arts:Index>
                           <arts:BusinessUnitNumber>283</arts:BusinessUnitNumber>
                           <arts:BusinessUnitTypeCode>RT</arts:BusinessUnitTypeCode>
                           <arts:BusinessUnitShortName>SC FORT MCMURRAY</arts:BusinessUnitShortName>
                           <arts:BusinessUnitName>SC280</arts:BusinessUnitName>
                        </arts:BusinessUnit>
                        <arts:WorkstationID>2</arts:WorkstationID>
                        <arts:SequenceNumber>000035</arts:SequenceNumber>
                        <arts:BeginDateTime>2016-09-22T08:52:07-06:00</arts:BeginDateTime>
                        <arts:EndDateTime>2016-09-22T08:52:19-06:00</arts:EndDateTime>
                        <arts:OperatorID>000002083</arts:OperatorID>
                     </arts:TransactionLink>
                     <arts:SaleReturnTypeCode>RRTN</arts:SaleReturnTypeCode>
                     <arts:SaleReturnTypeDescriptionText>Receipted Return</arts:SaleReturnTypeDescriptionText>
                     <arts:ReasonCode>DEFECTIVE</arts:ReasonCode>
                     <arts:Description>Defective</arts:Description>
                  </arts:Return>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:SequenceNumber>2</arts:SequenceNumber>
                  <arts:Tax TypeCode="Refund" TaxType="GST">
                     <arts:Amount>7.45</arts:Amount>
                     <arts:Percent>5.00</arts:Percent>
                  </arts:Tax>
               </arts:LineItem>
               <arts:LineItem>
                  <arts:SequenceNumber>3</arts:SequenceNumber>
                  <arts:Tender TypeCode="Refund" SubTenderType="CAD" TenderType="Cash">
                     <arts:Amount>156.45</arts:Amount>
                  </arts:Tender>
               </arts:LineItem>
               <arts:Total TotalType="TransactionGrossAmount">156.45</arts:Total>
               <arts:Total TotalType="TransactionSubtotal">149.00</arts:Total>
               <arts:Total TotalType="TransactionDiscountTotal">0.00</arts:Total>
               <arts:Customer>
                  <arts:CustomerName>
                     <arts:Name TypeCode="FirstName">Alinochka</arts:Name>
                     <arts:Name TypeCode="LastName">Grishanova</arts:Name>
                  </arts:CustomerName>
                  <arts:Address>
                     <arts:PostalCode>T5T 5T5</arts:PostalCode>
                  </arts:Address>
                  <arts:Telephone>
                     <arts:FullTelephoneNumber>5555555555</arts:FullTelephoneNumber>
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
	

}
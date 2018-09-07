/**
 * Created by Derek Marley (derek@middleware360.com)
 */
package com.fgl.stub.payload.employee

import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil
import groovy.xml.MarkupBuilder


class EmployeeSubscriberStub {
    /**
     * Returns a stubbed version of the employee subscriber record
     * getXml
     * @return String of XML employee subscriber Stub Record
     */
    public static String getXml(String crudOperation) {
		
    return """<?xml version="1.0" encoding="UTF-8"?>
<ns0:FGLEmployeeMessage xmlns:ns0="http://www.fglsports.com/schema/message/employee/v1">
    <ns0:FGLEmployeeHeader>
        <ns0:CrossReferenceValues>
            <ns0:GroupValues>
                <ns0:Name>DEPT_XREF_CD</ns0:Name>
                <ns0:Value>Hardgoods</ns0:Value>
            </ns0:GroupValues>
            <ns0:GroupValues>
                <ns0:Name>DEPT_JOB_CD</ns0:Name>
                <ns0:Value>0307</ns0:Value>
            </ns0:GroupValues>
            <ns0:GroupValues>
                <ns0:Name>WFM_IND</ns0:Name>
                <ns0:Value>Y</ns0:Value>
            </ns0:GroupValues>
            <ns0:GroupValues>
                <ns0:Name>ENCRYPT_KEY</ns0:Name>
                <ns0:Value>MTIzNDU2Nzg5MDEyMzQ1NjEyMzQ1Njc4OTAxMjM0NTY=</ns0:Value>
            </ns0:GroupValues>
            <ns0:GroupValues>
                <ns0:Name>ENCRYPT_KEY_ID</ns0:Name>
                <ns0:Value>1234</ns0:Value>
            </ns0:GroupValues>
        </ns0:CrossReferenceValues>
    </ns0:FGLEmployeeHeader>
    <ns1:FGLEmployee xmlns:ns1="http://www.fglsports.com/schema/arts/employee/v1">
        <ns1:Worker WorkerStatus="TERMINATION">
            <ns1:WorkerID>002080523</ns1:WorkerID>
            <ns1:LegalName>
                <ns1:Name TypeCode="GivenName">BRIAN</ns1:Name>
                <ns1:Name TypeCode="FamilyName">DRUKER</ns1:Name>
            </ns1:LegalName>
            <ns1:WorkerTitle>JOB - 0307</ns1:WorkerTitle>
            <ns1:PersonalInformation>
                <ns1:Birthdate>b334PW1alpE3Bp7CX0rtuXVRgiNmP5+vq/9d+wbVbfo=</ns1:Birthdate>
                <ns1:Address PrimaryFlag="true">
                    <ns1:AddressLine1>JLdeGTO1NZdx2OiAsqBGkQ==</ns1:AddressLine1>
                    <ns1:AddressLine2>CITY AND PROVINCE</ns1:AddressLine2>
                    <ns1:City/>
                    <ns1:Territory>MB</ns1:Territory>
                    <ns1:PostalCode>R0H 0H0</ns1:PostalCode>
                </ns1:Address>
                <ns1:Telephone PrimaryFlag="true" TypeCode="Home" AreaCode="ichliNY9S72jV4fW/NWE5A==">VG03coa4+2nFePE0CikkEA==</ns1:Telephone>
                <ns1:EffectiveDate>2006-06-05</ns1:EffectiveDate>
            </ns1:PersonalInformation>
            <ns1:EmergencyContact>
                <ns1:ContactName>
                    <ns1:FullName>JACQUELINE OH</ns1:FullName>
                </ns1:ContactName>
                <ns1:Relationship>N/A</ns1:Relationship>
                <ns1:Telephone TypeCode="Mobile" AreaCode="BAQnT7a4Bs3fD/svS9Fvww==" PrimaryFlag="true">NhTn9Vb0brCqxYQBhCPcDQ==</ns1:Telephone>
            </ns1:EmergencyContact>
            <ns1:Availability AvailabilityTypeCode="Unavailable">
                <ns1:Interval>
                    <ns1:AvailabilityDate>2006-06-05</ns1:AvailabilityDate>
                </ns1:Interval>
                <ns1:EffectiveDate>2013-11-09</ns1:EffectiveDate>
            </ns1:Availability>
            <ns1:PositionAssignments TypeCode="90" StorePositionFlag="Y">
                <ns1:JobID>0307</ns1:JobID>
                <ns1:PositionID>Y5032</ns1:PositionID>
                <ns1:LocationID>0347</ns1:LocationID>
                <ns1:PositionName>POSITION - Y5032</ns1:PositionName>
                <ns1:EffectiveDate>2013-09-16</ns1:EffectiveDate>
            </ns1:PositionAssignments>
            <ns1:PayRate PayType="Regular">
                <ns1:PayRate>LhjdRC7f6fx2k452xs2IiA==</ns1:PayRate>
                <ns1:PayPeriod>HOURLY</ns1:PayPeriod>
                <ns1:Position>P</ns1:Position>
                <ns1:EffectiveDate>2013-09-16</ns1:EffectiveDate>
            </ns1:PayRate>
            <ns1:Classification FulltimeFlag="true"/>
            <ns1:WorkLocation>
                <ns1:SalesAssociateID>58027</ns1:SalesAssociateID>
                <ns1:HomeStore>347</ns1:HomeStore>
                <ns1:BannerName>SC</ns1:BannerName>
            </ns1:WorkLocation>
        </ns1:Worker>
    </ns1:FGLEmployee>
</ns0:FGLEmployeeMessage>"""
	}  
	
	public static String getLargestXml(String crudOperation) {

	return """<?xml version="1.0" encoding="UTF-8"?>
<p:FGLEmployeeMessage xmlns:p="http://www.fglsports.com/schema/message/employee/v1" xmlns:p1="http://www.fglsports.com/schema/arts/employee/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.fglsports.com/schema/message/employee/v1 ../dat-employee-phl-library/xsd/FGLEmployeeMessage.xsd ">
  <p:FGLEmployeeHeader>
    <p:CrossReferenceValues>
      <p:GroupValues>
        <p:Name>ENCRYPT_KEY</p:Name>
        <p:Value>MzIzNDU2Nzg5MDEyMzQ1Ng==</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_SOURCE_PARTY_ID</p:Name>
        <p:Value>212312312</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_LCTN_NUMERIC_CD</p:Name>
        <p:Value>234234</p:Value>
      </p:GroupValues>
    </p:CrossReferenceValues>
  </p:FGLEmployeeHeader>
  <p1:FGLEmployee>
    <p1:Worker Action="Add" EnrollmentStatus="Enrolled" WorkerClass="RegularWorker" WorkerStatus="" WorkerStatusReason="NewHire" WorkerType="Permanent">
      <p1:WorkerID>p1:WorkerID</p1:WorkerID>
      <p1:LegalName>
        <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
		<p1:Name TypeCode="FamilyName">HARPER</p1:Name>
        <p1:FullName>p1:FullName</p1:FullName>
      </p1:LegalName>
      <p1:DisplayName>p1:DisplayName</p1:DisplayName>
      <p1:WorkerTitle>p1:WorkerTitle</p1:WorkerTitle>
      <p1:PersonalInformation DisabledFlag="true" EmancipatedMinor="NotApplicable">
        <p1:SpecialRequirements>p1:SpecialRequirements</p1:SpecialRequirements>
        <p1:SocialSecurityNumber>0</p1:SocialSecurityNumber>
        <p1:Birthdate>8kClEcUssih4OKNGbg+8dg==</p1:Birthdate>
        <p1:Gender>p1:Gender</p1:Gender>
        <p1:WorkersPhotograph>p1:WorkersPhotograph</p1:WorkersPhotograph>
        <p1:EMail PrimaryFlag="true" TypeCode="Home">
          <p1:EMailAddress>p1:EMailAddress</p1:EMailAddress>
        </p1:EMail>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>93PJPlwth/dTO5PctyHnEuggOJUeBvYDV6PK+NJJnfU=</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>Sofia</p1:City>
          <p1:Territory TypeCode="">Sofia</p1:Territory>
          <p1:PostalCode>1000</p1:PostalCode>
          <p1:Country>Bulgaria</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:Telephone AreaCode="QzEcXXUyJWTMfH/TPak+Zw==" PrimaryFlag="true" TypeCode="Home">z2aNKDlSKJzWxyVGhL49rw==</p1:Telephone>
		<p1:Telephone AreaCode="OC678vr4u3jM+nxbSUZ7Hg==" PrimaryFlag="true" TypeCode="Mobile">2Duf7Ve/Dp/q4XF+1saMcw==</p1:Telephone>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PersonalInformation>
      <p1:SecurityIdentifier TypeCode="UserName">p1:SecurityIdentifier</p1:SecurityIdentifier>
      <p1:Certifications>
        <p1:CertificationName>p1:CertificationName</p1:CertificationName>
        <p1:CertificationNumber>p1:CertificationNumber</p1:CertificationNumber>
        <p1:IssuingBody>p1:IssuingBody</p1:IssuingBody>
        <p1:CertificationDate>2001-01-01</p1:CertificationDate>
        <p1:ExpirationDate>2001-01-01</p1:ExpirationDate>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Certifications>
      <p1:EmergencyContact>
        <p1:ContactName>
          <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
          <p1:FullName>p1:FullName</p1:FullName>
        </p1:ContactName>
        <p1:Relationship>p1:Relationship</p1:Relationship>
        <p1:Telephone AreaCode="vCM6zr+khFbIb8G0t2bVLg==" PrimaryFlag="true" TypeCode="Mobile">1poxK/HYN5+6abUmNPoQgA==</p1:Telephone>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>MA==</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>p1:City</p1:City>
          <p1:Territory TypeCode="">p1:Territory</p1:Territory>
          <p1:PostalCode>p1:PostalCode</p1:PostalCode>
          <p1:Country>p1:Country</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:EmergencyContact>
      <p1:BiometricData>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:BiometricData>
      <p1:School>
        <p1:SchoolName>p1:SchoolName</p1:SchoolName>
        <p1:SchoolDayEndTime Day="Sunday">12:00:00</p1:SchoolDayEndTime>
        <p1:LatestSchoolDayHour>12:00:00</p1:LatestSchoolDayHour>
        <p1:LatestNonSchoolDayHour>12:00:00</p1:LatestNonSchoolDayHour>
        <p1:SchoolGapTime>12:00:00</p1:SchoolGapTime>
        <p1:ContactInformation PrimaryFlag="true">
          <p1:ContactName>
            <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
            <p1:FullName>p1:FullName</p1:FullName>
          </p1:ContactName>
          <p1:Title>p1:Title</p1:Title>
          <p1:Address PrimaryFlag="true" TypeCode="Home">
            <p1:AddressLine1>MA==</p1:AddressLine1>
            <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
            <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
            <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
            <p1:City>p1:City</p1:City>
            <p1:Territory TypeCode="">p1:Territory</p1:Territory>
            <p1:PostalCode>p1:PostalCode</p1:PostalCode>
            <p1:Country>p1:Country</p1:Country>
            <p1:FullAddress>p1:FullAddress</p1:FullAddress>
          </p1:Address>
          <p1:Telephone AreaCode="MA==" PrimaryFlag="true" TypeCode="Home">MA==</p1:Telephone>
        </p1:ContactInformation>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:School>
      <p1:Availability ApprovalFlag="true" AvailabilityTypeCode="Available" PermanentFlag="true" PreferredFlag="true" UnavailabilityDegree="NotAvailable">
        <p1:AvailabilityReason>p1:AvailabilityReason</p1:AvailabilityReason>
        <p1:LocationID>p1:LocationID</p1:LocationID>
        <p1:Interval>
          <p1:AvailabilityDate>2001-01-01</p1:AvailabilityDate>
          <p1:StartTime>12:00:00</p1:StartTime>
          <p1:EndTime>12:00:00</p1:EndTime>
          <p1:Duration UnitOfMeasureCode="Hours">P1D</p1:Duration>
        </p1:Interval>
        <p1:EffectiveDate>2011-02-01</p1:EffectiveDate>
      </p1:Availability>
      <p1:PositionAssignments StorePositionFlag="" TypeCode="">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:PositionID>p1:PositionID</p1:PositionID>
        <p1:LocationID>8977</p1:LocationID>
        <p1:PositionName>p1:PositionName</p1:PositionName>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PositionAssignments>
      <p1:JobQualifications PrimaryJobFlag="true" QualifiedFlag="true" TrainedFlag="false">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:SkillLevel>p1:SkillLevel</p1:SkillLevel>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:JobQualifications>
      <p1:PayRate PayType="Regular">
        <p1:PayRate>/tGoFkGC50kfngMkEhXCQQ==</p1:PayRate>
        <p1:PayPeriod>HOURLY</p1:PayPeriod>
        <p1:Job>p1:Job</p1:Job>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PayRate>
      <p1:Classification CommissionFlag="true" ExemptFlag="true" FulltimeFlag="false" OvertimeEligibleFlag="true" SalaryFlag="true">
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Classification>
      <p1:Accruals TypeCode="Regular">
        <p1:Quantity EntryMethod="Scanned" UnitOfMeasureCode="EA" Units="1">0.0</p1:Quantity>
      </p1:Accruals>
      <p1:SpecialPay TypeCode="Birthday">0.0</p1:SpecialPay>
      <p1:WorkLocation>
        <p1:SalesAssociateID>p1:SalesAssociateID</p1:SalesAssociateID>
        <p1:HomeStore>p1:HomeStore</p1:HomeStore>
        <p1:BannerName>PHL</p1:BannerName>
        <p1:HomeDepartment>p1:HomeDepartment</p1:HomeDepartment>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:WorkLocation>
      <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
    </p1:Worker>
  </p1:FGLEmployee>
</p:FGLEmployeeMessage>"""
	}
	
	public static String getAverageLargeXml(String crudOperation) {

	return """<?xml version="1.0" encoding="UTF-8"?>
<p:FGLEmployeeMessage xmlns:p="http://www.fglsports.com/schema/message/employee/v1" xmlns:p1="http://www.fglsports.com/schema/arts/employee/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.fglsports.com/schema/message/employee/v1 ../dat-employee-phl-library/xsd/FGLEmployeeMessage.xsd ">
  <p:FGLEmployeeHeader>
    <p:CrossReferenceValues>
      <p:GroupValues>
        <p:Name>ENCRYPT_KEY</p:Name>
        <p:Value>MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_SOURCE_PARTY_ID</p:Name>
        <p:Value>212312312</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_LCTN_NUMERIC_CD</p:Name>
        <p:Value>234234</p:Value>
      </p:GroupValues>
    </p:CrossReferenceValues>
	<p:CrossReferenceValues>
      <p:GroupValues>
        <p:Name>ENCRYPT_KEY</p:Name>
        <p:Value>MzIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_SOURCE_PARTY_ID</p:Name>
        <p:Value>2345</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_LCTN_NUMERIC_CD</p:Name>
        <p:Value>655</p:Value>
      </p:GroupValues>
    </p:CrossReferenceValues>
	<p:CrossReferenceValues>
      <p:GroupValues>
        <p:Name>ENCRYPT_KEY</p:Name>
        <p:Value>MzIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTA=</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_SOURCE_PARTY_ID</p:Name>
        <p:Value>43553</p:Value>
      </p:GroupValues>
	  <p:GroupValues>
        <p:Name>PRMRY_LCTN_NUMERIC_CD</p:Name>
        <p:Value>4433</p:Value>
      </p:GroupValues>
    </p:CrossReferenceValues>
  </p:FGLEmployeeHeader>
  <p1:FGLEmployee>
    <p1:Worker Action="Add" EnrollmentStatus="Enrolled" WorkerClass="RegularWorker" WorkerStatus="" WorkerStatusReason="NewHire" WorkerType="Permanent">
      <p1:WorkerID>p1:WorkerID</p1:WorkerID>
      <p1:LegalName>
        <p1:Name Location="First" TypeCode="GivenName">John</p1:Name>
		<p1:Name TypeCode="FamilyName">HARPER</p1:Name>
        <p1:FullName>p1:FullName</p1:FullName>
      </p1:LegalName>
      <p1:DisplayName>p1:DisplayName</p1:DisplayName>
      <p1:WorkerTitle>p1:WorkerTitle</p1:WorkerTitle>
      <p1:PersonalInformation DisabledFlag="true" EmancipatedMinor="NotApplicable">
        <p1:SpecialRequirements>p1:SpecialRequirements</p1:SpecialRequirements>
        <p1:SocialSecurityNumber>0</p1:SocialSecurityNumber>
        <p1:Birthdate>Zp8SWAuJCVEYIcM6sPc/NQ==</p1:Birthdate>
        <p1:Gender>p1:Gender</p1:Gender>
        <p1:WorkersPhotograph>p1:WorkersPhotograph</p1:WorkersPhotograph>
        <p1:EMail PrimaryFlag="true" TypeCode="Home">
          <p1:EMailAddress>p1:EMailAddress</p1:EMailAddress>
        </p1:EMail>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>EtRrgoueWs+YRbrqaWXq3LXPuheGdfCCVtSx6Dh5SG0=</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>Sofia</p1:City>
          <p1:Territory TypeCode="">Sofia</p1:Territory>
          <p1:PostalCode>1000</p1:PostalCode>
          <p1:Country>Bulgaria</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:Telephone AreaCode="lXXUgkCKlBBUPCs2xwOLKg==" PrimaryFlag="true" TypeCode="Home">zmfW/gi/3MkkRj/RRKZR9Q==</p1:Telephone>
		<p1:Telephone AreaCode="LqNKP9tl2aoOv3E7q8tM8A==" PrimaryFlag="true" TypeCode="Mobile">jGan5pZ8/8xnLbqoHeuiqw==</p1:Telephone>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PersonalInformation>
      <p1:SecurityIdentifier TypeCode="UserName">p1:SecurityIdentifier</p1:SecurityIdentifier>
      <p1:Certifications>
        <p1:CertificationName>p1:CertificationName</p1:CertificationName>
        <p1:CertificationNumber>p1:CertificationNumber</p1:CertificationNumber>
        <p1:IssuingBody>p1:IssuingBody</p1:IssuingBody>
        <p1:CertificationDate>2001-01-01</p1:CertificationDate>
        <p1:ExpirationDate>2001-01-01</p1:ExpirationDate>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Certifications>
      <p1:EmergencyContact>
        <p1:ContactName>
          <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
          <p1:FullName>p1:FullName</p1:FullName>
        </p1:ContactName>
        <p1:Relationship>p1:Relationship</p1:Relationship>
        <p1:Telephone AreaCode="L98M51H2PMBW/yEU6D/OXw==" PrimaryFlag="true" TypeCode="Mobile">StijoahyNzR5DovaixcBeA==</p1:Telephone>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>MA==</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>p1:City</p1:City>
          <p1:Territory TypeCode="">p1:Territory</p1:Territory>
          <p1:PostalCode>p1:PostalCode</p1:PostalCode>
          <p1:Country>p1:Country</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:EmergencyContact>
      <p1:BiometricData>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:BiometricData>
      <p1:School>
        <p1:SchoolName>p1:SchoolName</p1:SchoolName>
        <p1:SchoolDayEndTime Day="Sunday">12:00:00</p1:SchoolDayEndTime>
        <p1:LatestSchoolDayHour>12:00:00</p1:LatestSchoolDayHour>
        <p1:LatestNonSchoolDayHour>12:00:00</p1:LatestNonSchoolDayHour>
        <p1:SchoolGapTime>12:00:00</p1:SchoolGapTime>
        <p1:ContactInformation PrimaryFlag="true">
          <p1:ContactName>
            <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
            <p1:FullName>p1:FullName</p1:FullName>
          </p1:ContactName>
          <p1:Title>p1:Title</p1:Title>
          <p1:Address PrimaryFlag="true" TypeCode="Home">
            <p1:AddressLine1>MA==</p1:AddressLine1>
            <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
            <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
            <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
            <p1:City>p1:City</p1:City>
            <p1:Territory TypeCode="">p1:Territory</p1:Territory>
            <p1:PostalCode>p1:PostalCode</p1:PostalCode>
            <p1:Country>p1:Country</p1:Country>
            <p1:FullAddress>p1:FullAddress</p1:FullAddress>
          </p1:Address>
          <p1:Telephone AreaCode="MA==" PrimaryFlag="true" TypeCode="Home">MA==</p1:Telephone>
        </p1:ContactInformation>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:School>
      <p1:Availability ApprovalFlag="true" AvailabilityTypeCode="Available" PermanentFlag="true" PreferredFlag="true" UnavailabilityDegree="NotAvailable">
        <p1:AvailabilityReason>p1:AvailabilityReason</p1:AvailabilityReason>
        <p1:LocationID>p1:LocationID</p1:LocationID>
        <p1:Interval>
          <p1:AvailabilityDate>2001-01-01</p1:AvailabilityDate>
          <p1:StartTime>12:00:00</p1:StartTime>
          <p1:EndTime>12:00:00</p1:EndTime>
          <p1:Duration UnitOfMeasureCode="Hours">P1D</p1:Duration>
        </p1:Interval>
        <p1:EffectiveDate>2011-02-01</p1:EffectiveDate>
      </p1:Availability>
      <p1:PositionAssignments StorePositionFlag="" TypeCode="">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:PositionID>p1:PositionID</p1:PositionID>
        <p1:LocationID>8977</p1:LocationID>
        <p1:PositionName>p1:PositionName</p1:PositionName>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PositionAssignments>
      <p1:JobQualifications PrimaryJobFlag="true" QualifiedFlag="true" TrainedFlag="false">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:SkillLevel>p1:SkillLevel</p1:SkillLevel>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:JobQualifications>
      <p1:PayRate PayType="Regular">
        <p1:PayRate>g25PPByET8ike5SIbpwzIw==</p1:PayRate>
        <p1:PayPeriod>HOURLY</p1:PayPeriod>
        <p1:Job>p1:Job</p1:Job>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PayRate>
      <p1:Classification CommissionFlag="true" ExemptFlag="true" FulltimeFlag="false" OvertimeEligibleFlag="true" SalaryFlag="true">
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Classification>
      <p1:Accruals TypeCode="Regular">
        <p1:Quantity EntryMethod="Scanned" UnitOfMeasureCode="EA" Units="1">0.0</p1:Quantity>
      </p1:Accruals>
      <p1:SpecialPay TypeCode="Birthday">0.0</p1:SpecialPay>
      <p1:WorkLocation>
        <p1:SalesAssociateID>p1:SalesAssociateID</p1:SalesAssociateID>
        <p1:HomeStore>p1:HomeStore</p1:HomeStore>
        <p1:BannerName>PHL</p1:BannerName>
        <p1:HomeDepartment>p1:HomeDepartment</p1:HomeDepartment>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:WorkLocation>
      <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
    </p1:Worker>
	<p1:Worker Action="Add" EnrollmentStatus="Enrolled" WorkerClass="RegularWorker" WorkerStatus="" WorkerStatusReason="NewHire" WorkerType="Permanent">
      <p1:WorkerID>p1:WorkerID</p1:WorkerID>
      <p1:LegalName>
        <p1:Name Location="First" TypeCode="GivenName">Nikola</p1:Name>
		<p1:Name TypeCode="FamilyName">HARPER</p1:Name>
        <p1:FullName>p1:FullName</p1:FullName>
      </p1:LegalName>
      <p1:DisplayName>p1:DisplayName</p1:DisplayName>
      <p1:WorkerTitle>p1:WorkerTitle</p1:WorkerTitle>
      <p1:PersonalInformation DisabledFlag="true" EmancipatedMinor="NotApplicable">
        <p1:SpecialRequirements>p1:SpecialRequirements</p1:SpecialRequirements>
        <p1:SocialSecurityNumber>0</p1:SocialSecurityNumber>
        <p1:Birthdate>v2/LTGhzEf/Cdu++OSMYgg==</p1:Birthdate>
        <p1:Gender>p1:Gender</p1:Gender>
        <p1:WorkersPhotograph>p1:WorkersPhotograph</p1:WorkersPhotograph>
        <p1:EMail PrimaryFlag="true" TypeCode="Home">
          <p1:EMailAddress>p1:EMailAddress</p1:EMailAddress>
        </p1:EMail>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>eq1VCS4+7jPyz/AuHJTtxQSD1wLp/QmvWLJ/FHjhokQ=</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>Sofia</p1:City>
          <p1:Territory TypeCode="">Sofia</p1:Territory>
          <p1:PostalCode>1000</p1:PostalCode>
          <p1:Country>Bulgaria</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:Telephone AreaCode="iT9L1e0dha8OascVDzUc2g==" PrimaryFlag="true" TypeCode="Home">tJTLWwDGMzn5suHETSKXLw==</p1:Telephone>
		<p1:Telephone AreaCode="MwHk9LwMJzOVW1F8N6dmOQ==" PrimaryFlag="true" TypeCode="Mobile">6D1cfercd41QTrL5ZES8CA==</p1:Telephone>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PersonalInformation>
      <p1:SecurityIdentifier TypeCode="UserName">p1:SecurityIdentifier</p1:SecurityIdentifier>
      <p1:Certifications>
        <p1:CertificationName>p1:CertificationName</p1:CertificationName>
        <p1:CertificationNumber>p1:CertificationNumber</p1:CertificationNumber>
        <p1:IssuingBody>p1:IssuingBody</p1:IssuingBody>
        <p1:CertificationDate>2001-01-01</p1:CertificationDate>
        <p1:ExpirationDate>2001-01-01</p1:ExpirationDate>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Certifications>
      <p1:EmergencyContact>
        <p1:ContactName>
          <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
          <p1:FullName>p1:FullName</p1:FullName>
        </p1:ContactName>
        <p1:Relationship>p1:Relationship</p1:Relationship>
        <p1:Telephone AreaCode="PQ/Qg/gA65qMvUJYQNXu3A==" PrimaryFlag="true" TypeCode="Mobile">ICWSFAuK9A/jKZE/iZ/9Hw==</p1:Telephone>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>MA==</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>p1:City</p1:City>
          <p1:Territory TypeCode="">p1:Territory</p1:Territory>
          <p1:PostalCode>p1:PostalCode</p1:PostalCode>
          <p1:Country>p1:Country</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:EmergencyContact>
      <p1:BiometricData>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:BiometricData>
      <p1:School>
        <p1:SchoolName>p1:SchoolName</p1:SchoolName>
        <p1:SchoolDayEndTime Day="Sunday">12:00:00</p1:SchoolDayEndTime>
        <p1:LatestSchoolDayHour>12:00:00</p1:LatestSchoolDayHour>
        <p1:LatestNonSchoolDayHour>12:00:00</p1:LatestNonSchoolDayHour>
        <p1:SchoolGapTime>12:00:00</p1:SchoolGapTime>
        <p1:ContactInformation PrimaryFlag="true">
          <p1:ContactName>
            <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
            <p1:FullName>p1:FullName</p1:FullName>
          </p1:ContactName>
          <p1:Title>p1:Title</p1:Title>
          <p1:Address PrimaryFlag="true" TypeCode="Home">
            <p1:AddressLine1>MA==</p1:AddressLine1>
            <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
            <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
            <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
            <p1:City>p1:City</p1:City>
            <p1:Territory TypeCode="">p1:Territory</p1:Territory>
            <p1:PostalCode>p1:PostalCode</p1:PostalCode>
            <p1:Country>p1:Country</p1:Country>
            <p1:FullAddress>p1:FullAddress</p1:FullAddress>
          </p1:Address>
          <p1:Telephone AreaCode="MA==" PrimaryFlag="true" TypeCode="Home">MA==</p1:Telephone>
        </p1:ContactInformation>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:School>
      <p1:Availability ApprovalFlag="true" AvailabilityTypeCode="Available" PermanentFlag="true" PreferredFlag="true" UnavailabilityDegree="NotAvailable">
        <p1:AvailabilityReason>p1:AvailabilityReason</p1:AvailabilityReason>
        <p1:LocationID>p1:LocationID</p1:LocationID>
        <p1:Interval>
          <p1:AvailabilityDate>2001-01-01</p1:AvailabilityDate>
          <p1:StartTime>12:00:00</p1:StartTime>
          <p1:EndTime>12:00:00</p1:EndTime>
          <p1:Duration UnitOfMeasureCode="Hours">P1D</p1:Duration>
        </p1:Interval>
        <p1:EffectiveDate>2011-02-01</p1:EffectiveDate>
      </p1:Availability>
      <p1:PositionAssignments StorePositionFlag="" TypeCode="">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:PositionID>p1:PositionID</p1:PositionID>
        <p1:LocationID>8977</p1:LocationID>
        <p1:PositionName>p1:PositionName</p1:PositionName>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PositionAssignments>
      <p1:JobQualifications PrimaryJobFlag="true" QualifiedFlag="true" TrainedFlag="false">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:SkillLevel>p1:SkillLevel</p1:SkillLevel>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:JobQualifications>
      <p1:PayRate PayType="Regular">
        <p1:PayRate>+GEU46rNOnApFrC0HNVJqQ==</p1:PayRate>
        <p1:PayPeriod>HOURLY</p1:PayPeriod>
        <p1:Job>p1:Job</p1:Job>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PayRate>
      <p1:Classification CommissionFlag="true" ExemptFlag="true" FulltimeFlag="false" OvertimeEligibleFlag="true" SalaryFlag="true">
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Classification>
      <p1:Accruals TypeCode="Regular">
        <p1:Quantity EntryMethod="Scanned" UnitOfMeasureCode="EA" Units="1">0.0</p1:Quantity>
      </p1:Accruals>
      <p1:SpecialPay TypeCode="Birthday">0.0</p1:SpecialPay>
      <p1:WorkLocation>
        <p1:SalesAssociateID>p1:SalesAssociateID</p1:SalesAssociateID>
        <p1:HomeStore>p1:HomeStore</p1:HomeStore>
        <p1:BannerName>PHL</p1:BannerName>
        <p1:HomeDepartment>p1:HomeDepartment</p1:HomeDepartment>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:WorkLocation>
      <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
    </p1:Worker>
	<p1:Worker Action="Add" EnrollmentStatus="Enrolled" WorkerClass="RegularWorker" WorkerStatus="" WorkerStatusReason="NewHire" WorkerType="Permanent">
      <p1:WorkerID>p1:WorkerID</p1:WorkerID>
      <p1:LegalName>
        <p1:Name Location="First" TypeCode="GivenName">Jane</p1:Name>
		<p1:Name TypeCode="FamilyName">HARPER</p1:Name>
        <p1:FullName>p1:FullName</p1:FullName>
      </p1:LegalName>
      <p1:DisplayName>p1:DisplayName</p1:DisplayName>
      <p1:WorkerTitle>p1:WorkerTitle</p1:WorkerTitle>
      <p1:PersonalInformation DisabledFlag="true" EmancipatedMinor="NotApplicable">
        <p1:SpecialRequirements>p1:SpecialRequirements</p1:SpecialRequirements>
        <p1:SocialSecurityNumber>0</p1:SocialSecurityNumber>
        <p1:Birthdate>FX/swhFP2+Q5ULWlhxcjCA==</p1:Birthdate>
        <p1:Gender>p1:Gender</p1:Gender>
        <p1:WorkersPhotograph>p1:WorkersPhotograph</p1:WorkersPhotograph>
        <p1:EMail PrimaryFlag="true" TypeCode="Home">
          <p1:EMailAddress>p1:EMailAddress</p1:EMailAddress>
        </p1:EMail>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>UADIEKVP756kGsrBKwCSn9YVLaK53IAcHm6zWwtww6c=</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>Sofia</p1:City>
          <p1:Territory TypeCode="">Sofia</p1:Territory>
          <p1:PostalCode>1000</p1:PostalCode>
          <p1:Country>Bulgaria</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:Telephone AreaCode="Vrz3EJlqgxGwLXlws+na2g==" PrimaryFlag="true" TypeCode="Home">RWjiHYsRH4q+2jLQYzCyNQ==</p1:Telephone>
		<p1:Telephone AreaCode="kI691mwa3XW/pI7Kd9SL0A==" PrimaryFlag="true" TypeCode="Mobile">sSr1V3acaZRH59xV2HOoyw==</p1:Telephone>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PersonalInformation>
      <p1:SecurityIdentifier TypeCode="UserName">p1:SecurityIdentifier</p1:SecurityIdentifier>
      <p1:Certifications>
        <p1:CertificationName>p1:CertificationName</p1:CertificationName>
        <p1:CertificationNumber>p1:CertificationNumber</p1:CertificationNumber>
        <p1:IssuingBody>p1:IssuingBody</p1:IssuingBody>
        <p1:CertificationDate>2001-01-01</p1:CertificationDate>
        <p1:ExpirationDate>2001-01-01</p1:ExpirationDate>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Certifications>
      <p1:EmergencyContact>
        <p1:ContactName>
          <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
          <p1:FullName>p1:FullName</p1:FullName>
        </p1:ContactName>
        <p1:Relationship>p1:Relationship</p1:Relationship>
        <p1:Telephone AreaCode="NkM4UQmSK5/eJharohJ6PA==" PrimaryFlag="true" TypeCode="Mobile">BV6o3U69EIEVKuOuS2Jhxw==</p1:Telephone>
        <p1:Address PrimaryFlag="true" TypeCode="Home">
          <p1:AddressLine1>MA==</p1:AddressLine1>
          <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
          <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
          <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
          <p1:City>p1:City</p1:City>
          <p1:Territory TypeCode="">p1:Territory</p1:Territory>
          <p1:PostalCode>p1:PostalCode</p1:PostalCode>
          <p1:Country>p1:Country</p1:Country>
          <p1:FullAddress>p1:FullAddress</p1:FullAddress>
        </p1:Address>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:EmergencyContact>
      <p1:BiometricData>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:BiometricData>
      <p1:School>
        <p1:SchoolName>p1:SchoolName</p1:SchoolName>
        <p1:SchoolDayEndTime Day="Sunday">12:00:00</p1:SchoolDayEndTime>
        <p1:LatestSchoolDayHour>12:00:00</p1:LatestSchoolDayHour>
        <p1:LatestNonSchoolDayHour>12:00:00</p1:LatestNonSchoolDayHour>
        <p1:SchoolGapTime>12:00:00</p1:SchoolGapTime>
        <p1:ContactInformation PrimaryFlag="true">
          <p1:ContactName>
            <p1:Name Location="First" TypeCode="GivenName">p1:Name</p1:Name>
            <p1:FullName>p1:FullName</p1:FullName>
          </p1:ContactName>
          <p1:Title>p1:Title</p1:Title>
          <p1:Address PrimaryFlag="true" TypeCode="Home">
            <p1:AddressLine1>MA==</p1:AddressLine1>
            <p1:AddressLine2>p1:AddressLine2</p1:AddressLine2>
            <p1:AddressLine3>p1:AddressLine3</p1:AddressLine3>
            <p1:AddressLine4>p1:AddressLine4</p1:AddressLine4>
            <p1:City>p1:City</p1:City>
            <p1:Territory TypeCode="">p1:Territory</p1:Territory>
            <p1:PostalCode>p1:PostalCode</p1:PostalCode>
            <p1:Country>p1:Country</p1:Country>
            <p1:FullAddress>p1:FullAddress</p1:FullAddress>
          </p1:Address>
          <p1:Telephone AreaCode="MA==" PrimaryFlag="true" TypeCode="Home">MA==</p1:Telephone>
        </p1:ContactInformation>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:School>
      <p1:Availability ApprovalFlag="true" AvailabilityTypeCode="Available" PermanentFlag="true" PreferredFlag="true" UnavailabilityDegree="NotAvailable">
        <p1:AvailabilityReason>p1:AvailabilityReason</p1:AvailabilityReason>
        <p1:LocationID>p1:LocationID</p1:LocationID>
        <p1:Interval>
          <p1:AvailabilityDate>2001-01-01</p1:AvailabilityDate>
          <p1:StartTime>12:00:00</p1:StartTime>
          <p1:EndTime>12:00:00</p1:EndTime>
          <p1:Duration UnitOfMeasureCode="Hours">P1D</p1:Duration>
        </p1:Interval>
        <p1:EffectiveDate>2011-02-01</p1:EffectiveDate>
      </p1:Availability>
      <p1:PositionAssignments StorePositionFlag="" TypeCode="">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:PositionID>p1:PositionID</p1:PositionID>
        <p1:LocationID>8977</p1:LocationID>
        <p1:PositionName>p1:PositionName</p1:PositionName>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PositionAssignments>
      <p1:JobQualifications PrimaryJobFlag="true" QualifiedFlag="true" TrainedFlag="false">
        <p1:JobID>p1:JobID</p1:JobID>
        <p1:SkillLevel>p1:SkillLevel</p1:SkillLevel>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:JobQualifications>
      <p1:PayRate PayType="Regular">
        <p1:PayRate>PyIBwkPLEtTOzX92f44/Pg==</p1:PayRate>
        <p1:PayPeriod>HOURLY</p1:PayPeriod>
        <p1:Job>p1:Job</p1:Job>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:PayRate>
      <p1:Classification CommissionFlag="true" ExemptFlag="true" FulltimeFlag="false" OvertimeEligibleFlag="true" SalaryFlag="true">
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:Classification>
      <p1:Accruals TypeCode="Regular">
        <p1:Quantity EntryMethod="Scanned" UnitOfMeasureCode="EA" Units="1">0.0</p1:Quantity>
      </p1:Accruals>
      <p1:SpecialPay TypeCode="Birthday">0.0</p1:SpecialPay>
      <p1:WorkLocation>
        <p1:SalesAssociateID>p1:SalesAssociateID</p1:SalesAssociateID>
        <p1:HomeStore>p1:HomeStore</p1:HomeStore>
        <p1:BannerName>PHL</p1:BannerName>
        <p1:HomeDepartment>p1:HomeDepartment</p1:HomeDepartment>
        <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
      </p1:WorkLocation>
      <p1:EffectiveDate>2001-01-01</p1:EffectiveDate>
    </p1:Worker>
  </p1:FGLEmployee>
</p:FGLEmployeeMessage>"""
	}
}
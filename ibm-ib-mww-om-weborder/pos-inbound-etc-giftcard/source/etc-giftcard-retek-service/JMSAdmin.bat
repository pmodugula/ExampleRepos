@echo off

rem ----------------------------------------------
rem  IBM Websphere MQ JMS Admin Tool Execution Script
rem  for Windows NT
rem
rem    <copyright 
rem    notice="lm-source-program" 
rem    pids="5724-H72,5655-R36,5655-L82,5724-L26," 
rem    years="2008,2012" 
rem    crc="3635869111" > 
rem   Licensed Materials - Property of IBM  
rem    
rem   5724-H72,5655-R36,5655-L82,5724-L26, 
rem    
rem   (C) Copyright IBM Corp. 2008, 2012 All Rights Reserved.  
rem    
rem   US Government Users Restricted Rights - Use, duplication or  
rem   disclosure restricted by GSA ADP Schedule Contract with  
rem   IBM Corp.  
rem    </copyright> 
rem ----------------------------------------------

cls

rem Get the console.encoding to set for JMSAdmin.
rem This is because, with some IBM Windows JDKs and certain codepages,  
rem the System properties file.encoding and console.encoding 
rem have to be the same to avoid corruption of particular special characters.
for /F %%x in ('java com.ibm.msg.client.commonservices.nls.CodepageSetUp') do set X=%%x

java -Dfile.encoding=%X% -Dcom.ibm.msg.client.commonservices.log.outputName="%MQ_JAVA_DATA_PATH%"\log -Dcom.ibm.msg.client.commonservices.trace.outputName="%MQ_JAVA_DATA_PATH%"\errors -DMQ_JAVA_INSTALL_PATH="%MQ_JAVA_INSTALL_PATH%" com.ibm.mq.jms.admin.JMSAdmin %1 %2 %3 %4 %5


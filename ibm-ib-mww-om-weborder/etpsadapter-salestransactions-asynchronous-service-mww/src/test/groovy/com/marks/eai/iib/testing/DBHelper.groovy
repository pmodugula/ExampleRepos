package com.marks.eai.iib.testing

import groovy.sql.Sql

import java.sql.Connection
import java.sql.DriverManager

/**
 * Created by wrhett on 08/03/2016.
 */
class DBHelper {


    static def url
    static def username
    static def password
    static def oracleConn
	static def schemaName

    static{

		username = "IIB"
		password = "ibm11d3v3"
		//url		 = "jdbc:oracle:thin:@mwwaxdb201.mww.lan:1521:STGDEV"
		url		 = "jdbc:oracle:thin:@10.103.2.201:1521:STGDEV"
		oracleConn = Sql.newInstance("${url}", "${username}","${password}", "oracle.jdbc.driver.OracleDriver")

    }

	
	/**
	 * delete data in the INT_PROCESS_CONTROL.TRNSCT_ETPS_SEQUENCE for store 99 - so each test
	 * @param transactionNumber
	 * @return
	 */
	static cleanSequenceTable(storeId){
		
		def sql = "delete from INT_PROCESS_CONTROL.TRNSCT_ETPS_SEQUENCE where STOREID=${storeId}"
    	println "executing ${sql}"
		oracleConn.execute sql.toString()

	}
	




}

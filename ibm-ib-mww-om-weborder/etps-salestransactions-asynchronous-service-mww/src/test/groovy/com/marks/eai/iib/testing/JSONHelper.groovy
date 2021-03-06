package com.marks.eai.iib.testing

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Created by rhett on 2016-02-23.
 */
class JSONHelper {

    static def setUUIDs(String content) {

        // explicitly set any tenders.successfulPedTransactionUuid AND allPedTransactions.uuid to 12345 (these are randomly generated by IIB so need to fudge them to a static value)
        //builder.retailTransaction.tenders.allPedTransactions.uuid=='12345'
        //println(builder.toPrettyString())

        def slurped = new JsonSlurper().parseText(content)
        def builder = new JsonBuilder(slurped)
        builder.content.retailTransaction.tenders.each{
            if (it.successfulPedTransactionUuid)
                it.successfulPedTransactionUuid = '12345'
        }

        builder.content.retailTransaction.allPedTransactions.each{
            if (it.uuid)
                it.uuid = '12345'
        }

        builder.toPrettyString()


    }


}

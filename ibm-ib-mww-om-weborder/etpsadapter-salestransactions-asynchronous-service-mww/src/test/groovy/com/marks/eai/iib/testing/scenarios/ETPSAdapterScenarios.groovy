package com.marks.eai.iib.testing.scenarios

import com.marks.eai.iib.testing.scenarios.InputBean as I

/**
 * ETPSAdapterScenarios

   Template
      specList << new I(
          jiraID: 'TODO: enter JiraID',
          description: "TODO: please update with description",
          sourceXpaths: ['somexpath':'some_value_to_set'],
          targetXpaths: ['somexpath':'some_value_to_assert])

    NOTE: the sourceXpath and targetXpaths are just HashMaps
 */
class ETPSAdapterScenarios {


    static List<I> specList

    static {
        specList = []

        /* 0 - Explicitly setting store input*/
//         specList << new I(
//                 jiraID: 'POE-xxxx',
//                 description: "FF legacy fulfilment",
//                 sourceXpaths: ['//submitTxnRequest/store':'560'],
//                 targetXpaths: ['//Messages/POSLog/Transaction/RetailStoreID':'560'])

        /* 1 - SourceSystem to Registert*/
        specList << new I(
                jiraID: 'POE-xxxx',
                description: "FF legacy fulfilment",
                sourceXpaths: ['//submitTxnRequest/consumerid':'FF'],
                targetXpaths: ['//Messages/POSLog/Transaction/WorkstationID':'61'])


        /* 2 - Test sequence number - 999/61 is deleted each time*/
//        specList << new I(
//                jiraID: 'POE-xxxx',
//                description: "FF legacy fulfilment",
//                sourceXpaths: ['//submitTxnRequest/consumerid':'FF'],
//                targetXpaths: ['//Messages/POSLog/Transaction/WorkstationID':'61'])



    }
}
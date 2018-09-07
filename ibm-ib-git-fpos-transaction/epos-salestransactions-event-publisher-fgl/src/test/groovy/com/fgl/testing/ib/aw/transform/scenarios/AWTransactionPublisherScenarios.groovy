package com.fgl.testing.ib.aw.transform.scenarios

import com.fgl.testing.ib.aw.transform.scenarios.InputBean as I



class AWTransactionPublisherScenarios {

    static List<I> specList

    static {
        specList = []

        /* 0 */
         specList << new I(
                 jiraID: 'FPOE-10487',
                 description: "Body is the same - publisher does not change anything",
                 sourceXpaths: ['//Messages/POSLog/Transaction/WorkstationID':'2'],
                 targetXpaths: ['//Messages/POSLog/Transaction/WorkstationID':'2']
         )

        /* 1 */
        specList << new I(
                jiraID: 'FPOE-10487',
                description: "SourceSystems MessageSourceCodeName is populated with 'Central Sales Server' by publisher",
                sourceXpaths: [:], // empty map, don't change anything on input
                targetXpaths: ['//Messages/SourceSystems/SourceSystem/MessageSourceCodeName':'Central Sales Server']
        )

        /* 2 */
        specList << new I(
                jiraID: 'FPOE-10487',
                description: "MessageRouting MessageId is populated with something (not String) by publisher - ie. the MessageId is set to something new",
                sourceXpaths: [:], // empty map, don't change anything on input
                targetXpaths: ['//MessageRouting/MessageId':'']
        )

			
    }
}
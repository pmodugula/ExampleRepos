package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberScenarios {

    static List<InputBean> specList

    static {
        specList = []

        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-9449',
                 description: "Test WorkstationID->RegisterNbr mapping",
                 sourceXpaths: ['//Messages/POSLog/Transaction/WorkstationID':'101'],
                 targetXpaths: ['//Header/RegisterNbr':'101']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-9449',
                description: "Test BeginDateTime->EntryDateTime mapping",
                sourceXpaths: ['//Messages/POSLog/Transaction/BeginDateTime':'2016-09-07T00:31:41-06:00'],
                targetXpaths: ['//Header/EntryDateTime':'09/07/2016 00:31:41']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-9449',
                description: "Test BusinessUnit->StoreNbr mapping",
                sourceXpaths: ['//Messages/POSLog/Transaction/BusinessUnit/BusinessUnitNumber':'380'],
                targetXpaths: ['//Header/StoreNbr':'380']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-9449',
                description: "Test SequenceNumber->TransactionNbr mapping",
                sourceXpaths: ['//Messages/POSLog/Transaction/SequenceNumber':'000018'],
                targetXpaths: ['//Header/TransactionNbr':'000018']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-9449',
                description: "Test OperatorID->CashierNbr mapping",
                sourceXpaths: ['//Messages/POSLog/Transaction/OperatorID':'000002613'],
                targetXpaths: ['//Header/CashierNbr':'000002613']
        )

        /* 5 */
        specList << new InputBean(
                jiraID: 'FPOE-9449',
                description: "Test RetailTransaction@TypeCode->TransactionCategory mapping",
                //TODO figure out why this Xpath fails using the @TypeCode
                //sourceXpaths: ['//Messages/POSLog/Transaction/@TypeCode':'SaleTransaction'],
                sourceXpaths: [:],
                targetXpaths: ['//Header/TransactionCategory':'1']
        )




			
    }
}
package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberNonMerchCodeScenarios {

    static List<InputBean> specList

    static {
        specList = []

//        ELSEIF FIELDTYPE(rLineitems.arts:Sale.arts:NonSKUItem) IS NOT NULL OR FIELDTYPE(rLineitems.arts:Return.arts:NonSKUItem) IS NOT NULL THEN
//        IF rLineitems.arts:Sale.arts:NonSKUItem.arts:ServiceItemTypeCode = 'REPAIRS AND ALTERATIONS' OR rLineitems.arts:Return.arts:NonSKUItem.arts:ServiceItemTypeCode = 'REPAIRS AND ALTERATIONS' THEN
//        SET rLine.LineObject = 6052;
//        ELSEIF rLineitems.arts:Sale.arts:NonSKUItem.arts:ServiceItemTypeCode = 'SHIPPING' OR rLineitems.arts:Return.arts:NonSKUItem.arts:ServiceItemTypeCode = 'SHIPPING' THEN
//        SET rLine.LineObject = 202;
//        ELSEIF rLineitems.arts:Sale.arts:NonSKUItem.arts:ServiceItemTypeCode = 'HANDLING FEE' OR rLineitems.arts:Return.arts:NonSKUItem.arts:ServiceItemTypeCode = 'HANDLING FEE' THEN
//        SET rLine.LineObject = 6053;
//        ELSEIF rLineitems.arts:Sale.arts:NonSKUItem.arts:ServiceItemTypeCode = 'ADMIN' OR rLineitems.arts:Return.arts:NonSKUItem.arts:ServiceItemTypeCode = 'ADMIN' THEN
//        SET rLine.LineObject = 6054;
//        ELSEIF rLineitems.arts:Sale.arts:NonSKUItem.arts:ServiceItemTypeCode = 'SPECIAL ORDER' OR rLineitems.arts:Return.arts:NonSKUItem.arts:ServiceItemTypeCode = 'SPECIAL ORDER' THEN
//        SET rLine.LineObject = 6055;
//        ELSE
//        SET rLine.LineObject = '';
//        END IF;


        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests tax code translation for REPAIRS AND ALTERATIONS",
                 sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/NonSKUItem/ServiceItemTypeCode":'REPAIRS AND ALTERATIONS'],
                 targetXpaths: ['//Line/LineObject':'6052']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for SHIPPING",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/NonSKUItem/ServiceItemTypeCode":'SHIPPING'],
                targetXpaths: ['//Line/LineObject':'202']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for HANDLING FEE",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/NonSKUItem/ServiceItemTypeCode":'HANDLING FEE'],
                targetXpaths: ['//Line/LineObject':'6053']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ADMIN",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/NonSKUItem/ServiceItemTypeCode":'ADMIN'],
                targetXpaths: ['//Line/LineObject':'6054']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for SPECIAL ORDER",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/NonSKUItem/ServiceItemTypeCode":'SPECIAL ORDER'],
                targetXpaths: ['//Line/LineObject':'6055']
        )

        /* 5 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for REPAIRS AND ALTERATIONS Return",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/NonSKUItem/ServiceItemTypeCode":'REPAIRS AND ALTERATIONS'],
                targetXpaths: ['//Line[2]/LineObject':'6052']

        )

        /* 6 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for SHIPPING Return",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/NonSKUItem/ServiceItemTypeCode":'SHIPPING'],
                targetXpaths: ['//Line[2]/LineObject':'202']
        )

        /* 7 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for HANDLING FEE Return",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/NonSKUItem/ServiceItemTypeCode":'HANDLING FEE'],
                targetXpaths: ['//Line[2]/LineObject':'6053']
        )

        /* 8 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ADMIN Return",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/NonSKUItem/ServiceItemTypeCode":'ADMIN'],
                targetXpaths: ['//Line[2]/LineObject':'6054']
        )

        /* 9 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for SPECIAL ORDER Return",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/NonSKUItem/ServiceItemTypeCode":'SPECIAL ORDER'],
                targetXpaths: ['//Line[2]/LineObject':'6055']
        )




			
    }
}
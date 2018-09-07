package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberTaxCodeScenarios {

    static List<InputBean> specList

    static {
        specList = []

//        IF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType ='GST' THEN
//        --							SET rLine.LineObject = '500';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType ='BCPST' THEN
//        --							SET rLine.LineObject = '502';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'MBPST' THEN
//        --							SET rLine.LineObject = '503';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'ONTPST' THEN
//        --							SET rLine.LineObject = '507';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'PEIPST' THEN
//        --							SET rLine.LineObject = '508';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'QST' THEN
//        --							SET rLine.LineObject = '509';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'SKPST' THEN
//        --							SET rLine.LineObject = '510';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'NFHST' THEN
//        --							SET rLine.LineObject = '521';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'NSHST' THEN
//        --							SET rLine.LineObject = '522';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'NBHST' THEN
//        --							SET rLine.LineObject = '523';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'BCHSTP' THEN
//        --							SET rLine.LineObject = '526';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'ONHSTP' THEN
//        --							SET rLine.LineObject = '527';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'NSHSTP' THEN
//        --							SET rLine.LineObject = '528';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'NSHSTF' THEN
//        --							SET rLine.LineObject = '529';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'ONHSTF' THEN
//        --							SET rLine.LineObject = '530';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'BCHSTF' THEN
//        --							SET rLine.LineObject = '531';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'PEHSTF' THEN
//        --							SET rLine.LineObject = '532';
//        --						ELSEIF rLineitems.arts:Tax.(XMLNSC.Attribute)TaxType = 'PEHSTP' THEN
//        --							SET rLine.LineObject = '533';
//        --						ELSE
//        --							SET rLine.LineObject = '';
//        --						END IF;


        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests tax code translation for BSTPST",
                 sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'BCPST'],
                 targetXpaths: ['//Line[2]/LineObject':'502']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for MBPST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'MBPST'],
                targetXpaths: ['//Line[2]/LineObject':'503']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ONTPST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'ONTPST'],
                targetXpaths: ['//Line[2]/LineObject':'507']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for PEIPST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'PEIPST'],
                targetXpaths: ['//Line[2]/LineObject':'508']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for QST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'QST'],
                targetXpaths: ['//Line[2]/LineObject':'509']
        )

        /* 5 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for SKPST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'SKPST'],
                targetXpaths: ['//Line[2]/LineObject':'510']
        )

        /* 6 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for NFHST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'NFHST'],
                targetXpaths: ['//Line[2]/LineObject':'521']
        )

        /* 7 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for NSHST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'NSHST'],
                targetXpaths: ['//Line[2]/LineObject':'522']
        )

        /* 8 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for NBHST",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'NBHST'],
                targetXpaths: ['//Line[2]/LineObject':'523']
        )

        /* 9 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for BCHSTP",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'BCHSTP'],
                targetXpaths: ['//Line[2]/LineObject':'526']
        )

        /* 10 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ONHSTP",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'ONHSTP'],
                targetXpaths: ['//Line[2]/LineObject':'527']
        )

        /* 11 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for NBHSTP",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'NSHSTP'],
                targetXpaths: ['//Line[2]/LineObject':'528']
        )

        /* 12 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for NSHSTF",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'NSHSTF'],
                targetXpaths: ['//Line[2]/LineObject':'529']
        )

        /* 13 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ONHSTF",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'ONHSTF'],
                targetXpaths: ['//Line[2]/LineObject':'530']
        )

        /* 14 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for BCHSTF",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'BCHSTF'],
                targetXpaths: ['//Line[2]/LineObject':'531']
        )

        /* 15 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for PEHSTF",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'PEHSTF'],
                targetXpaths: ['//Line[2]/LineObject':'532']
        )

        /* 16 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for PEHSTP",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'PEHSTP'],
                targetXpaths: ['//Line[2]/LineObject':'533']
        )

        /* 17 Test invalid code */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for ABCDEF",
                sourceXpaths: ["//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tax/@TaxType":'ABCDEF'],
                targetXpaths: ['//Line[2]/LineObject':'']
        )


//



			
    }
}
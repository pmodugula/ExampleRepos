package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberTenderScenarios {

    static List<InputBean> specList

    static {
        specList = []

//        ELSEIF FIELDNAME(rLineitems.arts:Tender)='Tender' THEN
//        IF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'CreditDebit' AND NOT EXISTS(rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType[]) THEN
//        SET rLine.LineObject = '603';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'CreditDebit' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'Visa' THEN
//        SET rLine.LineObject = '604';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'CreditDebit' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'MasterCard' THEN
//        SET rLine.LineObject = '605';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'CreditDebit' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'Amex' THEN
//        SET rLine.LineObject = '606';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'Cash' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'CAD' THEN
//        SET rLine.LineObject = '600';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'Cash' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'USD' THEN
//        SET rLine.LineObject = '613';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'Cash' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'EXCHANGE' THEN
//        SET rLine.LineObject = '613';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'ChargeTender' THEN
//        SET rLine.LineObject = '616';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCard' AND rLineitems.arts:Tender.(XMLNSC.Attribute)TypeCode = 'Refund' AND rInPOSLog.arts:Transaction.(XMLNSC.Attribute)TypeCode = 'TenderExchangeTransaction' THEN
//        SET rLine.LineObject = '625';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCard' AND rLineitems.arts:Tender.(XMLNSC.Attribute)TypeCode = 'Sale' AND rInPOSLog.arts:Transaction.(XMLNSC.Attribute)TypeCode = 'TenderExchangeTransaction' THEN
//        SET rLine.LineObject = '621';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCard' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'SPORTMARTGIFTCARD' THEN
//        SET rLine.LineObject = '626';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCard' AND rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType = 'ATHLETESWORLDGIFTCARD' THEN
//        SET rLine.LineObject = '6030';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCard' AND FIELDTYPE(rLineitems.arts:Tender.(XMLNSC.Attribute)SubTenderType) IS NULL THEN
//        SET rLine.LineObject = '625';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'GiftCertificate' THEN
//        SET rLine.LineObject = '615';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'Cheque' THEN
//        SET rLine.LineObject = '601';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'StorePromo' THEN
//        SET rLine.LineObject = '629';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'MallGiftCertificate' THEN
//        SET rLine.LineObject = '602';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'Coupon' THEN
//        SET rLine.LineObject = '4102';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'LoyaltyRedemption' THEN
//        SET rLine.LineObject = '6032';
//        ELSEIF rLineitems.arts:Tender.(XMLNSC.Attribute)TenderType = 'PennyRounding' THEN
//        SET rLine.LineObject = '6029';
//        ELSE
//        SET rLine.LineObject = '';
//        END IF;


        /* 0 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for CASH CURRENCY CAD",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Cash','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'CAD'],
                targetXpaths: ['//Line[3]/LineObject':'600']
        )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for CASH CURRENCY USD",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Cash','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'USD'],
                targetXpaths: ['//Line[3]/LineObject':'613']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for CASH CURRENCY EXCHANGE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Cash','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'EXCHANGE'],
                targetXpaths: ['//Line[3]/LineObject':'613']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for CASH CURRENCY EXCHANGE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Cash','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'EXCHANGE'],
                targetXpaths: ['//Line[3]/LineObject':'613']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for CreditDebit",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'CreditDebit'],
                targetXpaths: ['//Line[3]/LineObject':'603']
        )

        /* 5 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for Visa",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'CreditDebit','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'Visa'],
                targetXpaths: ['//Line[3]/LineObject':'604']
        )

        /* 6 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for MasterCard",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'CreditDebit','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'MasterCard'],
                targetXpaths: ['//Line[3]/LineObject':'605']
        )

        /* 7 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for Amex",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'CreditDebit','//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'Amex'],
                targetXpaths: ['//Line[3]/LineObject':'606']
        )

        /* 8 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for ChargeTender",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'ChargeTender'],
                targetXpaths: ['//Line[3]/LineObject':'616']
        )


        /* 9 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCard TenderExchange Refund",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCard',
                               '//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TypeCode':'Refund',
                               '//Messages/POSLog/Transaction/@TypeCode':'TenderExchangeTransaction'
                ],
                targetXpaths: ['//Line[3]/LineObject':'625']
        )

        /* 10 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCard TenderExchange Sale",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCard',
                               '//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'Sale',
                               '//Messages/POSLog/Transaction/@TypeCode':'TenderExchangeTransaction'
                ],
                targetXpaths: ['//Line[3]/LineObject':'621']
        )

        /* 11 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCard SPORTMARTGIFTCARD",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCard',
                               '//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'SPORTMARTGIFTCARD'
                ],
                targetXpaths: ['//Line[3]/LineObject':'626']
        )

        /* 12 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCard ATHLETESWORLDGIFTCARD",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCard',
                               '//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@SubTenderType':'ATHLETESWORLDGIFTCARD'
                ],
                targetXpaths: ['//Line[3]/LineObject':'6030']
        )

        /* 13 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCard",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCard'
                ],
                targetXpaths: ['//Line[3]/LineObject':'625']
        )

        /* 14 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for GiftCertificate",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'GiftCertificate'
                ],
                targetXpaths: ['//Line[3]/LineObject':'615']
        )

        /* 15 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for Cheque",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Cheque'
                ],
                targetXpaths: ['//Line[3]/LineObject':'601']
        )

        /* 16 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for StorePromo",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'StorePromo'
                ],
                targetXpaths: ['//Line[3]/LineObject':'629']
        )

        /* 17 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for MallGiftCertificate",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'MallGiftCertificate'
                ],
                targetXpaths: ['//Line[3]/LineObject':'602']
        )

        /* 18 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for Coupon",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'Coupon'
                ],
                targetXpaths: ['//Line[3]/LineObject':'4102']
        )

        /* 19 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for LoyaltyRedemption",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'LoyaltyRedemption'
                ],
                targetXpaths: ['//Line[3]/LineObject':'6032']
        )

        /* 20 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tender code translation for PennyRounding",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Tender/@TenderType':'PennyRounding'
                ],
                targetXpaths: ['//Line[3]/LineObject':'6029']
        )




//



			
    }
}
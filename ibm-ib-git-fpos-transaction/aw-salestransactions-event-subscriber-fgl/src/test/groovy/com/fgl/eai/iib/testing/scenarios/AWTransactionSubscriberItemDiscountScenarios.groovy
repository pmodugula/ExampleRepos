package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberItemDiscountScenarios {

    static List<InputBean> specList

    static {
        specList = []

        /* mapping logic which should be tested
        IF 'COUPON SUBTOTAL DISCOUNT' THEN '400'
        IF 'PRICE MATCHING SUBTOTAL DISCOUNT' THEN '402'
        IF 'TEAM SUBTOTAL DISCOUNT' THEN '403'
        IF 'INCORRECT TICKET SUBTOTAL DISCOUNT' THEN '404'
        IF 'GST EVENT SUBTOTAL DISCOUNT' THEN '405'
        IF 'MANAGER OVERRIDE SUBTOTAL DISCOUNT' THEN '410'
        IF 'EMPLOYEE SUBTOTAL DISCOUNT' THEN '449'
        IF 'COUPON ITEM DISCOUNT' THEN '451'
        IF 'DAMAGED ITEM DISCOUNT' THEN '452'
        IF 'PRICE MATCHING ITEM DISCOUNT' THEN '453'
        IF 'TEAM ITEM DISCOUNT' THEN '454'
        IF 'INCORRECT TICKET ITEM DISCOUNT' THEN '455'
        IF 'GST EVENT ITEM DISCOUNT' THEN '456'
        IF 'OTHER ITEM DISCOUNT' THEN '458'
        IF 'PRICE MATCHING OVER' THEN '473'
        IF 'TEAM PRICE OVERRIDE' THEN '474'
        IF 'INCORRECT TICKET PRICE OVER' THEN '475'

        IF 'MULTIPLE PRICE OVERRIDE' THEN '477'
        IF 'PACKAGE ITEM MARKDOWN' THEN '479'
        IF 'MANAGER OVERRIDE ITEM MARKDOWN' THEN '480'
        IF 'AD PRICE NOT IN SYSTEM ITEM MARKDOWN' THEN '481'
        IF 'MANAGER PRICE OVERRIDE' THEN '482'
        IF 'AD PRICE NOT IN SYSTEM PRICE OVERRIDE' THEN '483'
        IF 'JUNIOR TRADE IN PRICE OVERRIDE' THEN '484'
        IF 'PRICE ADJUSTMENT' THEN '485'
        IF 'PRICE ADJUSTMENT PRICE OVERRIDE' THEN '486'
        IF 'DM APPROVED' THEN '488'
        IF 'DM APPROVED PRICE OVERRIDE' THEN '489'
        IF 'EMPLOYEE ITEM DISCOUNT' THEN '499
        IF RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode NOT EXISTS THEN '470'
        ELSE blank
         */

        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests discount code translation for COUPON SUBTOTAL DISCOUNT",

                 ///eai:Exchange/eai:Messages/eai:POSLog/arts:Transaction/arts:RetailTransaction/arts:LineItem/arts:Sale/arts:RetailPriceModifier/arts:ReasonCode/text()
                 sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'COUPON SUBTOTAL DISCOUNT'],

                 ///XPOLLD/Line[2]/LineObject
                 targetXpaths: ['//Line[2]/LineObject':'400']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PRICE MATCHING SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PRICE MATCHING SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'402']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for TEAM SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'TEAM SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'403']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for INCORRECT TICKET SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'INCORRECT TICKET SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'404']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for GST EVENT SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'GST EVENT SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'405']
        )

        /* 5 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for MANAGER OVERRIDE SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'MANAGER OVERRIDE SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'410']
        )

        /* 6 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for EMPLOYEE SUBTOTAL DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'EMPLOYEE SUBTOTAL DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'449']
        )

        /* 7 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for COUPON ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'COUPON ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'451']
        )

        /* 8 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for DAMAGED ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'DAMAGED ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'452']
        )

        /* 9 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PRICE MATCHING ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PRICE MATCHING ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'453']
        )

        /* 10 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for TEAM ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'TEAM ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'454']
        )

        /* 11 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for INCORRECT TICKET ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'INCORRECT TICKET ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'455']
        )

        /* 12 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for GST EVENT ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'GST EVENT ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'456']
        )

        /* 13 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for OTHER ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'OTHER ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'458']
        )

        /* 14 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PRICE MATCHING OVER",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PRICE MATCHING OVER'],
                targetXpaths: ['//Line[2]/LineObject':'473']
        )

        /* 15 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for TEAM PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'TEAM PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'474']
        )

        /* 16 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for INCORRECT TICKET PRICE OVER",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'INCORRECT TICKET PRICE OVER'],
                targetXpaths: ['//Line[2]/LineObject':'475']
        )

        /* 17 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PACKAGE ITEM MARKDOWN",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PACKAGE ITEM MARKDOWN'],
                targetXpaths: ['//Line[2]/LineObject':'479']
        )

        /* 18 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for MANAGER OVERRIDE ITEM MARKDOWN",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'MANAGER OVERRIDE ITEM MARKDOWN'],
                targetXpaths: ['//Line[2]/LineObject':'480']
        )

        /* 19 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for AD PRICE NOT IN SYSTEM ITEM MARKDOWN",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'AD PRICE NOT IN SYSTEM ITEM MARKDOWN'],
                targetXpaths: ['//Line[2]/LineObject':'481']
        )

        /* 20 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for MANAGER PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'MANAGER PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'482']
        )

        /* 21 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for AD PRICE NOT IN SYSTEM PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'AD PRICE NOT IN SYSTEM PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'483']
        )

        /* 22 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for JUNIOR TRADE IN PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'JUNIOR TRADE IN PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'484']
        )

        /* 23 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PRICE ADJUSTMENT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PRICE ADJUSTMENT'],
                targetXpaths: ['//Line[2]/LineObject':'485']
        )

        /* 24 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for PRICE ADJUSTMENT PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'PRICE ADJUSTMENT PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'486']
        )

        /* 25 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for DM APPROVED",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'DM APPROVED'],
                targetXpaths: ['//Line[2]/LineObject':'488']
        )

        /* 26 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for DM APPROVED PRICE OVERRIDE",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'DM APPROVED PRICE OVERRIDE'],
                targetXpaths: ['//Line[2]/LineObject':'489']
        )

        /* 27 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for EMPLOYEE ITEM DISCOUNT",
                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Sale/RetailPriceModifier/ReasonCode':'EMPLOYEE ITEM DISCOUNT'],
                targetXpaths: ['//Line[2]/LineObject':'499']
        )






    }
}
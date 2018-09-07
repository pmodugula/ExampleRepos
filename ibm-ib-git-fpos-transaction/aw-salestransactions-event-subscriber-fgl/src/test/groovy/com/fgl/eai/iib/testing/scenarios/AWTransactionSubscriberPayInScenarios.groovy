package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberPayInScenarios {

    static List<InputBean> specList

    /*
    IF rInPayIn.arts:ReasonCode = 'SUPPLIES' OR rInPayIn.arts:ReasonCode = 'SUPPLIES' THEN
			SET rLine.LineObject = 700;
		ELSEIF rInPayIn.arts:ReasonCode = 'REPAIRS AND MAINTENANCE' OR rInPayIn.arts:ReasonCode = 'REPAIRS AND MAINTENANCE' THEN
			SET rLine.LineObject = 701;
		ELSEIF rInPayIn.arts:ReasonCode = 'SERVICE SHOP' OR rInPayIn.arts:ReasonCode = 'SERVICE SHOP' THEN
			SET rLine.LineObject = 702;
		ELSEIF rInPayIn.arts:ReasonCode = 'EQUIPMENT RENTALS' OR rInPayIn.arts:ReasonCode = 'EQUIPMENT RENTALS' THEN
			SET rLine.LineObject = 703;
		ELSEIF rInPayIn.arts:ReasonCode = 'MISC' OR rInPayIn.arts:ReasonCode = 'MISC' THEN
			SET rLine.LineObject = 704;
		END IF;
     */

    static {
        specList = []





        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests tax code translation for Pay Out SUPPLIES ",
                 sourceXpaths: ["//Messages/POSLog/Transaction/TenderControlTransaction/PayIn/ReasonCode":'SUPPLIES'],
                 targetXpaths: ['//Line/LineObject':'700']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for Pay Out REPAIRS AND MAINTENANCE ",
                sourceXpaths: ["//Messages/POSLog/Transaction/TenderControlTransaction/PayIn/ReasonCode":'REPAIRS AND MAINTENANCE'],
                targetXpaths: ['//Line/LineObject':'701']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for Pay Out SERVICE SHOP ",
                sourceXpaths: ["//Messages/POSLog/Transaction/TenderControlTransaction/PayIn/ReasonCode":'SERVICE SHOP'],
                targetXpaths: ['//Line/LineObject':'702']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for Pay Out EQUIPMENT RENTALS ",
                sourceXpaths: ["//Messages/POSLog/Transaction/TenderControlTransaction/PayIn/ReasonCode":'EQUIPMENT RENTALS'],
                targetXpaths: ['//Line/LineObject':'703']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for Pay Out MISC ",
                sourceXpaths: ["//Messages/POSLog/Transaction/TenderControlTransaction/PayIn/ReasonCode":'MISC'],
                targetXpaths: ['//Line/LineObject':'704']
        )






			
    }
}
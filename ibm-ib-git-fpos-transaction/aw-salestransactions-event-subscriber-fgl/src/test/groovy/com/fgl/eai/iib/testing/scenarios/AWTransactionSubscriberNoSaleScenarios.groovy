package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberNoSaleScenarios {

    static List<InputBean> specList

    static {
        specList = []

        /*
        -- TODO CODE LOOKUP
			IF rInTransaction.(XMLNSC.Attribute)TypeCode = 'OperatorSignOn' THEN
				SET rLine.LineObject = 1116;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'OperatorSignOff' THEN
				SET rLine.LineObject = 1117;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'TransactionVoid' THEN
				SET rLine.LineObject = 1121;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'XRead' THEN
				SET rLine.LineObject = 1534;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'ZRead' THEN
				SET rLine.LineObject = 1535;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'Unsettled' THEN
				SET rLine.LineObject = 1536;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'NoSaleTransaction' AND rInNoSale.arts:NoSaleReasonCode = 'CHANGE' THEN
				SET rLine.LineObject = 1110;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'NoSaleTransaction' AND rInNoSale.arts:NoSaleReasonCode = 'DRAWER OUT' THEN
				SET rLine.LineObject = 1111;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'NoSaleTransaction' AND rInNoSale.arts:NoSaleReasonCode = 'DRAWER IN' THEN
				SET rLine.LineObject = 1112;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'NoSaleTransaction' AND rInNoSale.arts:NoSaleReasonCode = 'HOT WAX/SKATE SHARPEN CARDS' THEN
				SET rLine.LineObject = 4104;
			ELSEIF rInTransaction.(XMLNSC.Attribute)TypeCode = 'NoSaleTransaction' AND rInNoSale.arts:NoSaleReasonCode = 'MERCHANDISE CARDS' THEN
				SET rLine.LineObject = 4105;
			ELSE
				SET rLine.LineObject = '';
			END IF;
         */



        /* 0 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests tax code translation for CHANGE ",
                 sourceXpaths: ["//Messages/POSLog/Transaction/@TypeCode":'NoSaleTransaction','//Messages/POSLog/Transaction/ControlTransaction/NoSale/NoSaleReasonCode':'CHANGE'],
                 targetXpaths: ['//Line/LineObject':'1110']
         )

        /* 1 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for DRAWER OUT ",
                sourceXpaths: ["//Messages/POSLog/Transaction/@TypeCode":'NoSaleTransaction','//Messages/POSLog/Transaction/ControlTransaction/NoSale/NoSaleReasonCode':'DRAWER OUT'],
                targetXpaths: ['//Line/LineObject':'1111']
        )

        /* 2 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for DRAWER IN ",
                sourceXpaths: ["//Messages/POSLog/Transaction/@TypeCode":'NoSaleTransaction','//Messages/POSLog/Transaction/ControlTransaction/NoSale/NoSaleReasonCode':'DRAWER IN'],
                targetXpaths: ['//Line/LineObject':'1112']
        )

        /* 3 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for HOT WAX/SKATE SHARPEN CARDS ",
                sourceXpaths: ["//Messages/POSLog/Transaction/@TypeCode":'NoSaleTransaction','//Messages/POSLog/Transaction/ControlTransaction/NoSale/NoSaleReasonCode':'HOT WAX/SKATE SHARPEN CARDS'],
                targetXpaths: ['//Line/LineObject':'4104']
        )

        /* 4 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests tax code translation for MERCHANDISE CARDS ",
                sourceXpaths: ["//Messages/POSLog/Transaction/@TypeCode":'NoSaleTransaction','//Messages/POSLog/Transaction/ControlTransaction/NoSale/NoSaleReasonCode':'MERCHANDISE CARDS'],
                targetXpaths: ['//Line/LineObject':'4105']
        )




			
    }
}
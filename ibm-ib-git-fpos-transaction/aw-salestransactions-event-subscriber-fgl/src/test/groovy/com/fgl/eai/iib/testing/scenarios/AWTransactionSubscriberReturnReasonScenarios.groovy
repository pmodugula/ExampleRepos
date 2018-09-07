package com.fgl.eai.iib.testing.scenarios

class AWTransactionSubscriberReturnReasonScenarios {

    static List<InputBean> specList

    static {
        specList = []

        /*
        IF rLineitems.arts:Return.arts:ReasonCode = 'DEFECTIVE' THEN
--								SET rReturn.ReturnReasonCode = 1;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'REGISTER' THEN
--								SET rReturn.ReturnReasonCode = 2;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'GIFT' THEN
--								SET rReturn.ReturnReasonCode = 3;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'COMPETITIVE PRICE' THEN
--								SET rReturn.ReturnReasonCode = 4;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'CUSTOMER SERVICE' OR rLineitems.arts:Return.arts:ReasonCode = 'JUNIOR TRADE IN' THEN
--								SET rReturn.ReturnReasonCode = 5;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'WRONG SIZE OR COLOUR' THEN
--								SET rReturn.ReturnReasonCode = 6;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'OTHER' THEN
--								SET rReturn.ReturnReasonCode = 7;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'RETURN PROMO' THEN
--								SET rReturn.ReturnReasonCode = 8;
--							ELSEIF rLineitems.arts:Return.arts:ReasonCode = 'SPECIAL ORDER' THEN
--								SET rReturn.ReturnReasonCode = 9;
--							ELSE
--								SET rReturn.ReturnReasonCode = -99;
--							END IF;
         */

        /* 0 */
        specList << new InputBean(
                jiraID: 'FPOE-12512',
                description: "Tests discount code translation for return reason code DEFECTIVEXXX", // test for invalid code

                sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/ReasonCode':'DEFECTIVEXXX'],

                targetXpaths: ['//XPOLLD/Return/ReturnReasonCode':'-99']
        )


        /* 1 */
         specList << new InputBean(
                 jiraID: 'FPOE-12512',
                 description: "Tests discount code translation for return reason code DEFECTIVE",

                 sourceXpaths: ['//Messages/POSLog/Transaction/RetailTransaction/LineItem/Return/ReasonCode':'DEFECTIVE'],

                 targetXpaths: ['//XPOLLD/Return/ReturnReasonCode':'1']
         )








    }
}

// defaults
mq {
    hostName = "10.103.1.162"
    port = 1414
    queueManager = "IBQCI"
    channel = "EAI.IBMIB.USER"
    transportType = "1"
}

environments {

    ci {
        mq {
            hostName = "10.103.1.162"
            port = 1414
            queueManager = "IBQCI"
            channel = "EAI.IBMIB.USER"
            transportType = "1"
            cssQueue = "MWW.ETPS.SALES_OUT.IBM_IB.CI"
        }


    }

    qa {
        mq {
            hostName = "mwwlxesb102.mww.lan"
            port = 14150
            queueManager = "ABQQ0"
            channel = "EAI.IBMIB.ADMIN"
            transportType = "1"
            cssQueue = "MWW.DIG.SALESRAW.EPM_POS.QA.4"
        }


    }

    prd {
        mq {
            hostName = "fmamb2ap01"
            port = 14171
            queueManager = "ABQ1"
            channel = "EAI.IBMIB.ADMIN"
            transportType = "1"
            cssQueue = "MWW.DIG.SALESRAW.EPM_POS"
        }


    }

}

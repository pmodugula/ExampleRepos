mqsisetdbparms IBNQ1 -n STGQA -u STG_DB1_USER -p STG_DB1_PASSWORD
mqsisetdbparms IBNQ1 -n STGQA2 -u STG_DB2_USER -p STG_DB2_PASSWORD
mqsisetdbparms IBNQ1 -n AUDQA -u AUD_DB1_USER -p AUD_DB1_PASSWORD
mqsisetdbparms IBNQ1 -n AUDQA2 -u AUD_DB2_USER -p AUD_DB2_PASSWORD
mqsisetdbparms IBNQ1 -n PMMQA -u pmm -p pmmqa
mqsisetdbparms IBNQ1 -n WMQA -u IBMIBDEV1 -p bZpBpBp1
mqsichangeproperties IBNQ1 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap01q.fglcorporate.net:7267
mqsisetdbparms IBNQ1 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ1 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ1 -n jndi::FGL.QA5.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ1 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ1 -n jms::FGL.QA5.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

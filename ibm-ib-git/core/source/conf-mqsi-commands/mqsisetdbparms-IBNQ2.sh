mqsisetdbparms IBNQ2 -n STGQA -u STG_DB1_USER -p STG_DB1_PASSWORD
mqsisetdbparms IBNQ2 -n STGQA2 -u STG_DB2_USER -p STG_DB2_PASSWORD
mqsisetdbparms IBNQ2 -n AUDQA -u AUD_DB1_USER -p AUD_DB1_PASSWORD
mqsisetdbparms IBNQ2 -n AUDQA2 -u AUD_DB2_USER -p AUD_DB2_PASSWORD
mqsisetdbparms IBNQ2 -n PMMQA -u pmm -p pmmqa
mqsisetdbparms IBNQ2 -n WMQA -u IBMIBDEV1 -p bZpBpBp1
mqsichangeproperties IBNQ2 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap01q.fglcorporate.net:7267
mqsisetdbparms IBNQ2 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ2 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ2 -n jndi::FGL.QA5.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ2 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNQ2 -n jms::FGL.QA5.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

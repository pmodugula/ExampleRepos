mqsisetdbparms IBND1 -n STGDEV -u IIB -p ibm11d3v3
mqsisetdbparms IBND1 -n STGDEV2 -u IIB -p ibm11d3v3
mqsisetdbparms IBND1 -n AUDDEV -u IIB -p ibm11d3v3
mqsisetdbparms IBND1 -n AUDDEV2 -u IIB -p ibm11d3v3
mqsisetdbparms IBND1 -n PMMDEV -u pmm -p pmmdev
mqsisetdbparms IBND1 -n WMDEV -u IBMIBDEV1 -p bZpBpBp1
mqsichangeproperties IBND1 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap01d.fglcorporate.net:7227
mqsisetdbparms IBND1 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBND1 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBND1 -n jndi::FGL.DEV.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBND1 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBND1 -n jms::FGL.DEV.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

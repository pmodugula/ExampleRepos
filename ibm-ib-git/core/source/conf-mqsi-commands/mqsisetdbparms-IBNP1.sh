mqsisetdbparms IBNP1 -n AUDPP -u IIB -p a4dRFGqnaw81
mqsisetdbparms IBNP1 -n STGPP -u IIB -p a4dRFGqnaw81
mqsisetdbparms IBNP1 -n PMMPP -u IIB -p EHqlx8xkpo
mqsisetdbparms IBNP1 -n WMCDCPP -u IIBCDC -p IIBCDC
mqsisetdbparms IBNP1 -n WMMDCPP -u IIBMDC -p IIBMDC
mqsichangeproperties IBNP1 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap03p.fglcorporate.net:7253
mqsisetdbparms IBNP1 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP1 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP1 -n jndi::FGL.QA2.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP1 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP1 -n jms::FGL.QA2.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

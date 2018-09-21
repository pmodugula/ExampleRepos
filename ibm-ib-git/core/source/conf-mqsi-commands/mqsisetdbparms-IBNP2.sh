mqsisetdbparms IBNP2 -n AUDPP -u IIB -p a4dRFGqnaw81
mqsisetdbparms IBNP2 -n STGPP -u IIB -p a4dRFGqnaw81
mqsisetdbparms IBNP2 -n PMMPP -u IIB -p EHqlx8xkpo
mqsisetdbparms IBNP2 -n WMCDCPP -u IIBCDC -p IIBCDC
mqsisetdbparms IBNP2 -n WMMDCPP -u IIBMDC -p IIBMDC
mqsichangeproperties IBNP2 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap03p.fglcorporate.net:7253
mqsisetdbparms IBNP2 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP2 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP2 -n jndi::FGL.QA2.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP2 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBNP2 -n jms::FGL.QA2.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

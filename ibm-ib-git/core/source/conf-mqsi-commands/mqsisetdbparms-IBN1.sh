mqsisetdbparms IBN1 -n AUDPRD -u IIB -p qFj3HCxGg
mqsisetdbparms IBN1 -n STGPRD -u IIB -p qFj3HCxGg
mqsisetdbparms IBN1 -n PMMPRD -u IIB -p P4AlGX6gS
mqsisetdbparms IBN1 -n WMCDCPRD -u IIBCDC -p Meu5vJB8sE
mqsisetdbparms IBN1 -n WMMDCPRD -u IIBMDC -p xzeeXkp4Ce
mqsichangeproperties IBN1 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap03.fglcorporate.net:7213
mqsisetdbparms IBN1 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN1 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN1 -n jndi::FGL.PRD.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN1 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN1 -n jms::FGL.PRD.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

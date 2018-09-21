mqsisetdbparms IBN2 -n AUDPRD -u IIB -p qFj3HCxGg
mqsisetdbparms IBN2 -n STGPRD -u IIB -p qFj3HCxGg
mqsisetdbparms IBN2 -n PMMPRD -u IIB -p P4AlGX6gS
mqsisetdbparms IBN2 -n WMCDCPRD -u IIBCDC -p Meu5vJB8sE
mqsisetdbparms IBN2 -n WMMDCPRD -u IIBMDC -p xzeeXkp4Ce
mqsichangeproperties IBN2 -c JMSProviders -o Tibco_EMS -n jndiBindingsLocation -v tibjmsnaming://caltib3ap03.fglcorporate.net:7213
mqsisetdbparms IBN2 -n jndi::com.tibco.tibjms.naming.TibjmsInitialContextFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN2 -n jndi::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN2 -n jndi::FGL.PRD.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN2 -n jms::XAQueueConnectionFactory -u usr_trn_int -p usr_trn_int
mqsisetdbparms IBN2 -n jms::FGL.PRD.WM.BW.WMCOMMON.Q -u usr_trn_int -p usr_trn_int

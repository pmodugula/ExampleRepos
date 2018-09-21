cat /home/ralex/conf-iib-schema/sqlplus-QA.sql|perl -e 'while(<>){s/(.*\S)\s*/$1/;print"$_\n"}' > /home/ralex/conf-iib-schema/sqlplus.sql~;mv /home/ralex/conf-iib-schema/sqlplus.sql~ /home/ralex/conf-iib-schema/sqlplus-QA.sql
/opt/oracle/product/11.2.0.1/bin/sqlplus -s IIB/I12399Ib@MWWAXDB101 @/home/ralex/conf-iib-schema/sqlplus-QA.sql

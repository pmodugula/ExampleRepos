cat /home/ralex/conf-iib-schema/sqlplus-DV.sql|perl -e 'while(<>){s/(.*\S)\s*/$1/;print"$_\n"}' > /home/ralex/conf-iib-schema/sqlplus.sql~;mv /home/ralex/conf-iib-schema/sqlplus.sql~ /home/ralex/conf-iib-schema/sqlplus-DV.sql
/opt/oracle/product/11.2.0.1/bin/sqlplus -s IIB/ibm11d3v3@STGDEV @/home/ralex/conf-iib-schema/sqlplus-DV.sql

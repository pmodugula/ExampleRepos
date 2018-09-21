cat /home/ralex/conf-iib-schema/sqlplus-PP.sql|perl -e 'while(<>){s/(.*\S)\s*/$1/;print"$_\n"}' > /home/ralex/conf-iib-schema/sqlplus.sql~;mv /home/ralex/conf-iib-schema/sqlplus.sql~ /home/ralex/conf-iib-schema/sqlplus-PP.sql
/opt/oracle/product/11.2.0.1/bin/sqlplus -s IIB/a4dRFGqnaw81@MWWAXDB201 @/home/ralex/conf-iib-schema/sqlplus-PP.sql

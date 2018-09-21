cat /home/ralex/conf-iib-schema/sqlplus.sql|perl -e 'while(<>){s/(.*\S)\s*/$1/;print"$_\n"}' > /home/ralex/conf-iib-schema/sqlplus.sql~;mv /home/ralex/conf-iib-schema/sqlplus.sql~ /home/ralex/conf-iib-schema/sqlplus.sql
/opt/oracle/product/11.2.0.1/bin/sqlplus -s IIB/qFj3HCxGg@FMORA13DB01 @/home/ralex/conf-iib-schema/sqlplus.sql

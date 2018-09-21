IF "x%_HOSTNAME%" == "xlocalhost" (
 %_COPY% log4j-%_NODE%-%_SERVER%.properties C:\eai\ibm-ib\log4j-%_NODE%-%_SERVER%.properties
) ELSE (
 @REM %_COPY% log4j-%_NODE%-%_SERVER%.properties %_USERNAME%@%_HOSTNAME%:/home/%_USERNAME%/log4j-%_NODE%-%_SERVER%.properties
 %_COPY% log4j-%_NODE%-%_SERVER%.properties %_USERNAME%@%_HOSTNAME%:/var/mqsi/log4j-%_NODE%-%_SERVER%.properties
)

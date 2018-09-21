@ECHO OFF
IF "x" == "x%MQSI_FILEPATH%" (
 ECHO Error: this command must be run in an Integration Bus Console!
 GOTO :FINISH2
)
dir "C:\Program Files (x86)\IBM\IntegrationToolkit90\dropins"
dir "%MQSI_FILEPATH%\jplugin"
dir "C:\ProgramData\IBM\MQSI\shared-classes"
:FINISH2

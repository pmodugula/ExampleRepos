##DESCRIPTION:
The etps-salestransactions-asynchronous-service-mww repository.
IIB interface which maps ARTS XML transaction messages into JSON that CSS can consume
Used in the context of Fast Find, IONS and AX where no physical POS is present.
Works in conjunction with etps adapter

https://confluence.fglsports.com/display/MEPP/ETPS

##DEVELOPER WORKSTATION NOTES
**STEP 1.** Checkout code to standardized location on Workstation '<userhomedir>\eai\ibm-ib\<repository>'

**STEP 2.** Available Gradle Scripts within the repository

Refresh repository dependencies -- copies files from other repositories that this repository depends on

    gradlew rD


Clean repository -- removes all the copied in code that the repository depened on from other repositories

    gradlew cP
    
    
 
Test 
    gradlew test
   


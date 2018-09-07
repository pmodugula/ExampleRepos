package com.fgl.testing.ib.employee.transform.scenarios

import com.fgl.testing.ib.employee.transform.scenarios.InputBean as I

/**
 * AsnPublisherScenarios

   Template
      specList << new I(
          jiraID: 'TODO: enter JiraID',
          description: "TODO: please update with description",
          sourceXpaths: ['somexpath':'some_value_to_set'],
          targetXpaths: ['somexpath':'some_value_to_assert])

    NOTE: the sourceXpath and targetXpaths are just HashMaps
 */
class EmployeeSubsriberScenarios {

    protected static final NO_SOURCE_CHANGE_REQUIRED = "NO_SOURCE_CHANGE_REQUIRED"

    static List<I> specList

    static {
        specList = []

        /* 0 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing concat of names",
				 sourceXpaths: ['//FGLEmployeeMessage/FGLEmployee/Worker/LegalName/Name[1]':'Delyan','//FGLEmployeeMessage/FGLEmployee/Worker/LegalName/Name[2]':'Zafirov'],
                 targetStrings: ['Delyan Zafirov                '])
		
		/* 1 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing constant value of process subtype",
                 sourceXpaths: [NO_SOURCE_CHANGE_REQUIRED:NO_SOURCE_CHANGE_REQUIRED],
                 targetStrings:['AS'])
				 
		/* 2 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing constant value of Action",
                 sourceXpaths: [NO_SOURCE_CHANGE_REQUIRED:NO_SOURCE_CHANGE_REQUIRED],
                 targetStrings: ['R'])
				 
				 
		/* 3 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing padding of Worker ID",
                 sourceXpaths: ['//FGLEmployeeMessage/FGLEmployee/Worker/WorkerID':'12345678'],
                 targetStrings: ['012345678'])
				 
		/* 4 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing constant value of Home store",
                 sourceXpaths: [NO_SOURCE_CHANGE_REQUIRED:NO_SOURCE_CHANGE_REQUIRED],
                 targetStrings: ['000000000'])
				 
				 
		/* 5 */
         specList << new I(
                 jiraID: 'EAI-107',
                 description: "Testing padding of sales associate number",
                 sourceXpaths: ['//FGLEmployeeMessage/FGLEmployee/Worker/WorkLocation/SalesAssociateID':'12345'],
                 targetStrings: ['000012345'])


        /* 6 */
        specList << new I(
                jiraID: 'EAI-107',
                description: "Testing full output string for fix format output",
                sourceXpaths: [NO_SOURCE_CHANGE_REQUIRED:NO_SOURCE_CHANGE_REQUIRED],
                targetStrings: ['ASR000058027BRIAN DRUKER                  002080523000000000'])

			
    }
}
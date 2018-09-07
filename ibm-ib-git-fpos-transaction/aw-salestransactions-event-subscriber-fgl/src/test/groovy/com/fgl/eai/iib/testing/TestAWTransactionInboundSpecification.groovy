package com.fgl.eai.iib.testing

import groovy.xml.XmlUtil
import org.junit.Ignore
import spock.lang.Unroll

/**
 * Transaction integration test which uses the Spock framework (https://code.google.com/p/spock/) to test different transaction scenarios for trx-sales-aw-subscriber-fgl IIB component
 * Specifically puts (XPOLD-like XML format) message on inbound input queue and checks remote directory where the file is created
 * @author wrhett
 *
 */
@Ignore
class TestAWTransactionInboundSpecification extends BaseSpecification {

    // used by each test
    String transactionNumber
    def jira
    def scenarioDesc
    def scenarioNum
    def iibInputXMLFile
    def iibInputXMLFileFull
    def iibExpectedOutputFile
    def iibExpectedOutputFileFull
    String inputQueue = "FGL.AW.FPOS.SALESRAW_OUT.IIB.CI"
    String serverOutputPath = "/var/mqsi/staging/transactions/auditworks/CI"
    def ssh
    def producedFileContents

    // for each string, create a map of lists. Each map entry is a row from the file. Each row is a list
    def createMapFromXPOLDFileContents = { outputString ->
        def outputMap = [:]
        outputString.split("\\n").eachWithIndex { def entry, int i ->
            def line = entry.split("\\t")
            outputMap.put(i,line)
        }
        outputMap

    }

    @Unroll("Testing JIRA #jira - scenario #scenarioNum - #scenarioDesc - Transaction number #transactionNumber in trx-sales-aw-inbound-fgl output matches expected file #iibOutputFile")
    def "Read XML from IIB response and assert the data is valid"() {

        setup:

            iibInputXMLFileFull = "./source/trx-sales-all-samples-fgl/xpolld/xml/${iibInputXMLFile}"
            iibExpectedOutputFileFull = "./source/trx-sales-all-samples-fgl/xpolld/dat/${iibExpectedOutputFile}"

            // set up the connetion to mwwlxesb202 and define the remote server in a DSL
            ssh = org.hidetake.groovy.ssh.Ssh.newService()


            // sshUser is the user we want to ssh in with - in Bamboo this will be ibdevops, change as required for individual users
            // sshKeyPath is the private key file we need to use - in Bamboo this will be in a different place than if you run the test locally
            def sshUser = "wrhett"
            //def sshKeyPath = "~/.ssh/id_rsa" // key in home directory
            def sshKeyPath = "./src/test/resources/ssh/id_rsa" // key in test project

            def currentUserName = System.getProperty("user.name");

            // using this to trick Bamboo to use the correct user and private key
            if (currentUserName.contains("bamboo")){
                sshUser = "ibdevops"
                sshKeyPath = "/home/bamboo/ibdevops-10.103.1.162-id_rsa"
            }

            println "Current system user is ${currentUserName}"
            println"Using ssh user ${sshUser} ssh user and ${sshKeyPath} path to keys"

            ssh.remotes {
                iibci {
                    role 'iibci'
                    host = '10.103.1.162'
                    user = sshUser
                }
            }

            ssh.settings {
                jschLog = true
                identity = new File(sshKeyPath)
            }


        when: "A message is put on the IIB input queue"

            // read the iib input file
            String inputMessage = XmlUtil.serialize(new XmlSlurper().parse(iibInputXMLFileFull))
            println("Putting a message on the queue")
            // put a message (XPOLD format) on the inbound input queue
            producer.sendBody("wmq:$inputQueue",inputMessage)

        then: "File produced on remote directory matches expected results"


            Date date = new Date()
            def month = (date[Calendar.MONTH]+1).toString().padLeft(2,"0")
            def day = date[Calendar.DAY_OF_MONTH].toString().padLeft(2,"0")

            // remote path is created dynamically by day
            def remotePath = "/var/mqsi/staging/transactions/auditworks/CI/AWL.${month}${day}.IP"
            println "Remote path to look for file: ${remotePath}"

            // get the most recent file under under /var/mqsi/staging/transactions/auditworks/CI/AWL.MMDD
            ssh.run {
                session(ssh.remotes.iibci) {
                    def fileOnServer = execute "ls -t ${remotePath} | head -n1", pty: true
                    println "Getting copy of latest file on server ${fileOnServer}"

                    // get the contents of the remote file and scp into local string
                    def fileOnServerWithPath = "${remotePath}/${fileOnServer}"
                    producedFileContents = get from: fileOnServerWithPath

                }
            }

            // make sure we got something from the server
            assert producedFileContents

            // remove any carriage return characters which come from the scp and will mess up the parsing
            def producedFileContentsWithoutCarriageReturn=producedFileContents.replaceAll("\\r","")

            // if you just want to compare the 2 strings directly
            //assert expectedStr==producedFileContents

            // create for each string create a map of lists so we can compare 1 line at a time
            def expectedStr = new File(iibExpectedOutputFileFull).text
            def expectedMap = createMapFromXPOLDFileContents(expectedStr)
            def producedMap = createMapFromXPOLDFileContents(producedFileContentsWithoutCarriageReturn)

            // now assert the 2 maps are the same
            assert expectedMap.size()==producedMap.size()


            // compare line by line to get better output
            expectedMap.each{ key,value->

                def expectedLine = expectedMap[key]
                def producedLine = producedMap[key]

                // assert each line is the same
                def message = "Produced line # ${key} ${producedLine.toString()}  does not match expected line ${expectedLine.toString()}"

                //assert expectedLine == producedLine : message
                assert expectedLine == producedLine

            }

        where:
        // Spock data table, each row becomes a new test and each variable is substituted
        jira           | scenarioNum | scenarioDesc  | transactionNumber | iibInputXMLFile | iibExpectedOutputFile

        "FPOS Slice 1" | "1"         | "Simple sale" | "000004"            | '000004.xml'      | 'XPOLLD.151206.TMP'

    }


}

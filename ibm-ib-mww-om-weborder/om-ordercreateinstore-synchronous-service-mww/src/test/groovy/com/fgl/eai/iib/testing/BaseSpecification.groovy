package com.fgl.eai.iib.testing

import groovy.xml.XmlUtil
import org.apache.camel.CamelContext
import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.mock.MockEndpoint
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import spock.lang.Specification

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * Created by Derek Marley (derek@middleware360.com)
 */
@ContextConfiguration(classes = TestingScaffoldRouteBuilder.class)
class BaseSpecification extends Specification  {
    @Autowired
    CamelContext camelContext

    protected static final NO_SOURCE_CHANGE_REQUIRED = "NO_SOURCE_CHANGE_REQUIRED"
    protected static final ASSERT_TYPE_XML = "ASSERT_TYPE_XML"
    protected static final ASSERT_TYPE_TEXT = "ASSERT_TYPE_TEXT"

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @EndpointInject(uri = "mock:backout")
    protected MockEndpoint backoutEndpoint;

    //private static Set<String> fromEndpoints;
    private static Map<String,String> fromEndpoints;
    ProducerTemplate producer

    def inputQueue = "RECV.MWW.DIG.INSTOREORDCRT_IN.IBM_IB.DV"

    @Before
    public void setup() {

        producer = camelContext.createProducerTemplate()
    }

    @After
    public void teardown(){
        producer = null
    }

    /**
     * setNodeValue - overwrites a node value at given XPath expression with a given value
     * @param body
     * @param sourceXPath
     * @param sourceValue
     * @return
     */
    protected groovy.util.Node setNodeValue(groovy.util.Node body, String sourceXPath, String sourceValue) {
        if(sourceXPath!=NO_SOURCE_CHANGE_REQUIRED) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader(XmlUtil.serialize(body))));

                XPath xpath = XPathFactory.newInstance().newXPath();
                def result = xpath.compile(sourceXPath)

                NodeList myNodeList = (NodeList) result.evaluate(doc, XPathConstants.NODE);
                def node = myNodeList.item(0)

                if (node) // this usually works for node values
                    node.setNodeValue(sourceValue);
                else{
                   // for some reason attributes are weird so we do it this way
                    Node myNode = (Node) result.evaluate(doc, XPathConstants.NODE);
                    myNode.setNodeValue(sourceValue);
                }

                body = new XmlParser().parseText(getStringFromDocument(doc));

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (SAXException se) {
                se.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return body
    }

    /**
     * setMockConsumer
     * @param fromEndpoint
     * @param uri
     */
    protected void setMockConsumer(String fromEndpoint, String uri) {
        if (fromEndpoints == null) {
            //Instantiate Map and add first entry
            fromEndpoints = new HashMap<String, String>()
            fromEndpoints.put(fromEndpoint, uri)
        } else if (fromEndpoints.containsKey(fromEndpoint)) {
            //Already in the map -- nothing to do - as route was started
            return;
        }
        else{
            //add to existing MAP
            fromEndpoints.put(fromEndpoint, uri)
        }

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(fromEndpoint).to(uri);
            }
        });

    }

    protected String getNodeValue(groovy.util.Node body, String targetXPath) {
        return getNodeValue(XmlUtil.serialize(body), targetXPath);
    }

    protected String getNodeValue(String body, String targetXPath) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(body)));
            XPath xpath = XPathFactory.newInstance().newXPath();
            Object resolvedObj = xpath.compile(targetXPath).evaluate(doc);
//            Object resolvedObj = xpath.compile(targetXPath).evaluate(doc, XPathConstants.NODE);
//            println "getNodeValue: resolved xpath object ("+ resolvedObj?.getClass().getName() +") for $targetXPath: " + resolvedObj
            if (resolvedObj == null) {
                return null;
            }
            if (resolvedObj instanceof String) {
//                println "getNodeValue - resolvedObj is string " + (String)resolvedObj
                return (String)resolvedObj;
            } else if (resolvedObj instanceof Node) {
                return getNodeText((Node)resolvedObj)

            } else if (resolvedObj instanceof NodeList) {
//                println "getNodeValue - resolvedObj is nodeList"
                NodeList nl = (NodeList) resolvedObj;
                if (nl.getLength() > 0) {
//                    println "getNodeValue - nodeList size: " + nl.getLength()
//                    println "getNodeValue - nodeList content type: " + nl.item(0).getClass().getName()
                    return getNodeText(nl.item())
                } else {
//                    println("  returning null from empty nodeList")
                    return null
                }
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String getNodeText(Node node) {
//        println "getNodeText - enter - $node"
        if (node == null) {
//            println "getNodeText - returning null"
            return null;
        }
        switch (node.getNodeType()) {
            case Node.TEXT_NODE:
//                println "getNodeValue - text node"
                return node.getNodeValue()
            case Node.ELEMENT_NODE:
//                println "getNodeValue - element node"
                return node.getNodeName();
            case Node.ENTITY_NODE:
//                println "getNodeValue - entity node"
                return node.getNodeName()
            default:
//                println "getNodeValue - fall through"
                break;
        }

    }


    protected void assertNodeValue(String body, String targetXPath, String targetValue) throws Exception {
        Assert.assertNotNull("xml body is null", body)
        Assert.assertNotNull("xpath value is null", targetXPath)
        String result = getNodeValue(body, targetXPath);
        Assert.assertEquals(targetValue, result);
    }

    /**
     * assertoutputContains
     * @param body
     */
    protected void assertOutputContains(List<String> values, String body){
        values.each{value ->
            assert body.contains(value)
        }
    }


    /**
     * getStringFromDocument method to convert Document to String
     * @param doc
     * @return
     */
    protected String getStringFromDocument(Document doc) {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectclient;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



/**
 *
 * @author Chenjie
 */
public class WebServiceDesignStyles2ProjectClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException {
        // TODO code application logic here
        System.out.println(hello("jack"));
        String spy
                = "<spy><name>Michael</name><location>Pittsburgh</location>"
                + "<password>sesame</password><title>spy master</title></spy>";
        addSpy(spy);
        spy
                = "<spy><name>Joe</name><location>Philadelphia</location>"
                + "<password>xyz</password><title>spy agent</title></spy>";
        addSpy(spy);

        String list = getList();
        System.out.println("Two Spies \n" + list);
        String nameSpy = "<spy><name>Michael</name></spy>";
        deleteSpy(nameSpy);
        list = getList();
        System.out.println("One Spy \n" + list);
        nameSpy = "<spy><name>Joe</name></spy>";
        deleteSpy(nameSpy);
        list = getList();
        System.out.println("Zero Spy \n" + list);
        spy
                = "<spy><name>Michael</name><location>Pittsburgh</location>"
                + "<password>sesame</password><title>spy master</title></spy>";
        addSpy(spy);
        list = getList();
        System.out.println("One Spy \n" + list);
        System.out.println("List as XML");

        Document doc = getDocument(list);
        doc.getDocumentElement().normalize();
        NodeList nl = doc.getElementsByTagName("name");
        Node n = nl.item(0);
        String name = n.getTextContent();
        System.out.println("Should be the spy Mike " + name);

    }

    private static Document getDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document spyDoc = null;
        try {
            builder = factory.newDocumentBuilder();
            spyDoc = (Document) builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spyDoc;

    }

    private static String hello(java.lang.String name) {
        edu.chenjie1.DesignStyle2_Service service = new edu.chenjie1.DesignStyle2_Service();
        edu.chenjie1.DesignStyle2 port = service.getDesignStyle2Port();
        return port.hello(name);
    }

    private static String addSpy(java.lang.String spy) {
        edu.chenjie1.DesignStyle2_Service service = new edu.chenjie1.DesignStyle2_Service();
        edu.chenjie1.DesignStyle2 port = service.getDesignStyle2Port();
        return port.addSpy(spy);
    }

    private static String deleteSpy(java.lang.String name) {
        edu.chenjie1.DesignStyle2_Service service = new edu.chenjie1.DesignStyle2_Service();
        edu.chenjie1.DesignStyle2 port = service.getDesignStyle2Port();
        return port.deleteSpy(name);
    }

    private static String getList() {
        edu.chenjie1.DesignStyle2_Service service = new edu.chenjie1.DesignStyle2_Service();
        edu.chenjie1.DesignStyle2 port = service.getDesignStyle2Port();
        return port.getList();
    }

}

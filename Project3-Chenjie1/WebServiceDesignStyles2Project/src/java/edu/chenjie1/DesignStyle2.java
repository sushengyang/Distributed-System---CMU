/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chenjie1;

import java.io.StringReader;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

/**
 *
 * @author Chenjie
 */
@WebService(serviceName = "DesignStyle2")
public class DesignStyle2 {

    ArrayList<String> spyList = new ArrayList<String>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    Document spyDoc = null;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "addSpy")
    public String addSpy(@WebParam(name = "spy") String spyXML) {
 
        if (!spyList.contains(spyXML)) {
            spyList.add(spyXML);
        }

        return "Spy " + spyXML + "was added to the list.";
    }

    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String nameXML) {
        spyList.remove(0);
        
//        for (int i=0; i < spyList.size(); i++){
//            if (spyList.get(i).indexOf(nameXML) != -1)
//                spyList.remove(i);
//        }
        return "Spy" + nameXML + "was removed from the list.";
    }

    @WebMethod(operationName = "getList")
    public String getList() {
        String spies = "";
        for (String spy : spyList) {
            spies += spy + "\n ";
        }
        return spies;
    }
}

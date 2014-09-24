/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chenjie1;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Chenjie
 */
@WebService(serviceName = "DesignStyles1")
public class DesignStyles1 {
    SpyList spylist = SpyList.getInstance();
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "addSpy")
    public String addSpy(@WebParam(name = "name") String name,
            @WebParam(name = "title") String title,
            @WebParam(name = "location") String location,
            @WebParam(name = "password") String password) {
        
        Spy s = new Spy(name,title, location, password); 
        spylist.add(s);
        
        return "Spy " + name + "was added to the list.";
    }
    
    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String name){
        Spy delSpy = spylist.get(name);
        spylist.delete(delSpy);
        return "Spy " + name + "was removed from the list.";
    }
    
    @WebMethod(operationName = "getList")
    public String getList(){

        return spylist.toString();
    }
}

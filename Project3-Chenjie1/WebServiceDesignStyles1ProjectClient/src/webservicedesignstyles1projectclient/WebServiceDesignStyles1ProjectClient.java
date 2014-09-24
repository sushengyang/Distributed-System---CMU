/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicedesignstyles1projectclient;

/**
 *
 * @author Chenjie
 */
public class WebServiceDesignStyles1ProjectClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(hello("jack"));
        addSpy("joem", "spy", "Pittsburgh", "joe");
        addSpy("mikem", "spy", "Philadelphia", "mike");
        addSpy("jamesb", "spy", "Toronto", "james");
        System.out.println("Three spies added to spy list");
        System.out.println("The list constains: " + getList());
        deleteSpy("mikem");
        System.out.println("Mike was deleted.");
        System.out.println("The list now contains: " + getList());
    }

    private static String addSpy(java.lang.String name, java.lang.String title, java.lang.String location, java.lang.String password) {
        edu.chenjie1.DesignStyles1_Service service = new edu.chenjie1.DesignStyles1_Service();
        edu.chenjie1.DesignStyles1 port = service.getDesignStyles1Port();
        return port.addSpy(name, title, location, password);
    }

    private static String deleteSpy(java.lang.String name) {
        edu.chenjie1.DesignStyles1_Service service = new edu.chenjie1.DesignStyles1_Service();
        edu.chenjie1.DesignStyles1 port = service.getDesignStyles1Port();
        return port.deleteSpy(name);
    }

    private static String getList() {
        edu.chenjie1.DesignStyles1_Service service = new edu.chenjie1.DesignStyles1_Service();
        edu.chenjie1.DesignStyles1 port = service.getDesignStyles1Port();
        return port.getList();
    }

    private static String hello(java.lang.String name) {
        edu.chenjie1.DesignStyles1_Service service = new edu.chenjie1.DesignStyles1_Service();
        edu.chenjie1.DesignStyles1 port = service.getDesignStyles1Port();
        return port.hello(name);
    }


    }
    
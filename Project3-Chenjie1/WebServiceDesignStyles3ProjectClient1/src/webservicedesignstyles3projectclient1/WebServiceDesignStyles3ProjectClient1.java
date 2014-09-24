/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles3projectclient1;

/**
 *
 * @author Chenjie
 */
public class WebServiceDesignStyles3ProjectClient1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        System.out.println(client.addSpy("mike"));
        System.out.println(client.addSpy("joe"));

        System.out.println(client.deleteSpy("mike"));
        System.out.println(client.deleteSpy("joe"));
        System.out.println(client.deleteSpy("jack"));

        System.out.println(client.helloInHTML());

        client.close();
    }

}

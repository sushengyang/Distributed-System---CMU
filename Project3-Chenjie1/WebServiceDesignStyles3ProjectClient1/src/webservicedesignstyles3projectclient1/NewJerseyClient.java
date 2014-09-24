/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicedesignstyles3projectclient1;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:SpyList [SpyList]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Chenjie
 */
public class NewJerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:6502/WebServiceDesignStyles3Project/webresources";

    public NewJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SpyList");
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public String addSpy(String spyName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{spyName}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public String helloInHTML() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("hello");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public String deleteSpy(String spyName) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{spyName})).request().delete(String.class);
    }

    public void close() {
        client.close();
    }
    
}

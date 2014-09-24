/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chenjie1;

import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Chenjie
 */
@Path("SpyList")
@Singleton
public class SpyList {

    @Context
    private UriInfo context;

    SpyList1 spylist = SpyList1.getInstance();

    /**
     * Creates a new instance of SpyList
     */
    public SpyList() {
    }

    /**
     * Retrieves representation of an instance of edu.chenjie1.SpyList
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SpyList
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    @DELETE
    @Path("{spyName}")
    @Produces("application/xml")
    public String deleteSpy(@PathParam("spyName") String spyName) {
        Spy delSpy = spylist.get(spyName);
        if (delSpy != null) {
            spylist.delete(delSpy);
            return "Spy " + spyName + " was removed from the list.";
        } else {
            return spyName + " does not exist!";
        }
    }

    @GET
    @Path("{spyName}")
    @Produces("text/html")
    public String addSpy(@PathParam("spyName") String spyName) {
        Spy s = new Spy(spyName);
        spylist.add(s);

        return "Spy " + spyName + " was added to the list.";
    }

    @GET
    @Path("hello")
    @Produces("text/html")
    public String helloInHTML() {
        return "<html><body>Hi</body></html>";
    }
}

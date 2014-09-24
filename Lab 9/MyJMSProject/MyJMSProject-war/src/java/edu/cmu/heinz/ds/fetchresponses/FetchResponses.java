/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cmu.heinz.ds.fetchresponses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author macbookpro
 */
@WebServlet(name = "FetchResponses", urlPatterns = {"/FetchResponses"})

public class FetchResponses extends HttpServlet {
    // Lookup the ConnectionFactory using resource injection and assign to cf
    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory cf;
    // lookup the Queue using resource injection and assign to q
    @Resource(lookup = "jms/myQueueThree")
    private Queue q;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String val = "";
        try {
            // With the ConnectionFactory, establish a Connection, and then a Session on that Connection
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            con.start();
            
            MessageConsumer reader = session.createConsumer(q);	
            TextMessage tm = null;	
            while ((tm = (TextMessage) reader.receive(1000)) != null) {	
                String tmt = tm.getText();
                val = val + tmt;
                System.out.println("Servlet2 receive " + val + " to queue");
            }

            // Close the connection
            con.close();
            out.println("<HTML><BODY><H1>Wrote " + val + " to queue</H1>");
            out.println("</BODY></HTML>");
                       
        } catch (Exception e) {
            System.out.println("Servlet through exception " + e);
        } finally {
            out.close();
        }
    }
  
}
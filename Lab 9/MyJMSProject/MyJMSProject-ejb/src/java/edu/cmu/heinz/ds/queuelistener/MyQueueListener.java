/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cmu.heinz.ds.queuelistener;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author macbookpro
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue")
})
public class MyQueueListener implements MessageListener {
    // Lookup the ConnectionFactory using resource injection and assign to cf
    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory cf;
    // lookup the Queue using resource injection and assign to q
    @Resource(lookup = "jms/myQueueThree")    //QueueTwo for task two
    private Queue q;
    
    public MyQueueListener() {
    }
    
    @Override
    /*
     * When a message is available in jms/myQueueOne, then onMessage is called.
     */
    public void onMessage(Message message) {
        String val = "";
        try {
            // Since there can be different types of Messages, make sure this is the right type.
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String tmt = tm.getText();
                val = tmt;
                System.out.println("MyQueueListener received: " + tmt);
            } else {
                System.out.println("I don't handle messages of this type");
            }
            
            // With the ConnectionFactory, establish a Connection, and then a Session on that Connection
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * You send and receive messages to/from the queue via a session. We
             * want to send, making us a MessageProducer Therefore create a
             * MessageProducer for the session
             */
            MessageProducer writer = session.createProducer(q);

            /*
             * The message can be text, a byte stream, a Java object, or a
             * attribute/value Map We want to send a text message. BTW, a text
             * message can be a string, or it can be an XML object, and often a
             * SOAP object.
             */
            TextMessage msg = session.createTextMessage();
            msg.setText(val);

            // Send the message to the destination Queue
            writer.send(msg);

            // Close the connection
            con.close();
            System.out.println("MyQueueListener sent " + val + " to queue");
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
    
}

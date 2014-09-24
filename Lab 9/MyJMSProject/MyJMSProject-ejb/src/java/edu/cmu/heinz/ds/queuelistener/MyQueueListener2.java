/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cmu.heinz.ds.queuelistener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author macbookpro
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueueTwo")
})
public class MyQueueListener2 implements MessageListener {
    
    public MyQueueListener2() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            // Since there can be different types of Messages, make sure this is the right type.
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String tmt = tm.getText();
                System.out.println("MyQueueListener2 received: " + tmt);
            } else {
                System.out.println("I don't handle messages of this type");
            }
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
    
}

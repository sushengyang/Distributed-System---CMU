package pit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PITsnapshot", urlPatterns = {"/PITsnapshot"})
public class PITsnapshot extends HttpServlet {

    int numPlayers = 4;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Connection con = cf.createConnection();
            con.start(); // don't forget to start the connection
            QueueSession session = (QueueSession) con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // The PITsnapshot Queue is used for responses from the Players to this serverlet
            Queue q = (Queue) ctx.lookup("jms/PITsnapshot");
            MessageConsumer reader = session.createConsumer(q);

            /*
             * Throw out old PITsnapshot messages that may have been left from past
             * snapshots that did not complete (because of some error).
             */
            ObjectMessage m = null;
            while ((m = (ObjectMessage) reader.receiveNoWait()) != null) {
                System.out.println("Found an orphaned PITsnapshot message");
            }

            // Initialize the snapshot by sending a marker to a Player
            sendInitSnapshot();

            /*
             * Receive the snapshot messages from all Players.
             * Each snapshot is a HahsMap.  Put them into an array of HashMaps             * 
             */
            LinkedList<HashMap> state = new LinkedList<HashMap>();
            int stateResponses = 0;
            int failures = 0;
            while (stateResponses < numPlayers) {  
                if ((m = (ObjectMessage) reader.receive(1000)) == null) {
                    if (++failures > 5) {
                        System.out.println("Not all players reported, giving up after "+stateResponses);
                        out.print("Snapshot Failed");                        
                        con.close();
                        return;
                    }
                    continue;
                }
                stateResponses++;
                state.add((HashMap) m.getObject());
                }

            LinkedList<String> commodity = new LinkedList<String>();
            commodity.add("rice");
            commodity.add("oil");
            commodity.add("gold");
            commodity.add("cocoa");
            request.setAttribute("commodity", commodity);
            request.setAttribute("state", state);
            
            // Close the connection
            con.close();
            
            request.getRequestDispatcher("snapshotResult.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Servlet threw exception " + e);
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /*
     * Initiate the snapshot by sending a Marker message to one of the Players (Player0)
     * Any Player could have been used to initiate the snapshot.
     */
    private void sendInitSnapshot() {
        try {
            // Gather necessary JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/myConnectionFactory");
            Queue q = (Queue) ctx.lookup("jms/PITplayer0");
            Connection con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer writer = session.createProducer(q);
            
            /*
             * As part of the snapshot algorithm, players need to record 
             * what other Players they receive markers from.
             * "-1" indicates to the PITplayer0 that this marker is coming from
             * the monitor, not another Player.
             */
            Marker m = new Marker(-1);
            ObjectMessage msg = session.createObjectMessage(m);
            System.out.println("Initiating Snapshot");
            writer.send(msg);
            con.close();
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
            e.printStackTrace();
        }
    }
}

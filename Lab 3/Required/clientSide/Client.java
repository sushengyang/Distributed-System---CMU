import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import javax.net.*;

public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust
            System.setProperty("javax.net.ssl.trustStore","zcj.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6502);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("username:");
            out.write(br.readLine());
            out.newLine();
            out.flush();

            System.out.println("password:");
            out.write(br.readLine());
            out.newLine();
            out.flush();

            String answer = in.readLine();               
            System.out.println(answer);
            out.close(); 
            in.close();
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 
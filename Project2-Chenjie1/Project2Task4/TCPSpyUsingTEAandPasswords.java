import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import javax.net.*;

public class TCPSpyUsingTEAandPasswords {
    
    public static void main(String args[]) {
        
        int port = 6501;
        Socket s = null;
        try {
            
            InetAddress aHost = InetAddress.getLocalHost();
            s = new Socket(aHost, port);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter TEA Key:");
            out.write(br.readLine());
            out.newLine();
            out.flush();

            System.out.println("username:");
            out.write(br.readLine());
            out.newLine();
            out.flush();

            System.out.println("password:");
            out.write(br.readLine());
            out.newLine();
            out.flush();

            System.out.println("location:");
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
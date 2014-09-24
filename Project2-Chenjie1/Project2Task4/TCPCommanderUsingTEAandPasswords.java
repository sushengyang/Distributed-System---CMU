// Server side SSL 

import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;
import java.security.*;

public class TCPCommanderUsingTEAandPasswords {
    public static String quote = "Now rise, and show your strength. Be eloquent, and deep, and tender; see, with a clear eye, into Nature, and into life:  spread your white wings of quivering thought, and soar, a god-like spirit, over the whirling world beneath you, up through long lanes of flaming stars to the gates of eternity!";          
    public static void main(String args[]) {

        int port = 6501;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Accepting secure connections");

            Socket client = serverSocket.accept();
            System.out.println("Got connection");

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            client.getOutputStream()));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            String inputT = in.readLine();
            String username = in.readLine();
            String password = in.readLine();
            String location = in.readLine();
            
            /* Create a cipher using the first 16 bytes of the passphrase */
            TEA tea = new TEA("thisissecretsodon'ttellanyone".getBytes());
            TEA inputTEA = new TEA(inputT.getBytes());
            byte[] original = quote.getBytes();

            /* Run it through the cipher... and back */
            byte[] crypt = tea.encrypt(original);
            byte[] result = inputTEA.decrypt(crypt);

            /* Ensure that all went well */
            String test = new String(result);
            if (!test.equals(quote)) {
                throw new RuntimeException("Fail");
            }

            if ((username.equals("jamesb") && password.equals("james")) || (username.equals("seanb") && password.equals("sean")) || (username.equals("mikem") && password.equals("mike")) || (username.equals("joem") && password.equals("joe"))) {
                System.out.println("Data successfully transmitted to HQ!");
                out.write("Data successfully transmitted to HQ! \n");
                out.flush();
                replace(username,location);
                in.close();
                out.close();
            } else {
                System.out.println("Invalid authentication!!");
                out.write("Invalid authentication!!\n");
                out.flush();
                in.close();
                out.close();

            }

        } catch (Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
    
    static public void replace(String username, String location) {
      String originFile = "SecretAgent.kml";
      String tmpFileName = "tmp.kml";

      BufferedReader br = null;
      BufferedWriter bw = null;
      String str = "";
      try {
         br = new BufferedReader(new FileReader(originFile));
         bw = new BufferedWriter(new FileWriter(tmpFileName));
         String line;
         while ((line = br.readLine()) != null) {
             str += line;
         }
         int user = str.indexOf(username);
         int start = str.indexOf("<coordinates>", user);
         int end = str.indexOf("</coordinates>", user);
         String originCoordinate = str.substring(start + 13, end);
         str = str.replace(originCoordinate, location);
         
         bw.write(str);
      } catch (Exception e) {
         return;
      } finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(originFile);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(tmpFileName);
      newFile.renameTo(oldFile);

   }
}

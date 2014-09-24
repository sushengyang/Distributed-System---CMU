import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServerTCP {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            clientSocket = listenSocket.accept();
            Scanner in;
            PrintWriter out;
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            while (true) {
                String data = in.nextLine();
                System.out.println("Echoing: " + data);
                out.println(data);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
}

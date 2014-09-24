
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServerTCP {

    public static void main(String args[]) throws IOException {
        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;
        int serverPort = 7777; // the server port

        try {
            while (true) {
                ServerSocket listenSocket = new ServerSocket(serverPort);
                clientSocket = listenSocket.accept();
                StringBuffer responseHeader = new StringBuffer("HTTP/1.1 200 OK\n\n");

                //while (true){
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String data = in.readLine();
                String filename = data.substring(5, data.indexOf("HTTP") - 1);
                System.out.print(filename);

                if (!filename.isEmpty()) {
                    BufferedReader fileReader = new BufferedReader(new FileReader(filename));
                    String line = null;
                    while ((line = fileReader.readLine()) != null) {
                        responseHeader.append(line);
                    }
                    fileReader.close();
                    out.write(responseHeader.toString());
                    out.flush();
                    listenSocket.close();
                    clientSocket.close();
                }
                in.close();
            }

            // while (true) {
            //     String input = in.readLine();
            //     System.out.println(input);
            // }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
            out.write("HTTP/1.1 404 Not Found\n\n");
            out.write("<html><body>HTTP/1.1 404 Not Found\n\n</body></html>");
            out.close();

        }

    }
}

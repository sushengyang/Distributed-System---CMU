
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClientWithReliability {

    public static void main(String args[]) throws IOException {
        // args give message contents and server hostname

        String nextLine = null;
        int curr = 0;
        int prev = 0;
        int k = 0;
        System.out.println("Enter k to calculate sum:");
        BufferedReader typed = new BufferedReader(new InputStreamReader(System.in));
        if ((nextLine = typed.readLine()) != null) {
            k = Integer.parseInt(nextLine);
        }

        for (int i = 1; i <= k; i++) {
            curr = add(prev, i);
            prev = curr;
        }
    }

    public static int add(int x, int y) {
        DatagramSocket aSocket = null;
        try {
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;
            aSocket = new DatagramSocket();
            byte[] m = (x + " + " + y + " ").getBytes();
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.setSoTimeout(2000);
            aSocket.receive(reply);

            String replyString = new String(reply.getData());
            String delim = "[\\W]+";
            String[] split = replyString.split(delim);
            System.out.println("Reply: " + replyString);
            return Integer.parseInt(split[2]); 

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            return add(x, y);
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }

        }
        return add(x,y);
    }
}

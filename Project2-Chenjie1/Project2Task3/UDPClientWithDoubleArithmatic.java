
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClientWithDoubleArithmatic {

    public static void main(String args[]) throws IOException {
        // args give message contents and server hostname

        String nextLine = null;
        double curr = 0;
        double prev = 0;

        for (int i = 0; i < 100; i++) {
            curr = add(prev, i + 1.75);
            prev = curr;
        }

    }

    public static double add(double x, double y) {
        DatagramSocket aSocket = null;
        DatagramPacket request = null;

        try {
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;
            aSocket = new DatagramSocket();
            long longx = Double.doubleToLongBits(x);
            long longy = Double.doubleToLongBits(y);
            //System.out.println(longy);

            byte[] mx = longtoByteArray(longx);
            request = new DatagramPacket(mx, mx.length, aHost, serverPort);
            aSocket.send(request);

            byte[] my = (longtoByteArray(longy));
            request = new DatagramPacket(my, my.length, aHost, serverPort);
            aSocket.send(request);


            byte[] buffer = new byte[8];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            long longsum = byteArraytoLong(reply.getData());
            double sum = Double.longBitsToDouble(longsum);

            byte[] buffer2 = new byte[100];
            reply = new DatagramPacket(buffer2, buffer2.length);
            aSocket.receive(reply);
            String replyString = new String(reply.getData());
            System.out.println("Reply: " + replyString);

            return sum;

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }

        }
        return 0;
    }

        public static byte[] longtoByteArray(long num) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) ((num >> ((7 - i) * 8)) & 0xff);
        }
        return b;
    }

    	public static long byteArraytoLong(byte[] bytes) {
        long v = 0;
        for (int i = 0; i < bytes.length; i++) {
            v = (v << 8) + (bytes[i] & 0xff);
        }
        return v;
    }
}

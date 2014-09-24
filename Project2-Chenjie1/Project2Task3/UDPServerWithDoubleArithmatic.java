
import java.net.*;
import java.io.*;
import java.util.Random;

public class UDPServerWithDoubleArithmatic {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        byte[] buffer = new byte[8];
        // Random rnd = new Random();

        try {
            aSocket = new DatagramSocket(6789);
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            while (true) {
                aSocket.receive(request);
                long longx = byteArraytoLong(request.getData());
                //System.out.println(longx);

                aSocket.receive(request);
                long longy =byteArraytoLong(request.getData());
                //System.out.println(longy);

                double x = Double.longBitsToDouble(longx);
                double y = Double.longBitsToDouble(longy);
                double sum =0;
                sum = x + y;

                long longsum = Double.doubleToLongBits(sum);
                byte[] output = longtoByteArray(longsum);

                System.out.println("Got request " + "x = " + x + ", y = " + y);
                DatagramPacket reply = new DatagramPacket(output,
                        output.length, request.getAddress(), request.getPort());
                aSocket.send(reply);

                byte[] expression = (x + " + " + y + " = " + sum).getBytes();
                reply = new DatagramPacket(expression,
                        expression.length, request.getAddress(), request.getPort());
                aSocket.send(reply);

            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }

	public static long byteArraytoLong(byte[] bytes) {
        long v = 0;
        for (int i = 0; i < bytes.length; i++) {
            v = (v << 8) + (bytes[i] & 0xff);
        }
        return v;
    }

     public static byte[] longtoByteArray(long num) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) ((num >> ((7 - i) * 8)) & 0xff);
        }
        return b;
    }
}

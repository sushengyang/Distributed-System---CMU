
import java.net.*;
import java.io.*;
import java.util.Random;

public class UDPServerThatIgnoresYou {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        byte[] buffer = new byte[1000];
        Random rnd = new Random();

        try {
            aSocket = new DatagramSocket(6789);
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            while (true) {
                aSocket.receive(request);

                String requestString = new String(request.getData());

                if (rnd.nextInt(10) < 8) {
                    System.out.println("Got request " + new String(request.getData()) + " but ignoring it. ");
                    //continue;
                } else {
                    String delim = "[\\s]+";
                    String[] split = requestString.split(delim);
                    int num1 = Integer.parseInt(split[0]);
                    int num2 = Integer.parseInt(split[2]);
                    int result = 0;
                    String op = split[1];

                    if (op.equals("+")) {
                        result = num1 + num2;
                    } else if (op.equals("-")) {
                        result = num1 - num2;
                    } else if (op.equals("x")) {
                        result = num1 * num2;
                    } else if (op.equals("/")) {
                        result = num1 / num2;
                    } else if (op.equals("^")) {
                        result = (int) Math.pow(num1, num2);
                    }

                    byte[] output = ("" + num1 + op + num2 + " = " + result).getBytes();

                    System.out.println("Got request " + new String(request.getData()));
                    System.out.println("And making a reply");
                    DatagramPacket reply = new DatagramPacket(output,
                            output.length, request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }

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
}

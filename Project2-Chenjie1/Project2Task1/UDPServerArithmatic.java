import java.net.*;
import java.io.*;
public class UDPServerArithmatic{
	public static void main(String args[]){ 
	DatagramSocket aSocket = null;
	byte[] buffer = new byte[1000];

	try{
		aSocket = new DatagramSocket(6789);
		DatagramPacket request = new DatagramPacket(buffer, buffer.length);	
 		while(true){
			aSocket.receive(request);   

			String requestString = new String(request.getData());
			String  delim = "[\\s]+";
			String[] split = requestString.split(delim);
			int num1 = Integer.parseInt(split[0]);
			int num2 = Integer.parseInt(split[2]);
			int result = 0;
			String op = split[1];

			if (op.equals("+")) 
				result = num1 + num2;
			else if (op.equals("-"))
				result = num1 - num2;
			else if (op.equals("x"))
				result = num1 * num2;
			else if (op.equals("/")) 
				result = num1 / num2;
			 else if (op.equals("^"))
				result =(int) Math.pow(num1,num2);
			
			byte[] output = ("" + num1 + op + num2 + " = " + result).getBytes();
			DatagramPacket reply = new DatagramPacket(output, 
      	   	output.length, request.getAddress(), request.getPort());
			


			System.out.println("Echoing: "+requestString);
			aSocket.send(reply);
		}
	}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}
	}
}
// CalculatorClient.java                 
// This client gets a remote reference from the rmiregistry
// that is listening on the default port of 1099.
// It allows the client to quit with a "!". 
// Otherwise, it computes the sum of two integers 
// using the remote calculator.

import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;


public class BumperClient {
   public static void main(String args[]) throws Exception {
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("1000");
        Bumper c  = (Bumper) Naming.lookup("//localhost/CoolCalculator");
        long start = System.currentTimeMillis();
        while (!ctr.equals(n)) {
            ctr = ctr.add(BigInteger.valueOf(1));
            c.bump();
        }
        long stop = System.currentTimeMillis();
        System.out.println(c.get());
        System.out.println(stop-start);
	   }
    }
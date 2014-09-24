/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bumpertest;

import java.math.BigInteger;

/**
 *
 * @author Chenjie
 */
public class Bumpertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String abc = "1";
        System.out.print(hello(abc));
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("1000");
        long start = System.currentTimeMillis();
        while (!ctr.equals(n)) {
            ctr = ctr.add(BigInteger.valueOf(1));
            bump();
        }
        long stop = System.currentTimeMillis();
        System.out.println(get());
        System.out.println(stop-start);
                
    }

    private static String hello(java.lang.String name) {
        chenjie1.bumper.BumperServer2_Service service = new chenjie1.bumper.BumperServer2_Service();
        chenjie1.bumper.BumperServer2 port = service.getBumperServer2Port();
        return port.hello(name);
    }

    private static BigInteger get() {
        chenjie1.bumper.BumperServer2_Service service = new chenjie1.bumper.BumperServer2_Service();
        chenjie1.bumper.BumperServer2 port = service.getBumperServer2Port();
        return port.get();
    }


    private static boolean bump() {
        chenjie1.bumper.BumperServer2_Service service = new chenjie1.bumper.BumperServer2_Service();
        chenjie1.bumper.BumperServer2 port = service.getBumperServer2Port();
        return port.bump();
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chenjie1.bumper;

import java.math.BigInteger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Chenjie
 */
@WebService(serviceName = "bumperServer2")

public class bumperServer2 {

     BigInteger sum = new BigInteger("0");
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    @WebMethod(operationName = "bump")
    public boolean bump() {
      // A call on bump() adds 1 to a BigInteger held by the service.
      // It then returns true on completion.
      // The BigInteger is changed by the call on bump(). That is, 
      // 1 is added to the BigInteger and that value persists until
      // another call on bump occurs. 
        sum = sum.add(BigInteger.valueOf(1));
        return true;
        
    }
    
    @WebMethod(operationName = "get")
    public BigInteger get() {
      // a call on get returns the BigInteger held by the service  
        return sum;
    }
}

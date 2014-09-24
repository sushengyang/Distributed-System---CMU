/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.StringTokenizer;

/**
 *
 * @author Chenjie
 */
public class StockRMIClientSubscription {
    public static void main(String[] args) throws Exception{
        BufferedReader br  = 
                     new BufferedReader(
                         new InputStreamReader(System.in));  
        // connect to the rmiregistry and get a remote reference to the StockRMI object.
        StockRMI c  = (StockRMI) Naming.lookup("//localhost/stockService");
        System.out.println("Enter user name:");
        String userName = br.readLine();
        System.out.println("Enter S for subscribe or U for unsubscribe followed by the the stock symbol. ! for quit.");
        while (true){
            System.out.print(">");
            String line = br.readLine();
            if(line.equals("!")) {
                c.deRegisterCallBack(userName);
                System.exit(0);
            }
            else if(!line.equals("")){
                StringTokenizer st = new StringTokenizer(line);
                String subscribe = st.nextToken();
                String stockName = st.nextToken();
                if (subscribe.equals("S")) c.subscribe(userName, stockName);
                if (subscribe.equals("U")) c.unSubscribe(userName, stockName);
                
    }
            
    
}
    }
}
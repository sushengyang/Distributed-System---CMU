/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.StringTokenizer;

/**
 *
 * @author Chenjie
 */
public class StockRMIClientPriceUpdate {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br  = 
                     new BufferedReader(
                         new InputStreamReader(System.in));  
        // connect to the rmiregistry and get a remote reference to the StockRMI object.
        StockRMI c  = (StockRMI) Naming.lookup("//localhost/stockService");
        
        while (true){
            System.out.print(">");
            String line = br.readLine();
            if(line.equals("!")) System.exit(0);
            if(!line.equals("")){
                StringTokenizer st = new StringTokenizer(line);
                String stockName = st.nextToken();
                String stockPrice = st.nextToken();
                double price = Double.parseDouble(stockPrice);
                c.stockUpdate(stockName, price);
            }
            
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Chenjie
 */
public class StockRMIServer {
    public static void main(String args[]) throws RemoteException, MalformedURLException{
        StockRMI stockService = new StockRMIServant();
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind("stockService", stockService);
        System.out.println("Servant object ready");
    }
    
    
}

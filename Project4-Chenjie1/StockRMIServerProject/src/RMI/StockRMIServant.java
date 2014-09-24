/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chenjie
 */
public class StockRMIServant extends UnicastRemoteObject implements StockRMI{
   
    /* Given a stock, get a list of users that are interested in that stock. */
    private static Map stocks = new TreeMap();
    /* Given a user, get the remote object reference to its callback method. */
    private static Map users = new TreeMap();
    //private static Set<String> subUsers = new HashSet<String>();
    
    

    public StockRMIServant() throws RemoteException{    
    }

    @Override
    public boolean subscribe(String user, String stockSym) throws RemoteException {
        Set<String> subUsers = new HashSet<String>();
        if (stocks.get(stockSym)!=null) subUsers = (Set<String>) stocks.get(stockSym);
        subUsers.add(user);
        stocks.put(stockSym, subUsers);
        return true;
    }

    @Override
    public boolean unSubscribe(String user, String stockSym) throws RemoteException {
        Set<String> subUsers = new HashSet<String>();
        if (stocks.get(stockSym)!=null) subUsers = (Set<String>) stocks.get(stockSym);
        subUsers.remove(user);
        stocks.put(stockSym, subUsers);
        return true;
    }

    @Override
    public void stockUpdate(String stockSym, double price) throws RemoteException {
            Set<String> notifyUser = new HashSet<String>();
            if (stocks.get(stockSym) != null){
            notifyUser = (Set) stocks.get(stockSym);
            for (String user : notifyUser){
            Notifiable c = (Notifiable) users.get(user);
            c.notify(stockSym, price);
            }
            }
    }

    @Override
    public void registerCallBack(Notifiable remoteClient, String user) throws RemoteException {
        users.put(user, remoteClient);
    }

    @Override
    public void deRegisterCallBack(String user) throws RemoteException {
            Notifiable c = (Notifiable) users.get(user);
            c.exit();
            users.remove(user);
        
    }
  
}

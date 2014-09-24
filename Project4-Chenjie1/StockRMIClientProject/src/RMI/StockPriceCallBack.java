package RMI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author Chenjie
 */
public class StockPriceCallBack extends UnicastRemoteObject implements Notifiable{
    public StockPriceCallBack() throws RemoteException{
    }

    @Override
    public void notify(String stockSym, double price) throws RemoteException {
        System.out.println(stockSym + ": $" + price);
    }

    @Override
    public void exit() throws RemoteException {
        try{
            UnicastRemoteObject.unexportObject(this, true);
            System.out.println("StockPriceCallBack existing");
        }
        catch(Exception e) {
            System.out.println("Exception thrown" + e);
        }
    }
    
    public int test(){
        return 2;
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter user name:");
        String userName = br.readLine();
        System.out.println("Looking up the server in the registry..");
        // connect to the rmiregistry and get a remote reference to the StockRMI object.
        StockRMI c  = (StockRMI) Naming.lookup("//localhost/stockService");        
        System.out.println("Creating a callback object to handle calls from the server.");
        Notifiable s = new StockPriceCallBack();
        System.out.println("Registering the callback with a name at the server.");
        //Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind("PriceCallBack", s);
        c.registerCallBack(s, userName);
        System.out.println("Callback handler for StockDealer ready.");
        
    }
    
}

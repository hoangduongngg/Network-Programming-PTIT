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

/**
 *
 * @author dinht
 */
public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        IProduct product = new ProductImport();
        LocateRegistry.createRegistry(2207);
        //LocateRegistry.getRegistry("localhost", 2207);
        Naming.rebind("rmi://localhost:2207/product", product); //dang ky doi tuong
        System.out.println("success");
    }
}

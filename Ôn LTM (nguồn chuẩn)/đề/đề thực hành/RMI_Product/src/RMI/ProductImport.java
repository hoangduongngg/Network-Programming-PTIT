/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author dinht
 */
public class ProductImport  extends UnicastRemoteObject implements IProduct{

    public ProductImport() throws RemoteException {
    }
    
    

    @Override
    public Product getProduct(String studentCode) throws RemoteException {
        Product product = new Product(1, studentCode, "name", 10, 20, "a");
        return product;
    }
    
    @Override
    public boolean insertProduct(Product product) {
        return true;
    }
    
}

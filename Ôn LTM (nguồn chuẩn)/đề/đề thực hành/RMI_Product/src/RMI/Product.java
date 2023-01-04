/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author dinht
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 20151107;
    
    public Product() throws RemoteException{
    }

    public Product(int id, String code, String name, float importPrice, float exportPrice, String createdUser) throws RemoteException {
        this.id = id;
        this.code = code;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.createdUser = createdUser;
    }
    
    
    
    
    public int id;
    public String code;
    public String name;
    public float importPrice;
    public float exportPrice;
    public String createdUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    
    
    public String toString(){
        return id + code + name + importPrice + exportPrice + createdUser;
    }
    
    
    
    
    
}

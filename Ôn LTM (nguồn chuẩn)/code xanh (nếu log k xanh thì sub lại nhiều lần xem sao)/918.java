package LTM_Final;

import TCP.Customer918;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class A918 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("203.162.10.109", 2209);
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        String ma = "";
        oos.writeObject(ma);
        Customer918 cus = (Customer918) ois.readObject();
        String[] names = cus.getName().toLowerCase().trim().split("\\s+");
        String name = names[names.length-1].toUpperCase() + ",";
        for(int i=0;i<names.length-1;i++) {
            name += " " + names[i].substring(0,1).toUpperCase() + names[i].substring(1);
        }
        cus.setName(name);
        String[] dobs = cus.getDayOfBirth().split("-");
        String dob = dobs[1] + "/" + dobs[0] + "/" + dobs[2];
        cus.setDayOfBirth(dob);
        String userName = "";
        for(int i=0;i<names.length-1;i++) {
            userName+=names[i].substring(0,1);
        }
        cus.setUserName(userName + names[names.length-1]);
        oos.writeObject(cus);
        client.close();
    }
}

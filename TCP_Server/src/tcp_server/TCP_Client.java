/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp_server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hoangduongngg
 */
public class TCP_Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("localhost", 1107);
        System.out.println(client);
        
        //Dang 1: Gui a, b
        DataInputStream dis = new DataInputStream(client.getInputStream());
        int a = dis.readInt();
        int b = dis.readInt();
        System.out.println(a +" "+ b);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeLong(a+b);
        
//        //Dang 2: Object
//        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
//        Student s = (Student) ois.readObject();
//        System.out.println(s.getCode());
//        ois.close();


//        //Dang 3: Buffer
//        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        System.out.println(br.readLine());
//        br.close();
//
//        client.close();
        
    }

}

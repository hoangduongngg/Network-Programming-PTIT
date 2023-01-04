/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp_server;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hoangduongngg
 */
public class TCP_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1107);
        System.out.println("server start...");
        while (true) {            
            Socket conn = server.accept();
            System.out.println("a new request:" + conn);
            
            //Dang 1: gui a, b    
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeInt((int)(Math.random()));
            
            DataInputStream dis= new DataInputStream(conn.getInputStream());
            System.out.println(dis.readLong());
            conn.close();

//            //Dang 2: gui Object
//            ObjectOutputStream obs = new ObjectOutputStream(conn.getOutputStream());
//            Student s = new Student();
//            obs.writeObject(s);
//            obs.close();
//            conn.close();
            
//            //Dang 3: Buffer
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            bw.write("Hello");
//            bw.newLine(); //hoac \n cung duoc
//            bw.flush(); // giai phong bo nho dem
////            bw.close();  khong hieu qua bang flush
//            conn.close();
//            
//            
            
        }
        
        
    }
    
}

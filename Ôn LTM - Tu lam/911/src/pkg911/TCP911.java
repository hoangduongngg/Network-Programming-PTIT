/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg911;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author hoangduongngg
 */
public class TCP911 {

    public static void main(String[] args) throws IOException {
        //1. Khoi tao socket
        Socket socket = new Socket("192.165.1.1", 2007);
        
        //2. Khoi tao luong vao ra
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        
        //3. Tinh toan cac gia tri can gui di
        String str = "B19DCCN000;911";
        dos.writeUTF(str);
        
        int a = dis.readInt();
        int b = dis.readInt();
        int tong = a+b;
        int tich = a*b;
        int ucln = UCLN(a, b);
        int bcnn = tich/ucln;
        
        //4. Send
        dos.write(ucln);
        dos.write(bcnn);
        dos.write(a);
        dos.write(b);
        
        socket.close();
        
    }
    
    public static int UCLN(int a, int b) {
        while(a!=b) {
            if (a>b) a-=b;
            else b-=a;
        }
        return a;
    }
    
}

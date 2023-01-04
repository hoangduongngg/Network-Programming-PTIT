/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author hoangduongngg
 */
public class TCP721 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //1. Khai bao socket
        Socket socket = new Socket("", 2001);
        
        //2. Khai bao Buffer
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        //3. Tinh toan
        String str = "b19dccn000;721";
        bw.write(str);
        
        bw.newLine();
        bw.flush();
        String s = br.readLine();
        String res = "";
        bw.flush();
        
        //4. Send
        bw.write(s);
        
        br.close();
        bw.close();
        socket.close();
    }
    
}

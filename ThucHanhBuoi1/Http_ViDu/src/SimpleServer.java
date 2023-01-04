
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hoangduongngg
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        
        while (true) {
            Socket req = socket.accept();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
                    
            String line = br.readLine();
            
            while (!line.isEmpty()) {
                System.out.println(line);
                line = br.readLine();
            }
            
//            OutputStream os = new OutputStream

            
            br.close();
            socket.close();
        }
    }
}

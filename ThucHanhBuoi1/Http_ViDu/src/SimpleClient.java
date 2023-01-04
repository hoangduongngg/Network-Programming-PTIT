
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hoangduongngg
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8080);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String header = "";
        header += "GET / HTTP /1.1 \r/n";
        header += "Host: localhost \r/n";
        
        
        bw.write(header);
        bw.newLine();
        bw.flush();
        
        InputStream is = client.getInputStream();
//        byte[] data = byte[1024];
        
        
        is.close();
        bw.close();
        client.close();
        
    }
}

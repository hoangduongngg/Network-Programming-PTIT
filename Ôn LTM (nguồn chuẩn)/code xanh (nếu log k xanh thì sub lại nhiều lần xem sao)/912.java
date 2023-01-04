/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpbai2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.regex.Pattern;

public class Client {
    Socket socket=null;
    BufferedReader br = null;
    BufferedWriter bw = null;
    String ip="203.162.10.109";
    int port=2208;
    
    public Client() {
        try {

            connection();

            bw.write("msv");
            bw.newLine();
            bw.flush();

            String s = br.readLine();

            String s1 = "", s2 = "";
            String check = "[a-zA-Z0-9]";
            for (int i = 0; i < s.length(); i++){
                if (Pattern.matches(check, s.charAt(i) + "")){
                    s1+= s.charAt(i);
                } else s2+= s.charAt(i);
            }

            bw.write(s1);
            bw.newLine();
            bw.flush();

            bw.write(s2);
            bw.newLine();
            bw.flush();

            close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }        
        
    }
    public void connection(){
        try {
            socket= new Socket(ip, port); 
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void close(){
        if(socket!=null && br != null && bw != null){
            try {
                br.close();
                bw.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
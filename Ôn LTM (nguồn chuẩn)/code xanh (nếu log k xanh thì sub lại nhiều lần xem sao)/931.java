/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//a1,...,a50  In số Max Min
package udp931;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        String str;
        str = "";
        DatagramPacket dpreq = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("203.162.10.109"), 2207);
        client.send(dpreq);
        
        byte[] buf = new byte[1024];
        DatagramPacket dpReq = new DatagramPacket(buf, buf.length);
        client.receive(dpReq);
        
        String Receive = new String(dpReq.getData()).trim();
        String[] s1 = Receive.split(";");
        StringBuilder r = new StringBuilder(s1[0] + ";");
        StringTokenizer token = new StringTokenizer(Receive, r + ",;");
        List<Integer> a = new ArrayList<>();
        while(token.hasMoreTokens()){
            
            a.add(Integer.parseInt(token.nextToken()));
            Collections.sort(a);
        }
        //System.out.println(a.get(0).toString() + a.get(4));
        
        
        String kq = r + ";" + a.get(0) + "," + a.get(a.size()-1);
        DatagramPacket sendSer = new DatagramPacket(kq.getBytes(), kq.length(), InetAddress.getByName("203.162.10.109"), 2207);
        client.send(sendSer);
        
        client.close();
    }
}

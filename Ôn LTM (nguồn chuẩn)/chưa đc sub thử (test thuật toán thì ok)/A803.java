/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class A803 {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("203.162.10.109");
        int port = 2208;
        
        String msv = 
        byte[] send = msv.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(send, send.length, inetAddress, port);
        client.send(datagramPacket);

        byte[] receiveBytes = new byte[1024];
        DatagramPacket dta = new DatagramPacket(receiveBytes, receiveBytes.length);
        client.receive(dta);

        String[] s= new String(dta.getData()).split(";");
        String result = s[0] +";" + Xuly(s[1]);
        byte[] sendBytes = result.getBytes();
        DatagramPacket dtaPacket = new DatagramPacket(sendBytes,sendBytes.length , inetAddress, port);
        client.send(dtaPacket);
        client.close();


    }

    static String Xuly(String s) {
        String a = "";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        int numMax = 1;
        char charMax = charArray[0];
        for (char c : charArray) {
            if (map.containsKey(c)) {
                int num = map.get(c) + 1;
                if (num > numMax) {
                    numMax = num;
                    charMax = c;
                }
                map.put(c, num);
            } else {
                map.put(c, 1);
            }
        }
        a = charMax + ":";
        int i = -1;
        while (true) {
            i = s.indexOf(charMax, i + 1);
            if (i == -1)
                break;
            a += (i + 1) + ",";
        }
        return a;
    }
}

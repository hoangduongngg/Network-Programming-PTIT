/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class C801 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress=InetAddress.getByName("203.162.10.109");
            DatagramSocket client = new DatagramSocket();
            byte[] sendDat=new byte[1024];
            byte[] receiveDat=new byte[1024];
            
            String s = "";
            sendDat=s.getBytes();
            
            DatagramPacket sendDatagramPacket=new DatagramPacket(sendDat, sendDat.length, inetAddress, 2207);
            client.send(sendDatagramPacket);
            DatagramPacket receiveDatagramPacket=new DatagramPacket(receiveDat, receiveDat.length);
            client.receive(receiveDatagramPacket);
            String receivedat = new String(receiveDatagramPacket.getData());
            String[] t =receivedat.split(";");
            int n=Integer.valueOf(t[1].trim());
            String[] a=(t[2].split(","));
            int len=a.length;
            int[] b=new int[len];
            for(int i=0;i<len;i++){
                b[i]=Integer.valueOf(a[i].trim());
            }
            String send=t[0]+";";
            for(int i=1;i<=n;i++){
                int flag=0;
                for (int j = 0; j < len; j++) {
                    if(i==b[j])                    flag=1;
                }
                if(flag==0) send+=i+",";

            }
            send = send.substring(0,send.length()-1);
            byte[] sendda=send.getBytes();
            DatagramPacket sendDatagramPacket1=new DatagramPacket(sendda, sendda.length, inetAddress, 2207);
            client.send(sendDatagramPacket1);
            client.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(C801.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(C801.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(C801.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

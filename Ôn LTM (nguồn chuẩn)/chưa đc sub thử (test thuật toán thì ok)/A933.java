/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import UDP.Student933;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Admin
 */
public class A933 {

      public static void main(String[] args) {
            try {

                  DatagramSocket socket = new DatagramSocket();
                  InetAddress host = InetAddress.getByName("localhost");
                  int port = 2207;

                  String studentCode = 
                  byte[] write = studentCode.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(write, write.length, host, port);
                  socket.send(sendPacket);

                  byte[] read = new byte[1024];
                  DatagramPacket receivePacket = new DatagramPacket(read, read.length);
                  socket.receive(receivePacket);

                  ByteArrayInputStream inputStream = new ByteArrayInputStream(read);
                  ObjectInputStream ois = new ObjectInputStream(inputStream);

                  Student933 student933 = (Student933) ois.readObject();
                  String nameStudent = student933.getName();
                  student933.setName( chuanHoaChuoi(nameStudent));
                  student933.setEmail(chuannHoaEmail(nameStudent));
                  
                  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                  ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                  oos.writeObject(student933);
                  byte[] write1 = outputStream.toByteArray();
                  DatagramPacket sendPacket1 = new DatagramPacket(write1, write1.length, host, port);
                  socket.send(sendPacket1);

            } catch (Exception e) {
                  e.printStackTrace();
            }
      }

      public static String chuanHoaChuoi(String st){
            String[] s= st.trim().toLowerCase().split("\\s+");
            st="";
            for(int i=0;i<s.length;i++)
                  st+=s[i].substring(0,1).toUpperCase() + s[i].substring(1) +" ";
            st = st.substring(0,st.length()-1);
            return st;
      }

      public static String chuannHoaEmail(String st) {
            String[] s= st.trim().toLowerCase().split("\\s+");
            st = s[s.length - 1];
            for(int i=0 ; i < s.length-1 ; i++)
                  st += s[i].substring(0,1);
            st += "@ptit.edu.vn";
            return st;
      }
}

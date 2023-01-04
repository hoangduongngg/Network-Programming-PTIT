package tcp913;


import TCP.Student;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket ClientSocket = new Socket("203.162.10.109", 2209);

        
        ObjectInputStream inFromServer = new ObjectInputStream(ClientSocket.getInputStream());
        DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(ClientSocket.getOutputStream());
        
        String ma = "";
        
        outputStream.writeObject(ma);
        
        Student st = (Student) inFromServer.readObject();
        
        if(st.getGpa() > 3.7){
            st.setGpaLetter("A");
        }else if(st.getGpa() > 3.0 && st.getGpa()<=3.7){
            st.setGpaLetter("B");
        }else if(st.getGpa() >2.0 && st.getGpa() <= 3.0){
            st.setGpaLetter("C");
        }else if(st.getGpa() >1.0 && st.getGpa() <= 2.0){
            st.setGpaLetter("D");
        }else if(st.getGpa() >0 && st.getGpa() <=1.0){
            st.setGpaLetter("F");
        }
        
        outputStream.writeObject(st);
        
        inFromServer.close();
        outToServer.close();
        outputStream.close();
    }
}

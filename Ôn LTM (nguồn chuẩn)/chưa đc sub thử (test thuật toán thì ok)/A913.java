
import LTM_Final.TCP.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class A913 {
    public static void main(String[] args) {
        try {
            Socket ClientSocket = new Socket("203.162.10.109", 2209);
            ObjectInputStream ois = new ObjectInputStream(ClientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(ClientSocket.getOutputStream());

            String ma = 
            oos.writeObject(ma);

            Student st = (Student) ois.readObject();
            if(st.getGpa() >3.7 && st.getGpa() <= 4.0) st.setGpaLetter("A");
            else if(st.getGpa() >3.0 && st.getGpa() <= 3.7) st.setGpaLetter("B");
            else if(st.getGpa() >2.0 && st.getGpa() <= 3.0) st.setGpaLetter("C");
            else if(st.getGpa() >1.0 && st.getGpa() <= 2.0) st.setGpaLetter("D");
            else if(st.getGpa() >0 && st.getGpa() <= 1.0) st.setGpaLetter("F");


            oos.writeObject(st);
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

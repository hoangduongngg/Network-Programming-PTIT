

import java.io.IOException;
import java.net.*;

public class A932 {
    public static String chuanHoaChuoi(String st){
        String[] temp= st.trim().toLowerCase().split("\\s+");
        st="";
        for(int i=0;i<temp.length;i++)
            st+=temp[i].substring(0,1).toUpperCase() + temp[i].substring(1) +" ";
        st = st.substring(0,st.length()-1);
        return st;
    }
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inetAddress= InetAddress.getByName("203.162.10.109");

            String msv = 
            byte[] sendBytes = msv.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, 2208);
            client.send(datagramPacket);

            byte[] receiveBytes = new byte[1024];
            DatagramPacket dta = new DatagramPacket(receiveBytes, receiveBytes.length);
            client.receive(dta);
            String[] s = new String(dta.getData()).split(";");
            String result = s[0] +";";
            String data = chuanHoaChuoi(s[1]);
            result += data;

            byte[] receive = result.getBytes();
            DatagramPacket dtp = new DatagramPacket(receive, receive.length, inetAddress, 2208);
            client.send(dtp);

            client.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

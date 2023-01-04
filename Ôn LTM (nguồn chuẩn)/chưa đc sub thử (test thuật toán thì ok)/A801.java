

import java.io.IOException;
import java.net.*;

public class A801 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("203.162.10.109");
            DatagramSocket client = new DatagramSocket();

            String msv = 
            byte[] send= msv.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(send, send.length,inetAddress,2207);
            client.send(datagramPacket);

            byte[] receive = new byte[1024];
            DatagramPacket dta = new DatagramPacket(receive, receive.length);
            client.receive(dta);

            String[] s = new String(dta.getData()).split(";");


            String result = s[0]+";";
            int n = Integer.parseInt(s[1].trim());
            String[] t = s[2].trim().split(",");
            int len = t.length;
            int[] a = new int[len];
            for(int i = 0; i <len;i++){
                a[i] = Integer.parseInt(t[i]);
            }
            for(int i=1; i<=n;i++){
                int flag = 1;
                for(int j = 0;j <len;j++){
                    if(a[j] == i)  flag = 0;
                }
                if(flag==1) result += i +",";
            }
            result = result.substring(0,result.length()-1);

            byte[] rs = result.getBytes();
            DatagramPacket dpack = new DatagramPacket(rs,rs.length,inetAddress,2207);
            client.send(dpack);

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



import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class A931 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("10.170.4x");

            String msv = 
            byte[] sendBytes = msv.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, 1107);
            client.send(datagramPacket);

            byte[] receiveBytes = new byte[1024];
            DatagramPacket dtaPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
            client.receive(dtaPacket);

            String[] s = new String(dtaPacket.getData()).split(";");
            String result = s[0]+";";

            String[] t = s[1].split(",");

            int len = t.length;
            int[] a = new int[len];

            for(int i = 0 ; i<len;i++){
                a[i] = Integer.parseInt(t[i]);
            }

            Arrays.sort(a);
            result += a[len-1] +","+a[0];
            byte[] rs = result.getBytes();

            DatagramPacket dataPacket = new DatagramPacket(rs, rs.length, inetAddress, 1107);
            client.send(dataPacket);

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

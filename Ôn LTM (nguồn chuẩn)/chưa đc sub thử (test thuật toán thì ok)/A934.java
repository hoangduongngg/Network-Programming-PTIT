

import java.io.IOException;
import java.net.*;

public class A934 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("");
            DatagramSocket client = new DatagramSocket();
            int port = 1107;
            String msv = 
            byte[] sendbytes = msv.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(sendbytes, sendbytes.length, inetAddress, port);
            client.send(datagramPacket);

            byte[] receive = new byte[1024];
            DatagramPacket dta = new DatagramPacket(receive, receive.length);
            String receivedString = new String(dta.getData());
            String[] str = receivedString.trim().split(";");
        String strEncode = str[1].trim();
        int s = Integer.parseInt(str[2].trim());
        char[] charArray = strEncode.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((charArray[i] + "").matches("[a-z]")) {
                charArray[i] = (char) ((charArray[i] - 'a' + s) % 26 + 'a');
            } else if ((charArray[i] + "").matches("[A-Z]")) {
                charArray[i] = (char) ((charArray[i] - 'A' + s) % 26 + 'A');
            }
        }
        String result = str[0] + ";" + new String(charArray);
        byte[] send = result.getBytes();

            DatagramPacket dataP = new DatagramPacket(send, send.length , inetAddress, port);
            client.send(dataP);

            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
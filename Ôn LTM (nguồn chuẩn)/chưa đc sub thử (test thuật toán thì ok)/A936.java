

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class A936 {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        String str;
        str = 
        DatagramPacket dpreq = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("localhost"), 1108); //203.162.10.109
        client.send(dpreq);


        byte[] buf = new byte[1024];
        DatagramPacket dpReq = new DatagramPacket(buf, buf.length);
        client.receive(dpReq);

        String Receive = new String(dpReq.getData()).trim();
        String[] data = Receive.split(";");
        data[2] = "["+data[2]+"]";
        String[] token = data[1].split(data[2]);

        String kq ="";
        for(String x : token)       kq += x;

        String kqSendSer = "requestID;" + kq;
        byte[] send = kqSendSer.getBytes();
        DatagramPacket sendSer = new DatagramPacket(send,send.length, InetAddress.getByName("localhost"), 1108);
        client.send(sendSer);

        client.close();
    }
}

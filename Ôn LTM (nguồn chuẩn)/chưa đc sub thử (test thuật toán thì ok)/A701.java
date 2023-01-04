
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class A701 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            InputStream is = new DataInputStream(client.getInputStream());
            OutputStream os = new DataOutputStream(client.getOutputStream());
            String msv = "" ;
            byte[] send = msv.getBytes();
            os.write(send);

            byte[] receive = new byte[1024];
            is.read(receive);
            String[] s = new String(receive).split(",");
            int[] a = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                a[i] = Integer.parseInt(s[i].trim());
            }
            Arrays.sort(a);
            int Min = Integer.MAX_VALUE;
            for (int i = 0; i < s.length; i++) {
                for (int j = i + 1; j < s.length-1; j++) {
                    Min = Integer.min(Math.abs(a[j] - a[i]), Min);
                }
            }
            int vt1 = 0, vt2 = 0;
            for (int i = 0; i < s.length; i++) {
                for (int j = i + 1; j < s.length-1; j++) {
                    if (Math.abs(a[j] - a[i]) == Min) {
                        vt1 = a[i];
                        vt2 = a[j];
                    }
                }
            }
            String result = Min + ", " + vt1 + ", " + vt2;
            byte[] sendBytes = result.getBytes();
            os.write(sendBytes);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class A911 {
    public static int ucln(int a, int b){
        while(a!=b){
            if(a>b) a-=b;
            else b -= a;
        }
        return a;
    }
    public static void main(String[] args) {
        try {
            Socket client = new Socket( "203.162.10.109", 2207);
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            String msv = 
            dos.writeUTF(msv);
            int a = dis.readInt();
            int b = dis.readInt();
            int tong = a+b;
            int tich = a*b;
            int ucln = ucln(a,b);
            int bcnn = tich/ucln;

            dos.writeInt(ucln);
            dos.writeInt(bcnn);
            dos.writeInt(tong);
            dos.writeInt(tich);

            client.close();
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

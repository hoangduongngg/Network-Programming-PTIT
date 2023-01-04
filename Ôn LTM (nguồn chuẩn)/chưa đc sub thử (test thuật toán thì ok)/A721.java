
import java.io.*;
import java.net.Socket;

public class A721 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109",2208);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msv =
            bw.write(msv);
            bw.newLine();
            bw.flush();

            String s = br.readLine();
            String send = DemKyTu(s);
            bw.write(send);
            bw.newLine();
            bw.flush();

            client.close();
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String DemKyTu(String s) {
        String result="";
        s = s.replaceAll(" ", "");
        int[] d = new int[256];//ascii
        int len = s.length();
        for (int i = 0; i < len; i++) {
            d[s.charAt(i)]++;
        }
        for (int i = 0; i < len; i++) {
            int dem = 0;
            for(int j = 0 ; j <= i;j++){
                if(s.charAt(i) == s.charAt(j))  dem++;
            }
            if(dem == 1){
                if(d[s.charAt(i)] > 1)      result += s.charAt(i) +":" +d[s.charAt(i)] +",";
            }
        }
        return result;
    }
}

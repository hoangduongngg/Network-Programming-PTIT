/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String str="";
        bw.write(str);
        
        bw.newLine();
        bw.flush();
        String s = br.readLine();
        
        System.out.println(s);
        String b = DemKyTu(s);
        bw.write(b);
        bw.flush();
        System.out.println(b);
        
        bw.close();
        br.close();
        socket.close();
        
        
        
    }
        static String Result(String s)
    {
       String a ="";
       HashMap<Character, Integer> map = new HashMap<Character, Integer>();
       s=s.replaceAll(" ", "");
       //s = s.replaceAll("","");      
       
       char[] charArray = s.toCharArray();
        for (char c : charArray)
        {
            if(map.containsKey(c))
            {
                map.put(c, map.get(c)+1);
            }
            else
            {
                  map.put(c, 1);
            }
        }
        int m = 0;
        for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue()>1){
                a = a+entry.getKey()+":"+entry.getValue()+",";
            }
           
        }
         
         a=a.substring(0, a.length()-1);
        return a;
    } 
        static String DemKyTu(String str)
    {
        String a ="";
        str=str.replaceAll(" ", "");
        int counter[] = new int[256];
        int len = str.length();
        for (int i = 0; i < len; i++)
            counter[str.charAt(i)]++;
        char array[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            array[i] = str.charAt(i);
            int flag = 0;
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == array[j])
                    flag++;
            }
            if (flag == 1)
                if(counter[str.charAt(i)]>1){
                    System.out.println("Số lần xuất hiện của " + str.charAt(i)
                        + " trong chuỗi:" + counter[str.charAt(i)]);
                a+=str.charAt(i)+ ":"+counter[str.charAt(i)] +",";
                }
                
        }
        //a=a.substring(0, a.length()-1);
        return a;
    }
}

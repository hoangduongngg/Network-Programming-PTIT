/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.*;
/**
 *
 * @author hoangduongngg
 */
public class FileInputOutputStream {
   
    public static void main(String[] args) throws IOException {
       
        File inputFile = new File("farrago.txt");
        File outputFile = new File("outagain.txt");
        
        //Thuc hang: Sửa  FileInputOutputStream.java để đọc file myowninputfile.txt và ghi nội dung ra file myownoutputfile.txt
        inputFile = new File("myowninputfile.txt");
        outputFile = new File("myownoutputfile.txt");
       
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        int c;
       
        while ((c = in.read()) != -1){
            System.out.println(c);
            out.write(c);
        }
       
        System.out.println("FileInputStream is used to read a file and FileOutPutStream is used for writing.");
       
        in.close();
        out.close();
    }
}

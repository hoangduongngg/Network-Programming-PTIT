/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fileinput_1;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoangduongngg
 */
public class FileInput_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String file = "DATA.txt";
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[1024];
            int i = fis.read(data);
            while (i!=-1) {
                System.out.println(new String(data, 0, i, "utf-8"));
                i = fis.read(data);
            }
            fis.close();
        } catch (Exception ex) {
            Logger.getLogger(FileInput_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

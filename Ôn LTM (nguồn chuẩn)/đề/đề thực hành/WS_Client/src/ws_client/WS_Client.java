/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_client;

import java.util.InputMismatchException;
import java.util.Scanner;
import ws.WSImpl;
import ws.WSImplService;

/**
 *
 * @author Admin
 */
public class WS_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WSImplService service = new WSImplService();
        WSImpl port = service.getWSImplPort();
        ws.Product product = port.getProduct("B18DCCN123");
        Scanner sc = new Scanner(System.in);
            product.setName("name");
            while(product.getName().length() <= 8 || product.getName().length()>=20) {
                    System.out.print("Nhap ten san pham ( tu 8 den 20 ky tu ) : ");
                    product.setName(sc.nextLine());
            }
            product.setImportPrice(-1);
            product.setExportPrice(0);
            while(product.getImportPrice() <= 0 || product.getExportPrice() < product.getImportPrice()) {
                    try {
                        System.out.print("Nhap gia nhap va xuat : ");
                        float im = sc.nextFloat();
                        float ex = sc.nextFloat();
                        product.setImportPrice(im);
                        product.setExportPrice(ex);
                    } catch(InputMismatchException ex) {
                        sc = new Scanner(System.in);
                    }
            }
            System.out.println("Inserted product status : " + port.insertProduct(product));
    }
    
}

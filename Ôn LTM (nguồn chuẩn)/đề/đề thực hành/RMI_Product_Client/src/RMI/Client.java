/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author dinht
 */
public class Client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IProduct iproduct = (IProduct) Naming.lookup("rmi://localhost:2207/product");
        Product product = iproduct.getProduct("B18DCCN509");
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
		System.out.println("Inserted product status : " + iproduct.insertProduct(product));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import javax.jws.WebService;



/**
 *
 * @author Admin
 */
@WebService
public class WSImpl implements WSInterface {

	@Override
	public Product getProduct(String studentCode) {
		Product product = new Product(1, studentCode, "name", 10, 20, "a");
                return product;
	}

	@Override
	public boolean insertProduct(Product product) {
		return true;
	}
	
}

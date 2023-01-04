/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Admin
 */
@WebService
public interface WSInterface {

	@WebMethod
	public Product getProduct(String studentCode);
	@WebMethod
	public boolean insertProduct(Product product);
	
}

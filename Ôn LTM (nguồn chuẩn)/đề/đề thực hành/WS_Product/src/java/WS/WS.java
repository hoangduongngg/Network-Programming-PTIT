/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Admin
 */
public class WS {
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:50000/product",
        new WSImpl());
    }

}

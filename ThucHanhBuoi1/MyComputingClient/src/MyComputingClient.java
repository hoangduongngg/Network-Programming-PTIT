
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author hoangduongngg
 */
public class MyComputingClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        Socket client = null;
       
        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }
       
        for (int i=0; i <10; i++) {
            try {
                String msg = "";
               
                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);
               
                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);
               
                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(clientIn));
               
                // Create BufferedReader for a standard input
                BufferedReader stdIn = new BufferedReader(new
                        InputStreamReader(System.in));
               
                System.out.println("Enter 2 number. Type Bye to exit. ");
               
                // Read data from standard input device and write it
                // to the output stream of the client socket.
                msg = stdIn.readLine().trim();
                if (msg.equalsIgnoreCase("Bye")) {
                    pw.println(msg);
                }
                else {
                    int a = Integer.parseInt(msg);
                    int b = Integer.parseInt(stdIn.readLine());
                    long sum = a+b;
                    pw.println(sum);
                }
                
                
                
                
                
//                pw.println(msg);
               
                // Read data from the input stream of the client socket.
                System.out.println("Message returned from the server = " + br.readLine());
               
                pw.close();
                br.close();
                client.close();
               
                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
               
            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
 
}

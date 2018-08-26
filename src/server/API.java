/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author anjana
 */
public class API {
    
    public static void main(String[] args) throws IOException {
        
        int port = 7500;
        String input_port;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a port number to configure the server to listen"
                + "\nor press c to continue with default port number (7500)");
        System.out.print("Enter the port number: ");
        input_port = scan.nextLine();
        if(input_port.equals("c") || input_port.equals("C")){
           port = 7500;
        }
        else{
            try{
                port = Integer.parseInt(input_port);
            }
            catch (NumberFormatException e){
                port = 7500;
            }
            
        }
        
        ServerSocket listener = new ServerSocket(port);
        
        System.out.println("\nServer started listening to port: # "+port);
        
        ExecutorService clients = Executors.newFixedThreadPool(50);
        try {
            while(true){
                Socket socket = listener.accept();
                try{
                    System.out.println("API is processing the request of client #"+socket.getRemoteSocketAddress());
                    clients.execute(new Server(socket));
//PrintStream clientStream = new PrintStream(socket.getOutputStream());
            
//            clientStream.println("API Sent");
                }
                finally{
//                    socket.close();
                }
            }
        }
        finally{
            listener.close();
        }
        

        
        
    }
}

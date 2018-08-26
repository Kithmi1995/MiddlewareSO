/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author anjana
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        
        
        int port = 7500;
        String input_port, host = "localhost", input, response;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the port number and host to configure the client to connect with"
                + "\nor press c to continue with default port number (7500)"
                + "and default host (localhost)");
        System.out.print("Enter the API port number(7500): ");
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
        
        System.out.print("Enter the API host(localhost): ");
        host = scan.nextLine();
        if(host.equals("c") || host.equals("C")){
           host = "localhost";
        }

        
        Scanner scanInput = new Scanner(System.in);
        
        System.out.println("Welcome to the Service Oriented Middleware program."
                + "\nWe have Encryption - Decryption, Prime Number Testing, Factorial - Fibonacci Services."
                + "\nPlease make a selection.\nTo quite, press q.");
        
        while(true){
            System.out.println("\n\nen <message_to_be_encrypted> - To use the encryption service."
                + "\nde <message_to_be_decrypted> - To use the decryption service."
                + "\nip <number_to_be_check_if_prime> - To use the prime number testing service."
                + "\npr <get_all_primes_till_this_number> - To use the print get the prime numbers service."
                + "\nfa <number_to_get_the_factorial_of> - To use the factorial calculation service."
                + "\nfi <number_to_get_the_fibonacci_number_of> - To use the fibonacci number calculation service.");
        
            System.out.print("\n\nEnter your choice: ");
            input = scanInput.nextLine();
            
            if(input.equals("Q") || input.equals("q")){
                break;
            }
            else if(validateInput(input)){
                
                
                Socket socket = new Socket(host, port);
                Scanner serverInput = new Scanner(socket.getInputStream());
                
                PrintStream serverStream = new PrintStream(socket.getOutputStream());
                serverStream.println(input);
                
                response = serverInput.nextLine();
                System.out.println(response);
                
            }
        }
        
        System.out.println("Quitting the program....\nPress any key to exit.");
        input = scanInput.nextLine();
        
    }
    
    public static boolean validateInput(String input){
        if(input.startsWith("en") || input.startsWith("de")
                || input.startsWith("ip") || input.startsWith("pr")
                || input.startsWith("fa") || input.startsWith("fi") ){
            return true;
        }
        return false;
    }
}

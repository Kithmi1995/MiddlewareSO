/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
//import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anjana
 */
public class Server implements Runnable {
    
    Socket clientSocket;
   
    
    public Server(Socket socket){
        this.clientSocket = socket;
    }
    
    public static String[] getProviders(){
        String[] providers = {"EncryptorService", "PrimeNumberService", "FactFibo"};
        return providers;
    }
    
    public static String[][] getServiceCommandMapping(){
        String[][] serviceCommandMapping = {
            {"EncryptorService", "en", "de"},
            {"PrimeNumberService", "ip", "pr"},
            {"FactFibo", "fa", "fi"}
        };
        return serviceCommandMapping;
    }
    
    public static String[][] getCommandMethodMapping(){
        String[][] commandMethodMapping = {
            {"en", "encrypt"},
            {"de", "decrypt"},
            {"ip", "isPrime"},
            {"pr", "primes"},
            {"fa", "factorial"},
            {"fi", "fibonacci"}
        };
        return commandMethodMapping;
    }
    
    public static int arrayIndexHelper(String[][] haystack, String needle){
        int outer_len = haystack.length;
        for(int x = 0; x < outer_len; x++){
            int inner_len = haystack[x].length;
            for(int y = 0; y < inner_len; y++){
                if(haystack[x][y].equals(needle)){
                    return x;
                }
            }
        }
        return -1;
    }
    
    public static String getServiceProvider(String sericeRequest){
        int indexOfService = arrayIndexHelper(getServiceCommandMapping(), sericeRequest);
        return getProviders()[indexOfService];
    }
    
    public static String getServiceMethod(String command){
        int indexOfMethod = arrayIndexHelper(getCommandMethodMapping(), command);
        return getCommandMethodMapping()[indexOfMethod][(getCommandMethodMapping()[indexOfMethod].length - 1)];
    }
    
    public static boolean validateInput(String input){
        if(input.startsWith("en") || input.startsWith("de")
                || input.startsWith("ip") || input.startsWith("pr")
                || input.startsWith("fa") || input.startsWith("fi") ){
            return true;
        }
        return false;
    }
    
    public static boolean commandValidator(String input){
        return true;
    }

    @Override
    public void run() {
        
        Class <?> parameters[] = {};
        
        String request, response;
        try {
            
            Scanner scanRequest = new Scanner(clientSocket.getInputStream());
            request = scanRequest.nextLine();
            System.out.println("Processing Request at the Service Registry...");
            response = "Your request was: "+request;
            
            PrintStream clientStream = new PrintStream(clientSocket.getOutputStream());
//            clientStream.println(response);
            
            if(validateInput(request)){
                
                String[] inputParameters = request.split(" ");
                
                String serviceProviderClass = getServiceProvider(inputParameters[0]);
             
                String serviceMethod = getServiceMethod(inputParameters[0]);
                
                Class <?> classObject = Class.forName("services."+ serviceProviderClass);
                Object obj = classObject.newInstance();
                
                parameters = new Class[1];
                parameters[0] = String.class;
//                parameters[1] = Integer.class;
                
                Method method = classObject.getDeclaredMethod(serviceMethod, parameters);
                
                
                clientStream.println(method.invoke(obj, inputParameters[1]));
                
                clientStream.flush();
            }
            
            
            
            
//            clientStream.println(response);
            
            
//            // Lookup the Service Registry
//            HashMap <String, String> registry = new HashMap<String, String>();
//
//            registry.put("en", "encrypt");
            
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        

        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

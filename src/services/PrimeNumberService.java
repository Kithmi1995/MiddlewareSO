/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author anjana
 */
public class PrimeNumberService {
    
//    public static void main(String[] args) {
//        System.out.println(primes(10000));
//    }
//    
    public boolean isPrime(String s){
        int n = Integer.parseInt(s);
        if(n == 2){
            return true;
        }
        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        
        return true;
        
    }
    
    public String primes(String s){
        int n = Integer.parseInt(s);
        String prime_str = "";
        
        for(int i = 2; i < n; i++){
            if(isPrime(String.valueOf(i))){
                prime_str += Integer.toString(i)+", ";
            }
        }
        return prime_str.substring(0, (prime_str.length() - 2) );
    }
}

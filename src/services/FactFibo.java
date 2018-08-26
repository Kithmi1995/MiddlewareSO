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
public class FactFibo {
    
//    public static void main(String[] args) {
//        for(int x = 0; x < 15; x++){
//            System.out.print(fibonacci(x) + ", ");
//        }
//        
//    }
    
    
    public int factorial(String s){
        int n = Integer.parseInt(s);
        int fact = 1;
        for(int i = 2; i <= n; i++){
            fact *= i;
        }
        return fact;
    }
    
    public int fibonacci(String s){
        int n = Integer.parseInt(s);
        int a = 0, b = 1, fib = 1;
        for(int i = 1; i <= n; i++){
            fib = a + b;
            a = b;
            b = fib;
        }
        
        return fib;
    }
}

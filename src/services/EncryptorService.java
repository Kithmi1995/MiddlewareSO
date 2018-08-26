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
public class EncryptorService {
    
    public String encrypt(String msg){
        int key = 3;
        String enc_msg = "";
        int msg_len = msg.length();
        char new_char;
        
        for(int i = 0; i < msg_len; i++){
            new_char = (char)(msg.charAt(i) + key);
            enc_msg += new_char;
        }
        
        return enc_msg;
    }
    
    public String decrypt(String msg){
        int key = 3;
        String dc_msg = "";
        int msg_len = msg.length();
        char new_char;

        for(int i = 0; i < msg_len; i++){
            new_char = (char)(msg.charAt(i) - key);
            dc_msg += new_char;
        }

        return dc_msg;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijse.sss.encryption;
/**
 *
 * @author NEOx
 */

public class EncryptionDecryption {
    public static String encrypt(String str){
       String encrypted="";
       char[] encryptData=str.toCharArray();
        for (char c : encryptData) {
            encrypted +=(int) c+",";
        }
        return encrypted;
    }
    
    public static String decrypt(String str){
        String decrypted="";
        String[] decryptData = str.split(",");
        for (String string : decryptData) {
            int dataInt = Integer.parseInt(string);
            decrypted += (char)dataInt;
        }
        return decrypted;
    }
}

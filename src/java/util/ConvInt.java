/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Filipe
 */
public class ConvInt {

    public ConvInt() {
    }
    
    public static int parseInt2(String str){
        String str2 = "";
        for(int i = 0 ; i < (str.length())  ; i++){
            if((str.charAt(i) > '0' && str.charAt(i) < '9')){
                str2 = str2.concat(""+str.charAt(i));
            }else{
                break;
            }
        }
        return Integer.parseInt(str2);
    }
}

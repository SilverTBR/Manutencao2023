/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

public class NumberExecao {
 
    public static boolean verNum(String campo){
    if(campo == null){
        return true;
    } 

    for(int i = 0;i<campo.length(); i++){
        char c = campo.charAt(i);
        if(c < '0' || c > '9'){
            return true;
        }
    }
    return false;
}
}
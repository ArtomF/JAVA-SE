/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Artom
 */
public class Validacao {
    
    public static boolean validarNome(String val){
        String exp = "^[a-z A-Zá-è]{2,100}$";
        return val.matches(exp);
    }
    
    public static boolean validarEmail(String val){
        String exp = "^[a-zA-Z0-9][a-zA-Z0-9\\._-]+@([a-zA-Z0-9\\._-]+\\.)"
                + "[a-zA-Z-0-9]{2,3}$";
        return val.matches(exp);
    }
    
    public static boolean validarLogin(String val){
        String exp = "^[a-zA-Zá-è0-9]{4,50}$";
        return val.matches(exp);
    }
    
    public static boolean validarSenha(String val){
        String exp = "^[a-zA-Z0-9]{6,50}$";
        return val.matches(exp);
    }
    
    public static boolean validarSigla(String val){
        String exp = "^[A-Z]{2}$";
        return val.matches(exp);
    }
     
    public static boolean validarEstado(String val){
        String exp = "^[a-z A-Zá-è]{4,50}$";
        return val.matches(exp);
    }
    
    public static boolean validarFone(String val){
        String exp = "^\\([1-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}$";
        return val.matches(exp);
    }
    
    public static boolean validarNumero(String val){
        String exp = "^[0-9]{1,30}$";
        return val.matches(exp);
    }
    
    public static boolean validarFloat(String val){
        String exp = "^[0-9.]{1,30}$";
        return val.matches(exp);
    }
    
}

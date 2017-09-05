/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Artom
 */
public class ConexaoBanco {
    
    //Atributos statics para acessar o banco
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD =  "";
    
    //Método para acessar o Driver de conexão com o banco de Dados
    public static Connection getConexao() throws SQLException{
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException se) {
            throw new SQLException("Erro ao conectar ao banco! " 
                    + se.getMessage());            
        }
        return con;
    }
    
}

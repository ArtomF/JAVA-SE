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
    
    private static final String URL = "jdbc:mysql://localhost:3306/XXXX";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConexao() throws SQLException{
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar " + e.getMessage());
        }
        return con;
    }
    
}

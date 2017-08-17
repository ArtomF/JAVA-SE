/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ConexaoBanco;
import model.Estado;

/**
 *
 * @author Artom
 */
public class EstadoDAO {
    
    //Método que cadastra estados no banco
    public void cadastrar(Estado e) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement  pst = null;
        try {
            String sql = "insert into estado(idestado, nome_estado, sigla) "
                    + "values(null,? ,?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, e.getNome());
            pst.setString(2, e.getSigla());
            
            pst.execute();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar! " + se.getMessage());
            
        }finally{
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método
    
    public ArrayList<Estado> buscarEstados() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select * from estado";
            
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Estado> est = new ArrayList<>();
            
            while(rs.next()){
                Estado e = new Estado();
                
                e.setIdEstado(rs.getInt("idestado"));
                e.setNome(rs.getString("nome_estado"));
                e.setSigla(rs.getString("sigla"));
                
                est.add(e);
            }//Fecha while
            
            return est;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados! " + se.getMessage());
            
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
}

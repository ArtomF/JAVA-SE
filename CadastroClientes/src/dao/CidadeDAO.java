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
import model.Cidade;
import model.ConexaoBanco;
import model.Estado;

/**
 *
 * @author Artom
 */
public class CidadeDAO {
    
    //Método para cadastrar cidade no banco
    public void cadastrar(Cidade c) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "insert into cidade(idcidade, nome_cidade, idestado) "
                    + "values(null, ?, ?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, c.getNome());
            pst.setInt(2, c.getEstado().getIdEstado());
            
            pst.execute();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar! " + se.getMessage());
            
        }finally{
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método
    
    
    //Método para buscar cidades no banco
    public ArrayList<Cidade> buscarCidades() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select idcidade, nome_cidade, e.sigla from cidade c"
                    + " inner join estado e on c.idestado = e.idestado";
            
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Cidade> cid = new ArrayList<>();
            
            while(rs.next()){
                Cidade c = new Cidade();
                c.setIdCidade(rs.getInt("idcidade"));
                c.setNome(rs.getString("nome_cidade"));
                
                Estado e = new Estado();
                e.setSigla(rs.getString("e.sigla"));
                
                c.setEstado(e);
                
                cid.add(c);
            }//Fecha while
            
            return cid;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar cidades! " 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    public ArrayList<Cidade> filtrarCidade(String query) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select * from cidade " + query + 
                    " order by nome_cidade";
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Cidade> cid = new ArrayList<>();
            
            while(rs.next()){
                Cidade c = new Cidade();
                c.setIdCidade(rs.getInt("idcidade"));
                c.setNome(rs.getString("nome_cidade"));
               
                cid.add(c);
                
            }//Fecha while
            
            return cid;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar cidade!" + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
   
}

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
import model.Bairro;
import model.Cidade;
import model.ConexaoBanco;

/**
 *
 * @author Artom
 */
public class BairroDAO {
    
    //Método para cadastra dados no banco
    public void cadastrar(Bairro b) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "insert into bairro(idbairro, nome_bairro, idcidade) "
                    + "values(null, ?, ?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, b.getNome());
            pst.setInt(2, b.getCidade().getIdCidade());
            
            pst.execute();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar! " + se.getMessage());
        }finally{
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método   
    
    
    public ArrayList<Bairro> buscarBairros() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql = "select idbairro, nome_bairro,"
                    + " nome_cidade from bairro b "
                    + "inner join cidade c on b.idcidade = c.idcidade";
            
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Bairro> ba = new ArrayList<>();
            
            while(rs.next()){
                Bairro b = new Bairro();
                b.setIdBairro(rs.getInt("idbairro"));
                b.setNome(rs.getString("nome_bairro"));
                
                Cidade c = new Cidade();
                c.setNome(rs.getString("nome_cidade"));
                
                b.setCidade(c);
                
                ba.add(b);
            }//Fecha while
            
            return ba;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar bairros !" 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    public ArrayList<Bairro> filtrarBairros(String query) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select * from bairro " + query 
                    + " order by nome_bairro";
            
            ResultSet rs = stat.executeQuery(sql);
                    
            ArrayList<Bairro> ba = new ArrayList<>();
            
            while(rs.next()){
                Bairro b = new Bairro();
                b.setIdBairro(rs.getInt("idbairro"));
                b.setNome(rs.getString("nome_bairro"));
                
                ba.add(b);
                
            }//Fecha while
            
            return ba;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar bairros! " 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método    
}

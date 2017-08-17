/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Artom
 */
public class ProjetoDAO {
    
    public void cadastrarProjeto(Projeto p) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "insert into projeto(id,nome,descricao,prazo,"
                    + "datainicio,datatermino,status) values(null,?,?,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setString(3, p.getPrazo());
            pst.setString(4, p.getDataInicio());
            pst.setString(5, p.getDataFim());
            pst.setString(6, p.getStatus());
            
            pst.execute();
        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no banco! " 
                    + se.getMessage());
        }finally{
            con.close();
            pst.close();
        }
    }
    
    public ArrayList<Projeto> buscarProjeto() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select * from projeto";
            
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Projeto> proj = new ArrayList<>();
            
            while(rs.next()){
                
                Projeto p = new Projeto();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrazo(rs.getString("prazo"));
                p.setDataInicio(rs.getString("datainicio"));
                p.setDataFim(rs.getString("datatermino"));
                p.setStatus(rs.getString("status"));
                
                proj.add(p);
            }//Fecha while
            
            return proj;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do banco "
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public void alterarProjeto(Projeto p) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "update projeto set "
                    + "nome=?, descricao=?, prazo=?, datainicio=?,"
                    + " datatermino=?, status=? where id=?";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setString(3, p.getPrazo());
            pst.setString(4, p.getDataInicio());
            pst.setString(5, p.getDataFim());
            pst.setString(6, p.getStatus());
            pst.setLong(7, p.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao alterar projeto " + se.getMessage());
        }finally{
            con.close();
            pst.close();
        }
    }
    
    public void deletarProjeto(long id) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql = "delete from projeto where id= " + id;
            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar projeto " 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
    
    public ArrayList<Projeto> filtrar(String query) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql = "select * from projeto " + query;
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Projeto> proj = new ArrayList<>();
            
            while(rs.next()){
                Projeto p = new Projeto();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrazo(rs.getString("prazo"));
                p.setDataInicio(rs.getString("datainicio"));
                p.setDataFim(rs.getString("datatermino"));
                p.setStatus(rs.getString("status"));
                
                proj.add(p);
            }
            
            return proj;
            
        } catch (SQLException se) {
            throw new SQLException("Erro! " + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
       
}

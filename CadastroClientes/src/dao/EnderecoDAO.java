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
import model.ConexaoBanco;
import model.Endereco;

/**
 *
 * @author Artom
 */
public class EnderecoDAO {

    //Método para cadastrar endereço no banco de dados
    public int cadastrarEndereco(Endereco e) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "insert into endereco(rua, numero, complemento,"
                    + " idbairro, idcidade, idestado) values(?,?,?,?,?,?)";
            
            pst = con.prepareStatement(sql, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, e.getRua());
            pst.setLong(2, e.getNumero());
            pst.setString(3, e.getComplemento());
            pst.setInt(4, e.getBairro().getIdBairro());
            pst.setInt(5, e.getCidade().getIdCidade());
            pst.setInt(6, e.getEstado().getIdEstado());
            
            pst.execute();
            
            ResultSet rs = pst.getGeneratedKeys();
            int chaveGerada = 0;
           
            /* Testando se o resultSet é diferente de null
               e se ele tem um próximo. Se tiver, buscamos 
               a chaveGerada com o método getInt(1) */
            if (rs != null && rs.next()) {
                chaveGerada = rs.getInt(1);
            }
            
            //retornando a chave que foi gerada
            return chaveGerada;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar endereço! "
                    + se.getMessage());
        } finally {
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método
    
    //Método para alterar dados no banco
    public void alterarEndereco(Endereco e) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            String sql = "update endereco set rua = ?, numero = ?,"
                    + " complemento = ?, idbairro = ?, idcidade = ?,"
                    + " idestado = ? where idend = ?";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getRua());
            pst.setLong(2, e.getNumero());
            pst.setString(3, e.getComplemento());
            pst.setInt(4, e.getBairro().getIdBairro());
            pst.setInt(5, e.getCidade().getIdCidade());
            pst.setInt(6, e.getEstado().getIdEstado());
            pst.setInt(7, e.getIdEnd());
            
            pst.execute();
            
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao alterar dados da tabela endereço!" 
            + se.getMessage());
            
        }finally{
            con.close();
            pst.close();
        }//fecha finally
    }//Fecha método
}

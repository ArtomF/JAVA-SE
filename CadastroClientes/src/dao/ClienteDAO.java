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
import model.Cliente;
import model.ConexaoBanco;
import model.Endereco;
import model.Estado;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Artom
 */
public class ClienteDAO {
    
    //Método para cadastrar clientes no banco 
    public void cadastrarCliente(Cliente c) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {
            EnderecoDAO eDAO = new EnderecoDAO();
            int chaveGerada = eDAO.cadastrarEndereco(c.getEndereco());
            
            String sql = "insert into cliente(idcliente, nome, fone, email, "
                    + "idend) values(null,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, c.getNome());
            pst.setString(2, c.getFone());
            pst.setString(3, c.getEmail());
            pst.setInt(4, chaveGerada);
            
            pst.execute();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar cliente! "
                    + se.getMessage());
        }finally{
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método
    
    //Método para buscar cliente e seu endereço do banco de dados
    public ArrayList<Cliente> buscarClientes() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select idcliente, c.nome, fone, email, e.rua,"
                    + " e.numero, e.complemento,"
                    + " e.idestado, e.idcidade, e.idbairro,"
                    + " nome_bairro, nome_cidade,"
                    + " u.sigla from cliente c"
                    + " inner join endereco e on c.idend = e.idend"
                    + " inner join bairro b on e.idbairro = b.idbairro"
                    + " inner join cidade l on e.idcidade = l.idcidade"
                    + " inner join estado u on e.idestado = u.idestado";
            
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Cliente> cli = new ArrayList<>();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdCli(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setFone(rs.getString("fone"));
                c.setEmail(rs.getString("email"));
                
                Endereco e = new Endereco();
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setComplemento(rs.getString("complemento"));
                
                Estado es = new Estado();
                es.setIdEstado(rs.getInt("e.idestado"));
                es.setSigla(rs.getString("u.sigla"));
                
                e.setEstado(es);
                
                Cidade ci = new Cidade();
                ci.setIdCidade(rs.getInt("e.idcidade"));
                ci.setNome(rs.getString("nome_cidade"));
                
                e.setCidade(ci);
                
                Bairro b = new Bairro();
                b.setIdBairro(rs.getInt("e.idbairro"));
                b.setNome(rs.getString("nome_bairro"));
                
                e.setBairro(b);
                
                c.setEndereco(e);
                
                cli.add(c);
                
            }//Fecha while
            
            return cli;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar cliente! " 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    //Método que altera os dados no banco
    public void alterar(Cliente c) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;
        
        try {           
            String sql = "update cliente set nome = ?, fone = ?, email = ?,"
                    + " idend = ? where idcliente = ?";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, c.getNome());
            pst.setString(2, c.getFone());
            pst.setString(3, c.getEmail());
            pst.setInt(4, c.getEndereco().getIdEnd());
            pst.setInt(5, c.getIdCli());
            
            pst.execute();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao alterar cliente! "
                    + se.getMessage());
        }finally{
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método
    
    //Método para deletar cliente e o seu endereço do banco
    public void deletar(int id) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "delete from cliente, endereco using cliente"
                    + " inner join endereco where cliente.idend = " + id;

            stat.execute(sql);
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar! " + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    public ArrayList<Cliente> filtrarClientes(String query) throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql = "select idcliente, c.nome, fone, email, e.rua,"
                    + " e.numero, e.complemento,"
                    + " e.idestado, e.idcidade, e.idbairro,"
                    + " nome_bairro, nome_cidade,"
                    + " u.sigla from cliente c"
                    + " inner join endereco e on c.idend = e.idend"
                    + " inner join bairro b on e.idbairro = b.idbairro"
                    + " inner join cidade l on e.idcidade = l.idcidade"
                    + " inner join estado u on e.idestado = u.idestado " + query;
            
            ResultSet rs = stat.executeQuery(sql);
            
            ArrayList<Cliente> cli = new ArrayList<>();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdCli(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setFone(rs.getString("fone"));
                c.setEmail(rs.getString("email"));
                
                Endereco e = new Endereco();
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setComplemento(rs.getString("complemento"));
                
                Estado es = new Estado();
                es.setIdEstado(rs.getInt("e.idestado"));
                es.setSigla(rs.getString("u.sigla"));
                
                e.setEstado(es);
                
                Cidade ci = new Cidade();
                ci.setIdCidade(rs.getInt("e.idcidade"));
                ci.setNome(rs.getString("nome_cidade"));
                
                e.setCidade(ci);
                
                Bairro b = new Bairro();
                b.setIdBairro(rs.getInt("e.idbairro"));
                b.setNome(rs.getString("nome_bairro"));
                
                e.setBairro(b);
                
                c.setEndereco(e);
                
                cli.add(c);
                
            }//Fecha while
            
            return cli;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar cliente! " 
                    + se.getMessage());
        }finally{
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    //Método para imprimir relatório de clientes
    public void imprimirRelatorio() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        try{
            //Usando a classe JasperPrint para preparar a impressão do relatório
                JasperPrint  print = JasperFillManager.fillReport(
                        "C:/Users/Artom/JaspersoftWorkspace/"
                                + "SistemaOS/Cliente.jasper",null,con);
                
                //A linha abaixo exibi o relátorio através da classe jasper viewer
                JasperViewer.viewReport(print, false);
        }catch(Exception e){
            e.getMessage();
        }finally{
            con.close();
        }//Fecha finally
    }//Fecha método
    
}

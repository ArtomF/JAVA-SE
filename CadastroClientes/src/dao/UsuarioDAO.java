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
import model.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Artom
 */
public class UsuarioDAO {

    //Método responsável por validar o login do usuário
    public boolean logar(Usuario u) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        try {
            String sql = "select * from usuario where login=? and senha=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, u.getLogin());
            pst.setString(2, u.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            throw new SQLException("Erro ao logar no banco!" + se.getMessage());
        }
    }

    //Método responsável por cadastrar os dados na tabela usuario
    public void cadastrarUsuario(Usuario u) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;

        try {
            String sql = "insert into usuario(iduser, nome, email, login,"
                    + " senha, perfil) values(null,?,?,?,?,?)";

            pst = con.prepareStatement(sql);

            pst.setString(1, u.getNome());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getLogin());
            pst.setString(4, u.getSenha());
            pst.setString(5, u.getPerfil());

            pst.execute();

        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no banco! "
                    + se.getMessage());
        } finally {
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método

    //Método que busca os dados da tabela usuario
    public ArrayList<Usuario> buscarUsuario() throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql = "select * from usuario";

            ResultSet rs = stat.executeQuery(sql);

            ArrayList<Usuario> users = new ArrayList<>();

            while (rs.next()) {

                Usuario u = new Usuario();
                u.setIduser(rs.getLong("iduser"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));

                users.add(u);
            }//Fecha while

            return users;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados! " + se.getMessage());
        } finally {
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método

    //Método responsável por alterar os dados da tabela usuario
    public void alterarUsuario(Usuario u) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pst = null;

        try {
            String sql = "update usuario set nome=?, email=?, login=?, "
                    + "senha=?, perfil=? where iduser=?";

            pst = con.prepareStatement(sql);

            pst.setString(1, u.getNome());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getLogin());
            pst.setString(4, u.getSenha());
            pst.setString(5, u.getPerfil());
            pst.setLong(6, u.getIduser());

            pst.executeUpdate();

        } catch (SQLException se) {
            throw new SQLException("Erro ao alterar dados! " + se.getMessage());
        } finally {
            con.close();
            pst.close();
        }//Fecha finally
    }//Fecha método

    public void deletarUsuario(long id) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql = "delete from usuario where iduser= " + id;
            stat.execute(sql);

        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar dados! " + se.getMessage());
        } finally {
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método

    public ArrayList<Usuario> filtrarUsuario(String query) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql = "select * from usuario " + query;
            ResultSet rs = stat.executeQuery(sql);

            ArrayList<Usuario> user = new ArrayList<>();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIduser(rs.getLong("iduser"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));

                user.add(u);
            }

            return user;

        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar dados! " + se.getMessage());
        } finally {
            con.close();
            stat.close();
        }//Fecha finally
    }//Fecha método
    
    //Método para imprimir relatório de usuários
    public void imprimirRelatorio() throws SQLException{
        Connection con = ConexaoBanco.getConexao();
        try{
            //Usando a classe JasperPrint para preparar a impressão do relatório
                JasperPrint  print = JasperFillManager.fillReport(
                        "C:/Users/Artom/JaspersoftWorkspace/"
                                + "SistemaOS/Users.jasper",null,con);
                
                //A linha abaixo exibi o relátorio através da classe jasper viewer
                JasperViewer.viewReport(print, false);
        }catch(Exception e){
            e.getMessage();
        }finally{
            con.close();
        }//Fecha finally
    }//Fecha método

}

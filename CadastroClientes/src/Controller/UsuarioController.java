/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

/**
 *
 * @author Artom
 */
public class UsuarioController {
    
    public boolean logar(Usuario u) throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.logar(u);
    }
    
    public void cadastrarUsuario(Usuario u) throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.cadastrarUsuario(u);
    }
    
    public ArrayList<Usuario> buscarUsuario() throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.buscarUsuario();
    }
    
    public void alterarUsuario(Usuario u) throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.alterarUsuario(u);
    }
    
    public void deletarUsuario(long id) throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.deletarUsuario(id);
    }
    
    public ArrayList<Usuario> filtrarUsuario(String query) throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.filtrarUsuario(query);
    }
    
    public void imprirmirRelatorio() throws SQLException{
        UsuarioDAO uDAO = new UsuarioDAO();
        uDAO.imprimirRelatorio();
    }
}

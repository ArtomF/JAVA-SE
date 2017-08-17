/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Artom
 */
public class ClienteController {
    
    public void cadastrarCliente(Cliente c) throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.cadastrarCliente(c);
    }
    
    public ArrayList<Cliente> buscarClientes() throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        return cDAO.buscarClientes();
    }
    
    public void alterar(Cliente c) throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.alterar(c);
    }
    
    public void deletar(int id) throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.deletar(id);
    }
    
    public ArrayList<Cliente> filtrar(String query) throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        return cDAO.filtrarClientes(query);
    }
    
    public void imprimirRelatorio() throws SQLException{
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.imprimirRelatorio();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.CidadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cidade;

/**
 *
 * @author Artom
 */
public class CidadeController {
    
    public void cadastrar(Cidade c) throws SQLException{
        CidadeDAO cDAO = new CidadeDAO();
        cDAO.cadastrar(c);
    }  
    
    public ArrayList<Cidade> buscarCidades() throws SQLException{
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.buscarCidades();
    }
    
    public ArrayList<Cidade> filtrarCidade(String query) throws SQLException{
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.filtrarCidade(query);
    }
}

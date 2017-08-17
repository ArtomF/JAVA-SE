/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.EstadoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Estado;

/**
 *
 * @author Artom
 */
public class EstadoController {
    
    public void cadastrar(Estado e) throws SQLException{
        EstadoDAO eDAO = new EstadoDAO();
        eDAO.cadastrar(e);
    }  
    
    public ArrayList<Estado> buscarEstados() throws SQLException{
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.buscarEstados();
    }
}

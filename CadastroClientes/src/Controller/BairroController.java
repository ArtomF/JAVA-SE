/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import dao.BairroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bairro;

/**
 *
 * @author Artom
 */
public class BairroController {
    
    public static void cadastrar(Bairro b) throws SQLException{
        BairroDAO bDAO = new BairroDAO();
        bDAO.cadastrar(b);
    }   
    
    public ArrayList<Bairro> buscarBairros() throws SQLException{
        BairroDAO bDAO = new BairroDAO();
        return bDAO.buscarBairros();
    }
    
    public ArrayList<Bairro> filtrarBairros(String query) throws SQLException{
        BairroDAO bDAO = new BairroDAO();
        return bDAO.filtrarBairros(query);
    }
}

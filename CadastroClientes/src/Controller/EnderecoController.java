/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.EnderecoDAO;
import java.sql.SQLException;
import model.Endereco;

/**
 *
 * @author Artom
 */
public class EnderecoController {
    
    public int cadastrarEndereco(Endereco e) throws SQLException{
        EnderecoDAO eDAO = new EnderecoDAO();
        return eDAO.cadastrarEndereco(e);
    }
    
    public void alterarEndereco(Endereco e) throws SQLException{
        EnderecoDAO eDAO = new EnderecoDAO();
        eDAO.alterarEndereco(e);
    }
}

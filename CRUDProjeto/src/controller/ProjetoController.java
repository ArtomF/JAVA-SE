/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Projeto;
import model.ProjetoDAO;

/**
 *
 * @author Artom
 */
public class ProjetoController {
    
    public void cadastrarProjeto(Projeto p) throws SQLException{
        ProjetoDAO pdao = new ProjetoDAO();
        pdao.cadastrarProjeto(p);
    }
    
    public ArrayList<Projeto> buscarProjeto() throws SQLException{
        ProjetoDAO pdao = new ProjetoDAO();
        return pdao.buscarProjeto();    
    }
    
    public void alterarProjeto(Projeto p) throws SQLException{
        ProjetoDAO pdao = new ProjetoDAO();
        pdao.alterarProjeto(p);
    }
    
    public void deletarProjeto(long id) throws SQLException{
        ProjetoDAO pdao = new ProjetoDAO();
        pdao.deletarProjeto(id);
    }
    
    public ArrayList<Projeto> filtrar(String query) throws SQLException{
        ProjetoDAO pdao = new ProjetoDAO();
        return pdao.filtrar(query);
    }
    
}

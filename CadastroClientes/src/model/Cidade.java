/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Artom
 */
public class Cidade {
    
    //Atributos
    private int idCidade;
    private String nome;
    private Estado estado;

    //Método Construtor vazio
    public Cidade() { }

    //Métodos Get e Set
    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    //Método toString
    @Override
    public String toString() {
        return nome + "\n";
    }
    
    
}

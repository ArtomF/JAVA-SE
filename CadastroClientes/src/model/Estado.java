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
public class Estado {
    
    //Atributos
    private int idEstado;
    private String nome;
    private String sigla;

    //Método Construtor vazio
    public Estado() { }

    //Método Contrutor cheio
    public Estado(int idEstado, String nome, String sigla) {
        this.idEstado = idEstado;
        this.nome = nome;
        this.sigla = sigla;
    }

    //Métodos Get e Set
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }   
    
    //Método toString poersonalizado para chamada no combo box
    @Override
    public String toString() {
        return sigla + "\n";
    }
    
}

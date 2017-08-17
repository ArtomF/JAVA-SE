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
public class Bairro {
    
    //Atributos
    private int idBairro;
    private String nome;
    private Cidade cidade;

    //Método Construtor vazio
    public Bairro() {
    }

    //Método Construtor cheio
    public Bairro(int idBairro, String nome, Cidade cidade) {
        this.idBairro = idBairro;
        this.nome = nome;
        this.cidade = cidade;
    }

    //Métodos Get e Set
    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    //Método toString
    @Override
    public String toString() {
        return nome + "\n";
    }
    
    
}

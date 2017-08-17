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
public class Cliente {
    
    //Atributos;
    private int idCli;
    private String nome;
    private String fone;
    private String email;
    private Endereco endereco;

    //Método Construtor vazio
    public Cliente() { }

    //Método Construtor cheio
    public Cliente(int idCli, String nome, String fone, String email, Endereco endereco) {
        this.idCli = idCli;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.endereco = endereco;
    }
    
    //Métodos Get e Set
    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }  

    @Override
    public String toString() {
        return nome + "\n";
    }
    
    
}

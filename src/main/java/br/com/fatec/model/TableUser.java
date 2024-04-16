/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author leona
 */
public class TableUser {
    private final SimpleStringProperty nome;
    private final SimpleStringProperty cpf;
    private final SimpleStringProperty data;
    private final SimpleStringProperty endereco;
    private final SimpleStringProperty uf;
    private final SimpleStringProperty cidade;
    private final SimpleStringProperty complemento;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telefone;

    public TableUser() {
        this.nome = new SimpleStringProperty("");
        this.cpf = new SimpleStringProperty("");
        this.data = new SimpleStringProperty("");
        this.endereco = new SimpleStringProperty(""); 
        this.uf = new SimpleStringProperty("");
        this.cidade = new SimpleStringProperty("");
        this.complemento = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.telefone = new SimpleStringProperty("");
            
    }
    
    

    public TableUser(String nome, String cpf, String data, String endereco, String uf, String cidade, String complemento, String email, String telefone) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.data = new SimpleStringProperty(data);
        this.endereco = new SimpleStringProperty(endereco);
        this.uf = new SimpleStringProperty(uf);
        this.cidade = new SimpleStringProperty(cidade);
        this.complemento = new SimpleStringProperty(complemento);
        this.email = new SimpleStringProperty(email);
        this.telefone = new SimpleStringProperty(telefone);
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }
    
    public SimpleStringProperty cpfProperty() {
        return cpf;
    }
    
    public SimpleStringProperty dataProperty() {
        return data;
    }
    
    public SimpleStringProperty enderecoProperty() {
        return endereco;
    }

    public SimpleStringProperty ufProperty() {
        return uf;
    }

    public SimpleStringProperty cidadeProperty() {
        return cidade;
    }
    public SimpleStringProperty complementoProperty() {
        return complemento;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public String getNome() {
        return nome.get();
    }
    
    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCpf() {
        return cpf.get();
    }
    
    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getData() {
        return data.get();
    }
    
    public void setData(String data) {
        this.data.set(data);
    }

    public String getEndereco() {
        return endereco.get();
    }
    
    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public String getUf() {
        return uf.get();
    }
    
    public void setUf (String uf) {
        this.uf.set(uf);
    }

    public String getCidade() {
        return cidade.get();
    }
    public void setCidade (String cidade) {
        this.cidade.set(cidade);
    }

    public String getComplemento() {
        return complemento.get();
    }
    
    public void setComplemento (String complemento) {
        this.complemento.set(complemento);
    }

    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelefone() {
        return telefone.get();
    }
    
    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leona
 */
public class Autor {
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String cpf;
    private String nacionalidade;

    private List<Autor> listaAutores;

    public Autor(String nome, String sobrenome, String dataNascimento, String cpf, String nacionalidade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.listaAutores = new ArrayList<>();
    }

    // Getters e Setters para cada atributo

    public void adicionarAutor(Autor novoAutor) {
        listaAutores.add(novoAutor);
    }

    public void removerAutor(Autor autor) {
        listaAutores.remove(autor);
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}

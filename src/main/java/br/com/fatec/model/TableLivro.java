/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author leona
 */
public class TableLivro {
    private final SimpleStringProperty titulo;
    private final SimpleStringProperty autor;
    private final SimpleStringProperty anoLancamento;
    private final SimpleStringProperty editora;
    private final SimpleStringProperty genero;
    private final SimpleIntegerProperty idade;

    public TableLivro() {
        this.titulo = new SimpleStringProperty("");
        this.autor = new SimpleStringProperty("");
        this.anoLancamento = new SimpleStringProperty("");
        this.editora = new SimpleStringProperty("");
        this.genero = new SimpleStringProperty("");
        this.idade = new SimpleIntegerProperty(0);
    }

    public TableLivro(String titulo, String autor, String anoLancamento, String editora, String genero, int idade) {
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anoLancamento = new SimpleStringProperty(anoLancamento);
        this.editora = new SimpleStringProperty(editora);
        this.genero = new SimpleStringProperty(genero);
        this.idade = new SimpleIntegerProperty(idade);
    }
    
    
    //retorna as propriedades
    public SimpleStringProperty tituloProperty() {
        return titulo;
    }
    
    public SimpleStringProperty autorProperty() {
        return autor;
    }
    
    public SimpleStringProperty anoLancamentoProperty() {
        return anoLancamento;
    }
    
    public SimpleStringProperty editoraProperty() {
        return editora;
    }

    public SimpleStringProperty generoProperty() {
        return genero;
    }

    public SimpleIntegerProperty idadeProperty() {
        return idade;
    }

    public String getTitulo() {
        return titulo.get();
    }
    
    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getAutor() {
        return autor.get();
    }
    
    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public String getAnoLancamento() {
        return anoLancamento.get();
    }
    
    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento.set(anoLancamento);
    }

    public String getEditora() {
        return editora.get();
    }
    
    public void setEditora(String editora) {
        this.editora.set(editora);
    }

    public String getGenero() {
        return genero.get();
    }
    
    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public Integer getIdade() {
        return idade.get();
    }
    
    public void setIdade(int idade) {
        this.idade.set(idade);
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.util.Objects;

/**
 *
 * @author leona
 */
public class Emprestimo {
    private String emprestimoId;
    private String livroTitulo;
    private String usuarioCpf;
    private String dataEmprestimo;
    private String dataDevolucao;
    private Usuario usuario;
    private Livro livro;

    public Emprestimo() {
    }

    public Emprestimo(String emprestimoId, String livroTitulo, String usuarioCpf, String dataEmprestimo, String dataDevolucao, Usuario usuario, Livro livro) {
        this.emprestimoId = emprestimoId;
        this.livroTitulo = livroTitulo;
        this.usuarioCpf = usuarioCpf;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.usuario = usuario;
        this.livro = livro;
    }
    
    public String getLivroTitulo() {
        return livroTitulo;
    }

    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public void setUsuarioCpf(String usuarioCpf) {
        this.usuarioCpf = usuarioCpf;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(String emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.livroTitulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        return Objects.equals(this.livroTitulo, other.livroTitulo);
    }

    @Override
    public String toString() {
        return getEmprestimoId();
    }
}

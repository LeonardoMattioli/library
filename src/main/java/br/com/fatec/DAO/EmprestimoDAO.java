/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Emprestimo;
import br.com.fatec.model.Livro;
import br.com.fatec.model.Usuario;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author leona
 */
public class EmprestimoDAO implements DAO <Emprestimo> {
    
    private Emprestimo emprestimo;
    private Usuario usuario;
    private Livro livro;
    
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Emprestimo model) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO emprestimo (livro_titulo, usuario_cpf, "
                   + "data_emprestimo, data_devolucao, emprestimo_id) values (?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getLivroTitulo()); //1º interrogação
        pst.setString(2, model.getUsuarioCpf()); //2º interrogacao
        pst.setString(3, model.getDataEmprestimo());
        pst.setString(4, model.getDataDevolucao());
        pst.setString(5, model.getEmprestimoId());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            inseriu = true; //tudo certo com a inserção
        else
            inseriu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Emprestimo model) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        String sql = "DELETE FROM emprestimo WHERE emprestimo_id = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getEmprestimoId()); //1º interrogação
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            removeu = true; //tudo certo com a inserção
        else
            removeu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Emprestimo dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        String sql = "UPDATE emprestimo SET livro_titulo = ?, usuario_cpf = ?, "
                   + "data_emprestimo = ?, data_devolucao = ? WHERE emprestimo_id = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, dado.getLivroTitulo());
        pst.setString(2, dado.getUsuarioCpf());
        pst.setString(3, dado.getDataEmprestimo()); 
        pst.setString(4, dado.getDataDevolucao());
        pst.setString(5, dado.getEmprestimoId());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            alterou = true; //tudo certo com a inserção
        else
            alterou = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return alterou;
    }

    @Override
    public Emprestimo buscaID(Emprestimo dado) throws SQLException {
     
        
        emprestimo = null;
        //Comando SELECT
        String sql = "SELECT * FROM emprestimo WHERE emprestimo_id = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getEmprestimoId());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            
            emprestimo = new Emprestimo();
            
            emprestimo.setEmprestimoId(rs.getString("emprestimo_id"));
            
            usuario = new Usuario();
            usuario.setCpf(rs.getString("usuario_cpf"));
            emprestimo.setUsuario(usuario);
            
            livro = new Livro();
            livro.setTitulo(rs.getString("livro_titulo"));
            emprestimo.setLivro(livro);
            
            emprestimo.setLivroTitulo(rs.getString("livro_titulo"));
            emprestimo.setUsuarioCpf(rs.getString("usuario_cpf"));
            emprestimo.setDataEmprestimo(rs.getString("data_emprestimo"));
            emprestimo.setDataDevolucao(rs.getString("data_devolucao"));
            
        }
        
        Banco.desconectar();
        
        return emprestimo;
    }

    @Override
    public Collection<Emprestimo> lista(String filtro) throws SQLException {
        Collection<Emprestimo> listagem = new ArrayList<>();
        
        emprestimo = null;
        //Comando SELECT
        String sql = "SELECT * FROM emprestimo ";
        //colocar filtro ou nao
        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro

            emprestimo = new Emprestimo();

            emprestimo.setLivroTitulo(rs.getString("livro_titulo"));
            emprestimo.setUsuarioCpf(rs.getString("usuario_cpf"));
            emprestimo.setDataEmprestimo(rs.getString("data_emprestimo"));
            emprestimo.setDataDevolucao(rs.getString("data_devolucao"));
            emprestimo.setEmprestimoId(rs.getString("emprestimo_id"));
            
            //adicionar na coleção
            listagem.add(emprestimo);
        }
        
        Banco.desconectar();
        
        return listagem;
    }

    
}

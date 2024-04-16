/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Livro;
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
public class LivroDAO implements DAO <Livro>{
    private Livro livro;
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    @Override
    public boolean insere(Livro model) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
 
        String sql = "INSERT INTO livro (titulo, autor, "
                   + "ano_lancamento, editora, genero, idade_recomendada) values (?, ?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getTitulo()); //1º interrogação
        pst.setString(2, model.getAutor()); //2º interrogacao
        pst.setString(3, model.getAnoLancamento());
        pst.setString(4, model.getEditora());
        pst.setString(5, model.getGenero());
        pst.setInt(6, model.getIdade()); //2º interrogacao

        
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
    public boolean remove(Livro model) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM livro WHERE titulo = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getTitulo()); //1º interrogação
        
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
    public boolean altera(Livro dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE livro SET autor = ?, ano_lancamento = ?, "
                   + "editora = ?, genero = ?, idade_recomendada = ? WHERE titulo = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, dado.getAutor());
        pst.setString(2, dado.getAnoLancamento());
        pst.setString(3, dado.getEditora()); 
        pst.setString(4, dado.getGenero()); 
        pst.setInt(5, dado.getIdade());
        pst.setString(6, dado.getTitulo()); 
        
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
    public Livro buscaID(Livro dado) throws SQLException {
        //criado para buscar dados do proprietario
        
        livro = null;
        //Comando SELECT
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getTitulo());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            livro = new Livro();
            //move os dados do resultSet para o objeto veiculo
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAnoLancamento(rs.getString("ano_lancamento"));
            livro.setEditora(rs.getString("editora"));
            livro.setGenero(rs.getString("genero"));
            livro.setIdade(rs.getInt("idade_recomendada"));
            
            //precisa buscar os dados do proprietario em proprietarioDAO
        }
        
        Banco.desconectar();
        
        return livro;
    }

    @Override
    public Collection<Livro> lista(String filtro) throws SQLException {
        Collection<Livro> listagem = new ArrayList<>();
        
        livro = null;
        //Comando SELECT
        String sql = "SELECT * FROM livro ";
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
            //cria o objeto veiculo
            livro = new Livro();
            //move os dados do resultSet para o objeto veiculo
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAnoLancamento(rs.getString("ano_lancamento"));
            livro.setEditora(rs.getString("editora"));
            livro.setGenero(rs.getString("genero"));
            livro.setIdade(rs.getInt("idade_recomendada"));
            
            //adicionar na coleção
            listagem.add(livro);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}

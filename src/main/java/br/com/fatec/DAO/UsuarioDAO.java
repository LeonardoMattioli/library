/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

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
public class UsuarioDAO implements DAO <Usuario> {
    
    private Usuario usuario;
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Usuario model) throws SQLException {
        boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO usuario (cpf, nome, "
                   + "data_nascimento, endereco, uf, cidade, complemento, email, telefone) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getCpf()); //1º interrogação
        pst.setString(2, model.getNome()); //2º interrogacao
        pst.setString(3, model.getData());
        pst.setString(4, model.getEndereco());
        pst.setString(5, model.getUf());
        pst.setString(6, model.getCidade());
        pst.setString(7, model.getComplemento());
        pst.setString(8, model.getEmail());
        pst.setString(9, model.getTelefone());

        
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
    public boolean remove(Usuario model) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM usuario WHERE cpf = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getCpf()); //1º interrogação
        
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
    public boolean altera(Usuario dado) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE usuario SET nome = ?, data_nascimento = ?, "
                   + "endereco = ?, uf = ?, cidade = ?, complemento = ?, email = ?, telefone = ? WHERE cpf = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, dado.getNome());
        pst.setString(2, dado.getData());
        pst.setString(3, dado.getEndereco()); 
        pst.setString(4, dado.getUf()); 
        pst.setString(5, dado.getCidade());
        pst.setString(6, dado.getComplemento());
        pst.setString(7, dado.getEmail());
        pst.setString(8, dado.getTelefone());
        pst.setString(9, dado.getCpf()); 
        
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
    public Usuario buscaID(Usuario dado) throws SQLException {
        usuario = null;
        //Comando SELECT
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getCpf());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            usuario = new Usuario();
            //move os dados do resultSet para o objeto veiculo
            usuario.setCpf(rs.getString("cpf"));
            usuario.setNome(rs.getString("nome"));
            usuario.setData(rs.getString("data_nascimento"));
            usuario.setEndereco(rs.getString("endereco"));
            usuario.setUf(rs.getString("uf"));
            usuario.setCidade(rs.getString("cidade"));
            usuario.setComplemento(rs.getString("complemento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefone(rs.getString("telefone"));
            
            //precisa buscar os dados do proprietario em proprietarioDAO
        }
        
        Banco.desconectar();
        
        return usuario;
    }

    @Override
    public Collection<Usuario> lista(String filtro) throws SQLException {
        Collection<Usuario> listagem = new ArrayList<>();
        
        usuario = null;
        //Comando SELECT
        String sql = "SELECT * FROM usuario ";
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
            usuario = new Usuario();
         
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setData(rs.getString("data_nascimento"));
            usuario.setEndereco(rs.getString("endereco"));
            usuario.setUf(rs.getString("uf"));
            usuario.setCidade(rs.getString("cidade"));
            usuario.setComplemento(rs.getString("complemento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefone(rs.getString("telefone"));
            
            //adicionar na coleção
            listagem.add(usuario);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}

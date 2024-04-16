/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.TableLivro;
import br.com.fatec.model.TableUser;
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
public class TableUserDAO implements DAO <TableUser> {

    private TableUser tableUser;
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    @Override
    public boolean insere(TableUser model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(TableUser model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(TableUser model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TableUser buscaID(TableUser model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<TableUser> lista(String filtro) throws SQLException {
        ArrayList<TableUser> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM usuario ";

        //precisa fazer filtro para listagem
        if(filtro != null && filtro.length() > 0) {
            sql += " WHERE " + filtro;
        }
        
        //abre a conexao com o banco
        Banco.conectar();
        
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        while(rs.next()) {
            //criar o objeto
            tableUser = new TableUser();
            
            //mover os dados do resultSet para o objeto proprietário
            tableUser.setCpf(rs.getString("cpf"));
            tableUser.setNome(rs.getString("nome"));
            tableUser.setData(rs.getString("data_nascimento"));
            tableUser.setEndereco(rs.getString("endereco"));
            tableUser.setUf(rs.getString("uf"));
            tableUser.setCidade(rs.getString("cidade"));
            tableUser.setComplemento(rs.getString("complemento"));
            tableUser.setEmail(rs.getString("email"));
            tableUser.setTelefone(rs.getString("telefone"));
            
            
            
            //move o objeto para a coleção
            lista.add(tableUser);
        }
        Banco.desconectar();
        
        return lista;
    }
}

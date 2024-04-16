/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.TableLivro;
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
public class TableLivroDAO implements DAO <TableLivro> {
    
    private TableLivro tableLivro;
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(TableLivro model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(TableLivro model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(TableLivro model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TableLivro buscaID(TableLivro model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<TableLivro> lista(String filtro) throws SQLException {
        ArrayList<TableLivro> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM livro ";

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
            tableLivro = new TableLivro();
            
            //mover os dados do resultSet para o objeto proprietário
            tableLivro.setTitulo(rs.getString("titulo"));
            tableLivro.setAutor(rs.getString("autor"));
            tableLivro.setAnoLancamento(rs.getString("ano_lancamento"));
            tableLivro.setEditora(rs.getString("editora"));
            tableLivro.setGenero(rs.getString("genero"));
            tableLivro.setIdade(rs.getInt("idade_recomendada"));
            
            
            
            //move o objeto para a coleção
            lista.add(tableLivro);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        return lista;
    }
    
}

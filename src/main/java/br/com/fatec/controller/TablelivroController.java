/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.TableLivroDAO;
import br.com.fatec.model.TableLivro;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class TablelivroController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<TableLivro> tb_Livro;
    @FXML
    private TableColumn<TableLivro, String> colTitulo;
    @FXML
    private TableColumn<TableLivro, String> colAutor;
    @FXML
    private TableColumn<TableLivro, String> colAno;
    @FXML
    private TableColumn<TableLivro, String> colEditora;
    @FXML
    private TableColumn<TableLivro, String> colGenero;
    @FXML
    private TableColumn<TableLivro, Integer> colIdade;
    @FXML
    private TextField txtPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //faz a ligação da coluna do tableView com 
        //os atributos da classe Proprietario
        colTitulo.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(
                new PropertyValueFactory<>("autor"));
        colAno.setCellValueFactory(
                new PropertyValueFactory<>("anoLancamento"));
        colEditora.setCellValueFactory(
                new PropertyValueFactory<>("editora"));
        colGenero.setCellValueFactory(
                new PropertyValueFactory<>("genero"));
        colIdade.setCellValueFactory(
                new PropertyValueFactory<>("idade"));
        

        //preenche a tabela
        tb_Livro.setItems(preencheTabela(""));
    }    

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        String textoPesquisa = txtPesquisa.getText();

        tb_Livro.setItems(preencheTabela(textoPesquisa));
    }
    
    private ObservableList<TableLivro> preencheTabela(String filtro) {
       TableLivroDAO dao = new  TableLivroDAO();
       ObservableList<TableLivro> livros
           = FXCollections.observableArrayList();

       try {
           livros.addAll(dao.lista("titulo LIKE '%" + filtro + "%' OR " +
                             "autor LIKE '%" + filtro + "%' OR " +
                             "ano_lancamento LIKE '%" + filtro + "%' OR " +
                             "editora LIKE '%" + filtro + "%' OR " +
                             "genero LIKE '%" + filtro + "%' OR " +
                             "idade_recomendada LIKE '%" + filtro + "%'"));
       } catch (SQLException ex) {
           Alert alerta = new Alert(Alert.AlertType.ERROR,
                   "Erro Preenche Tabela: " + ex.getMessage(),
                   ButtonType.OK);
           alerta.showAndWait();
       }
        
        return livros;
    }
}

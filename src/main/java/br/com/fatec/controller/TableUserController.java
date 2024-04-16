/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.TableLivroDAO;
import br.com.fatec.DAO.TableUserDAO;
import br.com.fatec.model.TableLivro;
import br.com.fatec.model.TableUser;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class TableUserController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableColumn<TableUser, String> colAno;
    @FXML
    private TableView<TableUser> tbUser;
    @FXML
    private TableColumn<TableUser, String> colNome;
    @FXML
    private TableColumn<TableUser, String> colCpf;
    @FXML
    private TableColumn<TableUser, String> colEndereco;
    @FXML
    private TableColumn<TableUser, String> colUf;
    @FXML
    private TableColumn<TableUser, String> colCidade;
    @FXML
    private TableColumn<TableUser, String> colComplemento;
    @FXML
    private TableColumn<TableUser, String> colEmail;
    @FXML
    private TableColumn<TableUser, String> colTelefone;
    @FXML
    private TextField txtPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
        colAno.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        colEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colUf.setCellValueFactory(
                new PropertyValueFactory<>("uf"));
        colCidade.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));
        colComplemento.setCellValueFactory(
                new PropertyValueFactory<>("complemento"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));
  
        //preenche a tabela
        tbUser.setItems(preencheTabela(""));
    }    

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        String textoPesquisa = txtPesquisa.getText();

        tbUser.setItems(preencheTabela(textoPesquisa));
    }
    
    private ObservableList<TableUser> preencheTabela(String filtro) {
       TableUserDAO dao = new  TableUserDAO();
       ObservableList< TableUser> users
           = FXCollections.observableArrayList();

       try {
           users.addAll(dao.lista("cpf LIKE '%" + filtro + "%' OR " +
                             "nome LIKE '%" + filtro + "%' OR " +
                             "data_nascimento LIKE '%" + filtro + "%' OR " +
                             "endereco LIKE '%" + filtro + "%' OR " +
                             "uf LIKE '%" + filtro + "%' OR " +
                             "cidade LIKE '%" + filtro + "%' OR " +
                             "complemento LIKE '%" + filtro + "%' OR " +
                             "email LIKE '%" + filtro + "%' OR " +
                             "telefone LIKE '%" + filtro + "%'"));
       } catch (SQLException ex) {
           Alert alerta = new Alert(Alert.AlertType.ERROR,
                   "Erro Preenche Tabela: " + ex.getMessage(),
                   ButtonType.OK);
           alerta.showAndWait();
       }
        
        return users;
    }
    
}

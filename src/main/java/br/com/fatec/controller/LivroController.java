/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.LivroDAO;
import br.com.fatec.model.Livro;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class LivroController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtanoLancamento;
    @FXML
    private TextField txtEditora;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtIdade;
    @FXML
    private Button btnEmp;
    @FXML
    private Button btmCadLivro;
    @FXML
    private Button btnCadUser;
    @FXML
    private Button btnDados;
    
    private Livro livro;
    
    private LivroDAO livroDAO = new LivroDAO();
    private ObservableList<Livro> livros =  
            FXCollections.observableArrayList();
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCadastro;
    @FXML
    private ComboBox<Livro> cbLivro;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbLivro.setItems(livros);
        preencheComboLivro();
    }
    
    private Livro moveTelaParaModel() {
        livro = new Livro();
        livro.setTitulo(txtTitulo.getText());
        livro.setAutor(txtAutor.getText());
        livro.setAnoLancamento(txtanoLancamento.getText());
        livro.setEditora(txtEditora.getText());
        livro.setGenero(txtGenero.getText());
        livro.setIdade(Integer.parseInt(txtIdade.getText()));
        
        return livro;
    }
    
    private void moveModelParaTela(Livro model) {
        txtTitulo.setText(model.getTitulo());
        txtEditora.setText(model.getEditora());
        txtGenero.setText(model.getGenero());
        txtIdade.setText(String.valueOf(model.getIdade()));
        txtAutor.setText(model.getAutor());
        txtanoLancamento.setText(String.valueOf(model.getAnoLancamento()));
    }

    @FXML
    private void btnCadastro_onclick(ActionEvent event) {
        livro = new Livro();
        livro = moveTelaParaModel();
        livros.add(livro);
        
        try {
            if(livroDAO.insere(livro)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Livro cadastrado com Sucesso !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Livro não foi cadastrado");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        preencheComboLivro();
        limpaCampos();
    }

    @FXML
    private void btnEbtnExcluirClick(ActionEvent event) {
        livro = new Livro();
        livro = moveTelaParaModel();
        try {
            if(livroDAO.remove(livro)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Livro excluido com sucesso !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Erro na exclusão do livro !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        limpaCampos();
        preencheComboLivro();
    }

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        if(cbLivro.getValue() == null){
            mensagem("Favor selecionar um item...");
        }
        else {
            livro = new Livro();
            //obtem os dados da combo
            livro.setTitulo(String.valueOf(cbLivro.getValue()));
            try {
                livro = livroDAO.buscaID(livro);
            } catch (SQLException ex) {
                 System.out.println("Erro: " + ex.getMessage());
            }
            //exibe os dados
            moveModelParaTela(livro);
        }
         /*livro = new Livro();
         livro.setTitulo(txtTitulo.getText());
        try {
            livro = livroDAO.buscaID(livro);
            if(livro == null) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("livro não encontrado !!!");
                aviso.setContentText("");

                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                moveModelParaTela(livro);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            //colocar mensagem em ALERT
        }*/

    }
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) {
        livro = new Livro();
        livro = moveTelaParaModel();
        try {
            if(livroDAO.altera(livro)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Livro Alterado com sucesso!!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Erro ao Alterar o Livro !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        limpaCampos();
    }
    
    private void limpaCampos() {
        txtTitulo.clear();
        txtEditora.clear();
        txtGenero.clear();
        txtIdade.clear();
        txtAutor.clear();
        txtanoLancamento.clear();
    }
    
    private void preencheComboLivro() {
        try {
            livros.setAll(livroDAO.lista(""));
        } catch (Exception e) {
            System.out.println("erro" + e.getMessage());
        }
    }
    
}

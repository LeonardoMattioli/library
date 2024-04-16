/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class MenuController {

    @FXML
    private Pane paneMain;
    @FXML
    private Button btnCadLivro;
    @FXML
    private Button btnEmp;
    @FXML
    private Button btnCadUser;
    @FXML
    private Button btnDados;

    /**
     * Initializes the controller class.
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } */   

    @FXML
    private void btnCadLivroClick(ActionEvent event) throws IOException {
        Cadastro_Chamador cad = new Cadastro_Chamador();
        cad.start(new Stage());
    }

    @FXML
    private void btnCadUserClick(ActionEvent event) throws IOException {
        Usuario_Chamador us = new Usuario_Chamador();
        us.start(new Stage());
    }

    @FXML
    private void btnEmpClick(ActionEvent event) throws IOException {
        Emprestimo_Chamador emp = new Emprestimo_Chamador();
        emp.start(new Stage());
    }

    @FXML
    private void btnDadosClick(ActionEvent event) throws IOException {
        Dados_Chamador dado = new Dados_Chamador();
        dado.start(new Stage());
    }
    
}

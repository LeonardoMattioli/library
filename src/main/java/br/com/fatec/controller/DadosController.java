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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class DadosController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private Button btnCadLivro;
    @FXML
    private Button btnTUsuarios;
    @FXML
    private Button btnAutor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadLivroClick(ActionEvent event) throws IOException {
        TableLivro_Chamador us = new TableLivro_Chamador();
        us.start(new Stage());
    }

    @FXML
    private void btnCadUserClick(ActionEvent event) throws IOException {
        TableUser_Chamador us = new TableUser_Chamador();
        us.start(new Stage());
    }

    @FXML
    private void btnAutorClick(ActionEvent event) throws IOException {
        Autor_Chamador us = new Autor_Chamador();
        us.start(new Stage());
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.LoginDAO;
import br.com.fatec.model.Login;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class LoginController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogin;
    
    private Login login;
    
    private LoginDAO loginDAO = new LoginDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLoginClick(ActionEvent event) throws IOException {
        login = new Login();
         login.setEmail(txtLogin.getText());
         login.setSenha(txtSenha.getText());
        try {
            login = loginDAO.buscaID(login);
            if(login == null) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Email ou senha incorretos !!!");
                aviso.setContentText("");

                aviso.showAndWait(); //exibe a mensagem
                limpaCampos();
            }
            else {
                Menu_Chamador men = new Menu_Chamador();
                men.start(new Stage());
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage

    }
    
    private void limpaCampos() {
        txtLogin.clear();
        txtSenha.clear();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.UsuarioDAO;
import br.com.fatec.model.Livro;
import br.com.fatec.model.Usuario;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leona
 */
public class UsuarioController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private TextField txtCpf;
    
    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtNascimento;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUf;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtComplemento;
    @FXML
    private ComboBox<Usuario> cbUsuario;
    @FXML
    private Button btnPesquisar;
    
    private UsuarioDAO livroDAO = new UsuarioDAO();
    private ObservableList<Usuario> usuarios =  
            FXCollections.observableArrayList();
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCadastrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbUsuario.setItems(usuarios);
        preencheComboUsuario();
    }
    
    private Usuario moveTelaParaModel() {
        usuario = new Usuario ();
        usuario.setNome(txtNome.getText());
        usuario.setCpf(txtCpf.getText());
        usuario.setData(txtNascimento.getText());
        usuario.setEndereco(txtEndereco.getText());
        usuario.setUf(txtUf.getText());
        usuario.setCidade(txtCidade.getText());
        usuario.setComplemento(txtComplemento.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setTelefone(txtTelefone.getText());
        
        return usuario;
    }
    
    private void moveModelParaTela(Usuario model) {
        txtNome.setText(model.getNome());
        txtCpf.setText(model.getCpf());
        txtNascimento.setText(model.getData());
        txtEndereco.setText(model.getEndereco());
        txtUf.setText(model.getUf());
        txtCidade.setText(model.getCidade());
        txtComplemento.setText(model.getComplemento());
        txtEmail.setText(model.getEmail());
        txtTelefone.setText(model.getTelefone());
        
        //chamar rotina para buscar proprietario na combo
    }

    @FXML
    private void btnCadastrarClick(ActionEvent event) {
        usuario = new Usuario();
        usuario = moveTelaParaModel();
        
        if (txtNome.getText() == "" || txtCpf.getText() == "" || txtNascimento.getText() == "" || txtEndereco.getText() == "" || txtUf.getText() == "" || txtCidade.getText() == "" || txtComplemento.getText() == "" || txtEmail.getText() == "" || txtTelefone.getText() == "") {
                mensagem("Favor preencher todos os campos...");
        } else {
            try {
                if(usuarioDAO.insere(usuario)) {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Usuario cadastrado com Sucesso !!!");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem
                }
                else {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Erro no cadastro do Usuario");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem                
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }    
        }
        
        preencheComboUsuario();
        limpaCampos();
    }

    @FXML
    private void btnExcluirClick(ActionEvent event) {
        usuario = new Usuario();
        usuario = moveTelaParaModel();
        if (txtNome.getText() == "" || txtCpf.getText() == "" || txtNascimento.getText() == "" || txtEndereco.getText() == "" || txtUf.getText() == "" || txtCidade.getText() == "" || txtComplemento.getText() == "" || txtEmail.getText() == "" || txtTelefone.getText() == "") {
            mensagem("Favor preencher todos os campos...");
        } else {
            try {
                if(usuarioDAO.remove(usuario)) {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Usuario excluido com sucesso !!!");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem
                }
                else {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Erro ao excluir Usuario !!!");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem                
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        preencheComboUsuario();
        limpaCampos();
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) {
        usuario = new Usuario();
        usuario = moveTelaParaModel();
        if (txtNome.getText() == "" || txtCpf.getText() == "" || txtNascimento.getText() == "" || txtEndereco.getText() == "" || txtUf.getText() == "" || txtCidade.getText() == "" || txtComplemento.getText() == "" || txtEmail.getText() == "" || txtTelefone.getText() == "") {
            mensagem("Favor preencher todos os campos...");
        } else {
            try {
                if(usuarioDAO.altera(usuario)) {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Usuário Alterado com sucesso!!!");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem
                }
                else {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Mensagem");
                    aviso.setHeaderText("Erro ao alterar o Usuário !!!");
                    aviso.setContentText("");
                    aviso.showAndWait(); //exibe a mensagem                
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        limpaCampos();
    }

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        if(cbUsuario.getValue() == null){
            mensagem("Favor selecionar um item...");
        }
        else {
            usuario = new Usuario();
            //obtem os dados da combo
            usuario.setCpf(String.valueOf(cbUsuario.getValue()));
            try {
                usuario = usuarioDAO.buscaID(usuario);
            } catch (SQLException ex) {
                 System.out.println("Erro: " + ex.getMessage());
            }
            //exibe os dados
            moveModelParaTela(usuario);
        }
    }
    
    private void preencheComboUsuario() {
        try {
            usuarios.setAll(usuarioDAO.lista(""));
        } catch (Exception e) {
            System.out.println("erro" + e.getMessage());
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
        txtNome.clear();
        txtCpf.clear();
        txtNascimento.clear();
        txtEndereco.clear();
        txtUf.clear();
        txtCidade.clear();
        txtComplemento.clear();
        txtEmail.clear();
        txtTelefone.clear();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.model.Autor;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
public class AutorController implements Initializable {

    @FXML
    private Pane paneMain;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNacionalidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtData;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnInserir;
    
    private Autor autorPrincipal; // Autor principal que contém a lista de autores
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisa;

    public AutorController() {
        // Inicialize o autor principal
        autorPrincipal = new Autor("Leonardo", "Mattioli", "09/11/2003", "44010502827", "Brasileiro");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnExcluirClick(ActionEvent event) {
        String nomeParaRemover = txtNome.getText(); // Obtém o nome do campo de texto ou de um arquivo

        if (!nomeParaRemover.isEmpty()) {
            boolean autorEncontrado = false;
            List<Autor> listaAutores = autorPrincipal.getListaAutores();

            for (Autor autor : listaAutores) {
                if (autor.getNome().equalsIgnoreCase(nomeParaRemover)) {
                    listaAutores.remove(autor);
                    autorEncontrado = true;
                    break;
                }
            }

            String mensagem = autorEncontrado ? "Autor removido com sucesso!" : "Autor não encontrado!";
            mensagem(mensagem);
            limpaCampos();
        } else {
            mensagem("Por favor, insira um nome para remover.");
        }
    }

    @FXML
    private void btnInserirClick(ActionEvent event) {
        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String dataNascimento = txtData.getText();
        String cpf = txtCpf.getText();
        String nacionalidade = txtNacionalidade.getText();

        Autor novoAutor = new Autor(nome, sobrenome, dataNascimento, cpf, nacionalidade);
        autorPrincipal.adicionarAutor(novoAutor);
        
        
        mensagem("Autor Inserido com sucesso");
        
        limpaCampos();
    }

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        String nomeParaPesquisar = txtNome.getText(); // Obtém o nome do campo de texto

        if (!nomeParaPesquisar.isEmpty()) {
            Autor autorEncontrado = null;
            List<Autor> listaAutores = autorPrincipal.getListaAutores();

            for (Autor autor : listaAutores) {
                if (autor.getNome().equalsIgnoreCase(nomeParaPesquisar)) {
                    autorEncontrado = autor;
                    break;
                }
            }

            if (autorEncontrado != null) {
                preencherCamposComAutor(autorEncontrado);
            } else {
                limpaCampos();
                mensagem("Autor não encontrado!");
            }
        } else {
            limpaCampos();
            mensagem("Por favor, insira um nome para pesquisar.");
        }
    }
    
     private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage
    }
     
     private void preencherCamposComAutor(Autor autor) {
        txtSobrenome.setText(autor.getSobrenome());
        txtData.setText(autor.getDataNascimento());
        txtCpf.setText(autor.getCpf());
        txtNacionalidade.setText(autor.getNacionalidade());
    }
     
     private void limpaCampos() {
        txtNome.clear();
        txtNacionalidade.clear();
        txtSobrenome.clear();
        txtData.clear();
        txtCpf.clear();
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) {
        String nomeParaPesquisar = txtNome.getText(); // Obtém o nome do campo de texto

        if (!nomeParaPesquisar.isEmpty()) {
            Autor autorEncontrado = null;
            List<Autor> listaAutores = autorPrincipal.getListaAutores();

            for (Autor autor : listaAutores) {
                if (autor.getNome().equalsIgnoreCase(nomeParaPesquisar)) {
                    autorEncontrado = autor;
                    break;
                }
            }

            if (autorEncontrado != null) {
                atualizarInformacoesDoAutor(autorEncontrado);
                mensagem( "Autor atualizado com sucesso!");
                limpaCampos();
            } else {
                mensagem("Autor não encontrado!");
            }
        } else {
            mensagem("Por favor, insira um nome para atualizar.");
        }
    }
    
    private void atualizarInformacoesDoAutor(Autor autor) {
        autor.setSobrenome(txtSobrenome.getText());
        autor.setDataNascimento(txtData.getText());
        autor.setCpf(txtCpf.getText());
        autor.setNacionalidade(txtNacionalidade.getText());
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.EmprestimoDAO;
import br.com.fatec.DAO.LivroDAO;
import br.com.fatec.DAO.UsuarioDAO;
import br.com.fatec.model.Emprestimo;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author leona
 */
public class EmprestimoController implements Initializable {


    @FXML
    private Pane paneMain;
    @FXML
    private TextField txtData1;
    @FXML
    private TextField txtData2;
    @FXML
    private Button btnEmprestar;
    @FXML
    private ComboBox<Livro> cbLivro;
    @FXML
    private ComboBox<Usuario> cbCpf;
    
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    
    private LivroDAO livroDAO = new LivroDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    private Emprestimo emprestimo;
    
    public ObservableList<Livro> livros =  
            FXCollections.observableArrayList();
    
    private Usuario usuario;
    
    public ObservableList<Usuario> usuarios =  
            FXCollections.observableArrayList();
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    
    public ObservableList<Emprestimo> emprestimos =  
            FXCollections.observableArrayList();
    @FXML
    private TextField txtEmprestimo;
    @FXML
    private TextField txtPesquisa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbLivro.setItems(livros);
        cbCpf.setItems(usuarios);
        
        
        preencheComboLivro();
        preencheComboUsuario();
        preencheComboPesquisar();
    }
    
    private Emprestimo moveTelaParaModel() {
        Object cpfValor = cbCpf.getValue();
        String valorCpf =  cpfValor.toString();
        
        Object livroCb = cbLivro.getValue();
        String valorLivro =  livroCb.toString();
        
        emprestimo = new Emprestimo();
        emprestimo.setEmprestimoId(txtEmprestimo.getText());
        emprestimo.setLivroTitulo(valorLivro);
        emprestimo.setUsuarioCpf(valorCpf);
        emprestimo.setDataEmprestimo(txtData1.getText());
        emprestimo.setDataDevolucao(txtData2.getText());
        
        usuario = new Usuario();
        usuario.setCpf(cbCpf.getSelectionModel().getSelectedItem().getCpf());
        System.out.println(usuario.getCpf());
                
        
        try {
            usuario = usuarioDAO.buscaID(usuario);
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        
        emprestimo.setUsuario(usuario);                
        
        return emprestimo;
    }
    
    private void moveModelParaTela(Emprestimo model) {
        cbCpf.setValue(model.getUsuario());
        cbLivro.setValue(model.getLivro());
        txtData1.setText(model.getDataEmprestimo());
        txtData2.setText(model.getDataDevolucao());
        txtEmprestimo.setText(model.getEmprestimoId());   
    }
    
    private void preencheComboLivro() {
        try {
            livros.setAll(livroDAO.lista(""));
        } catch (Exception e) {
            System.out.println("erro" + e.getMessage());
        }
    }
    
    private void preencheComboUsuario() {
        try {
            usuarios.setAll(usuarioDAO.lista(""));
        } catch (Exception e) {
            System.out.println("erro" + e.getMessage());
        }
    }
    

    @FXML
    private void btnEmprestarClick(ActionEvent event) {
        emprestimo = new Emprestimo();
        emprestimo = moveTelaParaModel();
        try {
            if(emprestimoDAO.insere(emprestimo)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Registro de Emprestimo cadastrado com sucesso !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Registro de Emprestimo não cadastrado");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        preencheComboPesquisar();
        limpaCampos();
    }

    @FXML
    private void btnExcluirClick(ActionEvent event) {
        emprestimo = new Emprestimo();
        emprestimo = moveTelaParaModel();
        try {
            if(emprestimoDAO.remove(emprestimo)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Registro de Emprestimo excluido com sucesso !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Erro ao excluir registro de Emprestimo !!!");
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
    private void btnAlterarClick(ActionEvent event) {
        emprestimo = new Emprestimo();
        emprestimo = moveTelaParaModel();
        try {
            if(emprestimoDAO.altera(emprestimo)) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Emprestimo Alterado com sucesso !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem
            }
            else {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Mensagem");
                aviso.setHeaderText("Erro no Emprestimo !!!");
                aviso.setContentText("");
                aviso.showAndWait(); //exibe a mensagem                
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        limpaCampos();
    }

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
        if(txtPesquisa.getText() == null){
            mensagem("Favor selecionar um item...");
        }
        else {
            emprestimo = new Emprestimo();
            emprestimo.setEmprestimoId((txtPesquisa.getText()));
            try {
                emprestimo = emprestimoDAO.buscaID(emprestimo);
                if (emprestimo == null) {
                    limpaCampos();
                    mensagem("Registro de empréstimo não encontrado!");
                }    
            } catch (SQLException ex) {
                System.out.println("erro: " + ex.getMessage());
            }
            //exibe os dados
            moveModelParaTela(emprestimo);
        }
    }
    
    
    private void preencheComboPesquisar() {
        try {
            emprestimos.setAll(emprestimoDAO.lista(""));
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
        cbCpf.setValue(null);
        cbLivro.setValue(null);
        txtData1.clear();
        txtData2.clear();
        txtEmprestimo.clear();
    }
}

package com.example.atvmenu1.controller;

import com.example.atvmenu1.dao.ClienteDAO;
import com.example.atvmenu1.dao.ProdutoDAO;
import com.example.atvmenu1.model.Cliente;
import com.example.atvmenu1.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientesController {
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtEndereco;

    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colCpf;
    @FXML private TableColumn<Cliente, String> colTelefone;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colEndereco;

    private ClienteDAO dao = new ClienteDAO();

    private Cliente clienteSelecionado;

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        atualizarTabela();
    }

    public void atualizarTabela(){
        try{
            tabelaClientes.setItems(FXCollections.observableArrayList(dao.listarClientes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void salvarCliente(){
        try{
            if(clienteSelecionado == null){
                Cliente novo = new Cliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtEmail.getText(),
                        txtEndereco.getText()

                );
                dao.salvarCliente(novo);
            } else{
                clienteSelecionado.setNome(txtNome.getText());
                clienteSelecionado.setCpf(txtCpf.getText());
                clienteSelecionado.setTelefone(txtTelefone.getText());
                clienteSelecionado.setEmail(txtEmail.getText());
                clienteSelecionado.setEndereco(txtEndereco.getText());
                dao.atualizarClientes(clienteSelecionado);
            }
            atualizarTabela();
            limparCampos();
        } catch (Exception e) {
            exibirAlerta("Erro", e.getMessage());
        }
    }


    @FXML
    public void excluirCliente(){
        if(clienteSelecionado != null){
            try{
                dao.deletarcCliente(clienteSelecionado.getId());
                atualizarTabela();
                limparCampos();
            }catch (Exception e){exibirAlerta("erro", e.getMessage());}
        }
    }

    @FXML
    public void selecionarCliente(){
        clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if(clienteSelecionado != null){
            txtNome.setText(clienteSelecionado.getNome());
            txtCpf.setText(clienteSelecionado.getCpf());
            txtTelefone.setText(clienteSelecionado.getTelefone());
            txtEmail.setText(clienteSelecionado.getEmail());
            txtEndereco.setText(clienteSelecionado.getEndereco());
        }
    }

    @FXML
    public void  limparCampos(){
        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtEndereco.clear();
        clienteSelecionado = null;
        tabelaClientes.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}

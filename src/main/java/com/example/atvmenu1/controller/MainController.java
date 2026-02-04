package com.example.atvmenu1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    public void abrirHome(){
        carregarTela("Home.fxml");
    }

    @FXML
    public void abrirProduto(){
        carregarTela("Produtos.fxml");
    }

    @FXML
    public void abrirCliente(){
        carregarTela("Clientes.fxml");
    }

    @FXML
    public void abrirNovaVenda(){
        carregarTela("NovaVenda.fxml");
    }

    @FXML
    public void abrirRelatorio(){
        carregarTela("Relatorio.fxml");
    }

    @FXML
    public void initialize(){
        carregarTela("Home.fxml");
    }



    private void carregarTela(String fxml){
        try{
            root.setCenter(
                    FXMLLoader.load(getClass().getResource("/fxml/" + fxml))
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void abrirAjuda (ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre o sistema");
        alert.setHeaderText("Informações do Sistema");
        alert.setContentText(
                "Nome Sistema de Vendas\n" +
                        "versão 1.0\n" +
                        "Desenvolvedor: Arthur Rodrigues Silva\n" +
                        "Ano: 2026"
        );
        alert.showAndWait();
    }

}

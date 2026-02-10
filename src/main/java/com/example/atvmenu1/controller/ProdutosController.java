package com.example.atvmenu1.controller;

import com.example.atvmenu1.dao.ProdutoDAO;
import com.example.atvmenu1.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutosController {
    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TextField txtQtnd;

    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, Integer> colID;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;
    @FXML private TableColumn<Produto, Integer> colQtnd;

    private ProdutoDAO dao = new ProdutoDAO();

    private Produto produtoSelecionado;

    @FXML
    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQtnd.setCellValueFactory(new PropertyValueFactory<>("qtnd"));
        atualizarTabela();
    }

    public void atualizarTabela(){
        try{
            tabelaProdutos.setItems(FXCollections.observableArrayList(dao.listarTodos()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void salvarProduto(){
        try{
            if(produtoSelecionado == null){
                Produto novo = new Produto(
                        txtNome.getText(),
                        Double.parseDouble(txtPreco.getText()),
                        Integer.parseInt(txtQtnd.getText())
                );
                dao.salvar(novo);
            } else{
                produtoSelecionado.setNome(txtNome.getText());
                produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                produtoSelecionado.setQtnd(Integer.parseInt(txtQtnd.getText()));
                dao.atualizar(produtoSelecionado);
            }
            atualizarTabela();
            limparCampos();
        } catch (Exception e) {
            exibirAlerta("Erro", e.getMessage());
        }
    }


    @FXML
        public void excluirProduto(){
            if(produtoSelecionado != null){
                try{
                    dao.deletar(produtoSelecionado.getId());
                    atualizarTabela();
                    limparCampos();
                }catch (Exception e){exibirAlerta("erro", e.getMessage());}
            }
        }

        @FXML
        public void selecionarItem(){
            produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
            if(produtoSelecionado != null){
                txtNome.setText(produtoSelecionado.getNome());
                txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
                txtQtnd.setText(String.valueOf(produtoSelecionado.getQtnd()));
            }
        }

        @FXML
        public void  limparCampos(){
            txtNome.clear();
            txtPreco.clear();
            txtQtnd.clear();
            produtoSelecionado = null;
            tabelaProdutos.getSelectionModel().clearSelection();
        }

        private void exibirAlerta(String titulo, String msg){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setContentText(msg);
            alert.show();
        }
}

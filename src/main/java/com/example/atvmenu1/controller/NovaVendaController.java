package com.example.atvmenu1.controller;

import com.example.atvmenu1.dao.ClienteDAO;
import com.example.atvmenu1.dao.NovaVendaDAO;
import com.example.atvmenu1.dao.ProdutoDAO;
import com.example.atvmenu1.model.Cliente;
import com.example.atvmenu1.model.NovaVenda;
import com.example.atvmenu1.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

public class NovaVendaController {

    public ComboBox cbCliente;
    @FXML private ComboBox<Cliente> cbNome;
    @FXML private ComboBox<Produto> cbProduto;
    @FXML private Spinner<Integer> spQtnd;

    @FXML private TableView<NovaVenda> tabelaVendas;
    @FXML private TableColumn<NovaVenda, Integer> colId;
    @FXML private TableColumn<NovaVenda, String> colNome;
    @FXML private TableColumn<NovaVenda, String> colProduto;
    @FXML private TableColumn<NovaVenda, Integer> colQtnd;
    @FXML private TableColumn<NovaVenda, Double> colValor;
    @FXML private TableColumn<NovaVenda, Double> colTotal;

    private NovaVendaDAO vendaDAO = new NovaVendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    public void initialize() {

        SpinnerValueFactory<Integer> factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        spQtnd.setValueFactory(factory);

        cbNome.setItems(FXCollections.observableArrayList(clienteDAO.listarClientes()));
        cbProduto.setItems(FXCollections.observableArrayList(produtoDAO.listarTodos()));

        cbProduto.setOnAction(e -> {
            Produto produto = cbProduto.getValue();
            if (produto != null) {
                SpinnerValueFactory<Integer> novaFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(
                                1,
                                produto.getQtnd(),
                                1
                        );
                spQtnd.setValueFactory(novaFactory);
            }
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQtnd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        atualizarTabela();
    }


    @FXML
    public void atualizarTabela() {
        tabelaVendas.setItems(FXCollections.observableArrayList(vendaDAO.listarVendas()));
    }

    @FXML
    public void finalizarVenda() {

        Cliente cliente = cbNome.getValue();
        Produto produto = cbProduto.getValue();

        if (cliente == null || produto == null) {
            mostrarErro("Selecione cliente e produto!");
            return;
        }

        int quantidade = spQtnd.getValue();

        if (quantidade > produto.getQtnd()) {
            mostrarErro("Estoque insuficiente!");
            return;
        }

        vendaDAO.realizarVenda(cliente.getId(), produto, quantidade);

        atualizarTabela();
        limparVendas();
    }

    @FXML
    public void limparVendas() {
        cbNome.getSelectionModel().clearSelection();
        cbProduto.getSelectionModel().clearSelection();
        spQtnd.getValueFactory().setValue(1);
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }

    @FXML
    public void selecionarItem() {
        NovaVenda item = tabelaVendas.getSelectionModel().getSelectedItem();

        if (item != null) {

            cbNome.getSelectionModel().select(
                    clienteDAO.buscarPorNome(item.getNome())
            );

            cbProduto.getSelectionModel().select(
                    produtoDAO.buscarPorNome(item.getProduto())
            );

            spQtnd.getValueFactory().setValue(item.getQtnd());
        }
    }


}

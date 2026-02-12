package com.example.atvmenu1.dao;

import com.example.atvmenu1.model.NovaVenda;
import com.example.atvmenu1.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NovaVendaDAO {

    public List<NovaVenda> listarVendas() {
        List<NovaVenda> vendas = new ArrayList<>();
        String sql = "SELECT v.id, c.nome AS nome, p.nome AS produto, v.qtnd, v.valor_unitario AS valor, v.total " +
                "FROM vendas v " +
                "JOIN clientes c ON v.id_cliente = c.id " +
                "JOIN produtos p ON v.id_produto = p.id";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                NovaVenda v = new NovaVenda(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("produto"),
                        rs.getInt("qtnd"),
                        rs.getDouble("valor"),
                        rs.getDouble("total")
                );
                vendas.add(v);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas: " + e.getMessage());
        }

        return vendas;
    }



    public void deletar(int id) {
        String sql = "DELETE FROM vendas WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    public void realizarVenda(int fkId_cliente, Produto produto, int qtnd) {

        String sqlInserirVenda = "INSERT INTO vendas (id_cliente, id_produto, qtnd, valor_unitario, total) VALUES (?, ?, ?, ?, ?)";
        String sqlAtualizarEstoque = "UPDATE produtos SET qtnd = qtnd - ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            double total = qtnd * produto.getPreco();

            PreparedStatement stmtVenda = conn.prepareStatement(sqlInserirVenda);
            stmtVenda.setInt(1, fkId_cliente);
            stmtVenda.setInt(2, produto.getId());
            stmtVenda.setInt(3, qtnd);
            stmtVenda.setDouble(4, produto.getPreco());
            stmtVenda.setDouble(5, total);
            stmtVenda.executeUpdate();

            PreparedStatement stmtEstoque = conn.prepareStatement(sqlAtualizarEstoque);
            stmtEstoque.setInt(1, qtnd);
            stmtEstoque.setInt(2, produto.getId());
            stmtEstoque.executeUpdate();
            produto.setQtnd(produto.getQtnd() - qtnd);


            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao finalizar venda: " + e.getMessage());
        }
    }

}

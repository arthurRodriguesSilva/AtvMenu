package com.example.atvmenu1.model;

import com.google.protobuf.ValueOrBuilder;
import com.sun.jdi.DoubleType;

public class NovaVenda {


    private int id;
    private String nome;
    private String produto;
    private int qtnd;
    private Double valor;
    private Double total;

    public NovaVenda(String nome, String produto, int qtnd, Double valor, Double total){
        this.nome = nome;
        this.produto = produto;
        this.qtnd = qtnd;
        this.valor = valor;
        this.total = total;
    }

    public NovaVenda(int id, String nome, String produto, int qtnd, Double valor, Double total){
        this.id = id;
        this.nome = nome;
        this.produto = produto;
        this.qtnd = qtnd;
        this.valor = valor;
        this.total = total;
    }
    public NovaVenda() {}

    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getProduto() {return produto;}
    public int getQtnd() {return qtnd;}
    public Double getValor() {return valor;}
    public Double getTotal() {return total;}

    public void setId(int id) {this. id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setProduto(String produto) {this.produto = produto;}
    public void setQtnd(int qtnd) {this.qtnd = qtnd;}
    public void setValor(Double valor) {this.valor = valor;}
    public void setTotal(Double total) {this.total = total;}
}


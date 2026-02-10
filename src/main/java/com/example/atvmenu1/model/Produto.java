package com.example.atvmenu1.model;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int qtnd;

    public Produto(String nome, double preco, int qtnd){
        this.nome = nome;
        this.preco = preco;
        this.qtnd = qtnd;
    }

    public Produto (int id, String nome, double preco, int qtnd) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtnd = qtnd;
    }

    public Produto() {}

    public int getId() {return id;}
    public String getNome() {return nome;}
    public double getPreco() {return preco;}
    public int getQtnd() {return qtnd;}

    public void setId(int id) {this. id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setPreco(double preco) {this.preco = preco;}
    public void setQtnd(int qtnd) {this.qtnd = qtnd;}
}

package br.edu.ifg.luziania.model.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.math.BigDecimal;

import java.util.Date;


public class DadosProdutoDTO {
    private Integer id;
    private String modelo;
    private String numeroDeSerie;
    private String dataDeAquisicao;
    private BigDecimal precoDeCompra;
    private Integer quantidadeDisponivel;
    private Integer fornecedorId;
    private Integer produtosId;


    // Construtores, getters e setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(String dataDeAquisicao) {
        this.dataDeAquisicao = dataDeAquisicao;
    }

    public BigDecimal getPrecoDeCompra() {
        return precoDeCompra;
    }

    public void setPrecoDeCompra(BigDecimal precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Integer getProdutosId() {
        return produtosId;
    }

    public void setProdutosId(Integer produtosId) {
        this.produtosId = produtosId;
    }
}
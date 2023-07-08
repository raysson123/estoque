package br.edu.ifg.luziania.model.dto;

public class Dados_ProdutoDTO {
    private Integer id;
    private String modelo;
    private String numeroDeSerie;
    private String dataDeAquisicao;
    private String precoDeCompra;
    private String quantidadeDisponivel;
    private Integer fornecedorId;

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

    public String getPrecoDeCompra() {
        return precoDeCompra;
    }

    public void setPrecoDeCompra(String precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }

    public String getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(String quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}

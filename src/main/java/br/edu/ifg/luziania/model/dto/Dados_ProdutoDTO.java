package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Fornecedor;

import java.math.BigDecimal;
import java.util.Date;

public class Dados_ProdutoDTO {
    private Integer id;
    private String modelo;
    private String numeroDeSerie;
    private Date dataDeAquisicao;
    private BigDecimal precoDeCompra;
    private Integer quantidadeDisponivel;
    private Fornecedor fornecedorDTO;
    private String produtosNome;
    private String produtosCartegoria;

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

    public Date getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(Date dataDeAquisicao) {
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

    public Fornecedor getFornecedorDTO() {
        return fornecedorDTO;
    }

    public void setFornecedorDTO(Fornecedor fornecedorDTO) {
        this.fornecedorDTO = fornecedorDTO;
    }

    public String getProdutosNome() {
        return produtosNome;
    }

    public void setProdutosNome(String produtosNome) {
        this.produtosNome = produtosNome;
    }

    public String getProdutosCartegoria() {
        return produtosCartegoria;
    }

    public void setProdutosCartegoria(String produtosCartegoria) {
        this.produtosCartegoria = produtosCartegoria;
    }
}

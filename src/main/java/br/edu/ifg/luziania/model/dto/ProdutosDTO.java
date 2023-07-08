package br.edu.ifg.luziania.model.dto;

public class ProdutosDTO {

        private Integer id;
        private String nomeProdutos;
        private String descricao;
        private String nomeCategoria;
        private boolean posueEstoque;
    public ProdutosDTO(Integer id, String nomeProdutos, String descricao, String nomeCategoria, boolean possuiEstoque) {
        this.id = id;
        this.nomeProdutos = nomeProdutos;
        this.descricao = descricao;
        this.nomeCategoria = nomeCategoria;
        this.posueEstoque = possuiEstoque;
    }
    public ProdutosDTO() {
        // Default constructor
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProdutos() {
        return nomeProdutos;
    }

    public void setNomeProdutos(String nomeProdutos) {
        this.nomeProdutos = nomeProdutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public boolean isPosueEstoque() {
        return posueEstoque;
    }

    public void setPosueEstoque(boolean posueEstoque) {
        this.posueEstoque = posueEstoque;
    }
}

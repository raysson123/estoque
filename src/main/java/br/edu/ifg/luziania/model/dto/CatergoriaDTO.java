package br.edu.ifg.luziania.model.dto;

public class CatergoriaDTO
{
    private Integer id;
    private String nomeCategoria;
    private boolean possuiProduto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public boolean isPossuiProduto() {
        return possuiProduto;
    }

    public void setPossuiProduto(boolean possuiProduto) {
        this.possuiProduto = possuiProduto;
    }
}

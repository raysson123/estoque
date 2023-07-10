package br.edu.ifg.luziania.model.dto;


import br.edu.ifg.luziania.model.entity.Usuario;

import java.util.Date;

public class UsarioRetiraProdutoDTO {
    private Integer id;

    private Integer dadosProdutoID;
    private Usuario usuario;
    private String descricao;

    private Integer quatida;
    private String dataDeRetirada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDadosProdutoID() {
        return dadosProdutoID;
    }

    public void setDadosProdutoID(Integer dadosProdutoID) {
        this.dadosProdutoID = dadosProdutoID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuatida() {
        return quatida;
    }

    public void setQuatida(Integer quatida) {
        this.quatida = quatida;
    }

    public String getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(String dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }
}

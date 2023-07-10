package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.DadosProduto;
import br.edu.ifg.luziania.model.entity.Usuario;

import java.util.Date;

public class UsarioRetiraProdutoDTO {
    private Integer id;

    private DadosProduto dadosProduto;
    private String descricao;

    private Usuario usuario;
    private Integer quatida;
    private Date dataDeRetirada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DadosProduto getDadosProduto() {
        return dadosProduto;
    }

    public void setDadosProduto(DadosProduto dadosProduto) {
        this.dadosProduto = dadosProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getQuatida() {
        return quatida;
    }

    public void setQuatida(Integer quatida) {
        this.quatida = quatida;
    }

    public Date getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(Date dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }
}

package br.edu.ifg.luziania.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeProdutos;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "produtos")
    private List<DadosProduto> dadosProduto;

    public String getNomeProdutos() {
        return nomeProdutos;
    }

    public void setNomeProdutos(String nomeProdutos) {
        this.nomeProdutos = nomeProdutos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<DadosProduto> getDadosProduto() {
        return dadosProduto;
    }

    public void setDadosProduto(List<DadosProduto> dadosProduto) {
        this.dadosProduto = dadosProduto;
    }
}

package br.edu.ifg.luziania.model.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "UsarioRetiraProdutos")
public class UsarioRetiraProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "dadosProduto_id")
    private DadosProduto dadosProduto;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Integer quatida;
    @Column(name = "Data_de_Retirada")
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

    public void setDescricao(String descrição) {
        this.descricao = descrição;
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
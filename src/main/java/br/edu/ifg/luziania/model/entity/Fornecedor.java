package br.edu.ifg.luziania.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeFornecedor;

    private String nomeDeContato;

    private String numeroDeTelefone;

    private String numeroCNPJ;

    private String numeroEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeDeContato() {
        return nomeDeContato;
    }

    public void setNomeDeContato(String nomeDeContato) {
        this.nomeDeContato = nomeDeContato;
    }

    public String getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public void setNumeroDeTelefone(String numeroDeTelefone) {
        this.numeroDeTelefone = numeroDeTelefone;
    }

    public String getNumeroCNPJ() {
        return numeroCNPJ;
    }

    public void setNumeroCNPJ(String numeroCNPJ) {
        this.numeroCNPJ = numeroCNPJ;
    }

    public String getNumeroEmail() {
        return numeroEmail;
    }

    public void setNumeroEmail(String numeroEmail) {
        this.numeroEmail = numeroEmail;
    }
}

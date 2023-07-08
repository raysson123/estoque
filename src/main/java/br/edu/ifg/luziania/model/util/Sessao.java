package br.edu.ifg.luziania.model.util;


import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class Sessao implements Serializable {

    private String nomeUsuario;
    private Integer id;
    private TipoUsuario tipoUsuario;


    // Getters e setters para os atributos...

    public boolean isSessaoInstanciada() {
        return nomeUsuario != null || id != null || tipoUsuario != null;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}


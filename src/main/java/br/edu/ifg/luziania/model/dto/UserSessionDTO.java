package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.util.TipoUsuario;

public class UserSessionDTO {
    private String nomeUsuario;
    private Integer id;
    private TipoUsuario tipoUsuario;

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

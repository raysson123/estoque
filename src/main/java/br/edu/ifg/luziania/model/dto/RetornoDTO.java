package br.edu.ifg.luziania.model.dto;

public class RetornoDTO {
    private String mensagem;

    public RetornoDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
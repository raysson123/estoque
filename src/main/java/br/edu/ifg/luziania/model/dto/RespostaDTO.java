package br.edu.ifg.luziania.model.dto;

public class RespostaDTO {

    private Integer status;
    private String mensagem;
    private String url;

    public RespostaDTO(){

    }
    public RespostaDTO(Integer status, String mensagem, String url) {
        this.status = status;
        this.mensagem = mensagem;
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


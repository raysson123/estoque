package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.DadosProdutoBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.DadosProdutoDTO;
import br.edu.ifg.luziania.model.dto.FornecedorDTO;
import br.edu.ifg.luziania.model.dto.UsarioRetiraProdutoDTO;
import br.edu.ifg.luziania.model.util.Templetes;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/dadosprodutos")
public class dadosProdutos {

    @Inject
    UsuarioBO usuarioBO;
    @Inject
    DadosProdutoBO dadosProdutoBO;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response dadosprodutos() {
        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("10")))
                .build();
    }
    @GET
    @Path("/cadatralist")
    @Produces(MediaType.TEXT_HTML)
    public Response cadatralist() {
        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("5")))
                .build();
    }
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarcategoria() {
        return dadosProdutoBO.dadosDadosPLista();
    }

    @POST
    @Path("/cadatralist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savar(DadosProdutoDTO[] dadosProdutoDTO) {

        return dadosProdutoBO.cadastradadosProdutos(dadosProdutoDTO);
    }  @POST
    @Path("/retirar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savar(UsarioRetiraProdutoDTO[] dadosProdutoDTO) {

        return dadosProdutoBO.retiradadeProdutos(dadosProdutoDTO);
    }
}

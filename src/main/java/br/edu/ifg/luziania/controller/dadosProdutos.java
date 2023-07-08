package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.DadosProdutoBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.DadosProdutoDTO;
import br.edu.ifg.luziania.model.dto.FornecedorDTO;
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
    @Path("/cadatralist")
    @Produces(MediaType.TEXT_HTML)
    public Response cadatralist() {
        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("5")))
                .build();
    }

    @POST
    @Path("/cadatralist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savar(DadosProdutoDTO[] dadosProdutoDTO) {

        return dadosProdutoBO.cadastradadosProdutos(dadosProdutoDTO);
    }
}

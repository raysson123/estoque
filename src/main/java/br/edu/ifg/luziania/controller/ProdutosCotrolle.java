package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ProdutosBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dao.ProdutosDAO;
import br.edu.ifg.luziania.model.dto.ProdutosDTO;
import br.edu.ifg.luziania.model.util.Templetes;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
public class ProdutosCotrolle {
    @Inject
    ProdutosBO produtosBO;
    @Inject
    UsuarioBO usuarioBO;
    @Inject
    ProdutosDAO produtosDAO;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")

    public Response carregapaginaProdutos() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("3")))
                .build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPodtos() {
        return produtosDAO.obterProdutosComEstoque();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastros")
    public Response savar(ProdutosDTO produtosDTO) {
        return produtosBO.cadastrarProduto(produtosDTO);
    }

    @PUT
    @Path("/atulizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") Integer id, ProdutosDTO produtosDTO) {
        return produtosBO.atualizarProduto(id, produtosDTO);
    }

    @DELETE
    @Path("/excluir/{id}")
    public Response excluirProduto(@PathParam("id") Integer id) {
        return produtosBO.deletarProduto(id);

    }

}

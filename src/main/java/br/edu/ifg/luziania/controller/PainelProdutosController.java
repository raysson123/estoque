package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ProdutosBO;
import br.edu.ifg.luziania.model.dao.DadosProdutoDAO;
import br.edu.ifg.luziania.model.dao.ProdutosDAO;
import br.edu.ifg.luziania.model.entity.Produtos;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/produtos1")
public class PainelProdutosController {

    @Inject
    Template painelCadatroDados;

    @Inject
    ProdutosBO produtosBO;

    @Inject
    ProdutosDAO produtosDAO;
    @Inject
    DadosProdutoDAO dadosProdutoDAO;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/cadastrodata")
    public TemplateInstance cadastrardadosProduto() {

        return painelCadatroDados.instance();
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/cadastrosdadopro")
//    public Response savarFornecedor(DadosProdutoDTO dadosProdutoDTO) {
//
//        return produtosBO.cadatradadosDadosP(dadosProdutoDTO);
//
//
//    }
    @GET
    @Path("/estoque")
    @Produces(MediaType.APPLICATION_JSON)
    public String obterProdutosComEstoque() {
        return produtosDAO.obterProdutosComEstoque();
    }
    @POST
    @Path("/excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirProduto(JsonObject json) {
        Integer id = json.getInt("id");
        Produtos produto = produtosDAO.buscarProdutoPorId(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        produtosDAO.excluirProdutoSeNaoPossuirDadosProduto(produto);
        return Response.noContent().build();
    }

}

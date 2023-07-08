package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.FornecedorBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.FornecedorDTO;
import br.edu.ifg.luziania.model.util.Templetes;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedo")
public class FornecedorController {
    @Inject
    FornecedorBO  fornecedorBO;
    @Inject
    UsuarioBO usuarioBO;
    @Inject
    Template Fornecedor;
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")
    public Response carregapaginasFornecedor() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("4")))
                .build();
    }
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarcategoria() {
        return fornecedorBO.listarFornecedores();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastros")
    public Response savar(FornecedorDTO fornecedorDTO) {
        return fornecedorBO.cadastrarFornecedor(fornecedorDTO);
    }
    @DELETE
    @Path("/excluir/{id}")
    public Response excluir(@PathParam("id") Integer id) {
        return fornecedorBO.DeletaraFornecedor(id);

    }
    @PUT
    @Path("/atulizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") Integer id, FornecedorDTO fornecedorDTO) {
        return fornecedorBO.atualizarFornecedor(id,fornecedorDTO);
    }
}

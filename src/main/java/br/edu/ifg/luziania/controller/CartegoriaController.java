package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.CategoriaBO;
import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.CatergoriaDTO;
import br.edu.ifg.luziania.model.dto.FornecedorDTO;
import br.edu.ifg.luziania.model.util.Templetes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cartegoria")
public class CartegoriaController {
    @Inject
    CategoriaBO categoriaBO;
    @Inject
    UsuarioBO usuarioBO;
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")
    public Response carregapaginasFornecedor() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("9")))
                .build();
    }
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarcategoria() {
        return categoriaBO.lista();
    }
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CadastrasCategoria(CatergoriaDTO catergoriaDTO) {
        return categoriaBO.cadastrarCategoria(catergoriaDTO);
    }
    @DELETE
    @Path("/excluir/{id}")
    public Response excluirCartegoia(@PathParam("id") Integer id) {
        return categoriaBO.DeletaraCategoria(id);

    }
    @PUT
    @Path("/atulizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") Integer id, CatergoriaDTO catergoriaDTO) {
        return categoriaBO.atualizarFornecedor(id,catergoriaDTO);
    }
}

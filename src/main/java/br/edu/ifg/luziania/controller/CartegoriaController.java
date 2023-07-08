package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.CategoriaBO;
import br.edu.ifg.luziania.model.dto.CatergoriaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cartegoria")
public class CartegoriaController {
    @Inject
    CategoriaBO categoriaBO;
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
}

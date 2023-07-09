package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.SenhaDTO;
import br.edu.ifg.luziania.model.dto.UsuarioDTO;
import br.edu.ifg.luziania.model.util.Templetes;
import io.quarkus.qute.Template;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class LoginController {

    @Inject
    Template login;

    @Inject
    UsuarioBO usuarioBO;

    @POST
    @Path("/autenticar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(UsuarioDTO usuarioDTO) {
        return usuarioBO.autenticar(usuarioDTO);
    }
    @POST
    @Path("/setuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setUser(String token) {
        return usuarioBO.SetUsuario(token);
    }
    @POST
    @Path("/trocarsenha")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AtulSenha(SenhaDTO senhaDTO) {
        return usuarioBO.Atulisar(senhaDTO);
    }

    /**
     * Endpoint para cadastrar um usuário.
     *
     * @param dto Objeto com os dados do usuário a ser cadastrado
     * @return Resposta com o status e mensagem do resultado da operação
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public Response cadastra(UsuarioDTO dto) {
        return usuarioBO.cadastrarUsuario(dto);


    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sair")
    public Response logout() {
        return usuarioBO.logout();


    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/profile")
    public Response principal() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("8")))
                .build();
    }


}

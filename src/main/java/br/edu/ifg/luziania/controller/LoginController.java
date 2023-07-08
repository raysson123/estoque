package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.dto.UsuarioDTO;
import io.quarkus.qute.Template;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/login")
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


    ///
//    @GET
//    public Response errorPage(@Context HttpServletRequest request, @Context UriInfo uriInfo) {
//        // Obtém a URL da página não encontrada
//        String requestUrl = uriInfo.getRequestUri().toString();
//
//        // Pode usar requestUrl para personalizar a página de erro, exibindo a URL não encontrada ou outras informações
//
//        return Response.status(Response.Status.NOT_FOUND)
//                .entity("Página não encontrada: " + requestUrl)
//                .build();
//    }
}

package br.edu.ifg.luziania.controller;


//import br.edu.ifg.luziania.model.util.Redirecinar;


import br.edu.ifg.luziania.model.bo.UsuarioBO;
import br.edu.ifg.luziania.model.util.Sessao;
import br.edu.ifg.luziania.model.util.Templetes;
import io.quarkus.qute.Template;
import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class PainelController {

    @Inject
    Template painel;

    @Inject
    UsuarioBO usuarioBO;





    @GET
    @Produces(MediaType.TEXT_HTML)

    public Response principal() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1(usuarioBO.validar("6")))
                .build();
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/c")
    public Response principal1() {

        return Response.status(Response.Status.OK)
                .entity(Templetes.valores1("7"))
                .build();
    }


}



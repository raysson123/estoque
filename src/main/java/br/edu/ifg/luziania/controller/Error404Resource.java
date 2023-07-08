package br.edu.ifg.luziania.controller;


import br.edu.ifg.luziania.model.util.Templetes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/404")
public class Error404Resource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response error404() {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Templetes.valores1(""))
                .build();
    }
}

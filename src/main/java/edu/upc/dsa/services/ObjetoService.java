package edu.upc.dsa.services;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.GameManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/objetos", description = "Endpoint de objetos")
@Path("/objetos")
public class ObjetoService {

    private GameManager db; // Una vez se instale la librería de sql será el encargado

    @GET // GET a specific user that has an id
    @ApiOperation(value = "get objetos by username", notes = "get objetos by username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects(@PathParam("username") String username) {
        Usuario u = this.db.getUser(username);
        if (u == null) {
            return Response.status(404).build();
        } else return Response.status(201).entity(u).build();
    }
}


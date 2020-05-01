package edu.upc.dsa.services;

import edu.upc.dsa.models.Usuario;
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

@Api(value = "/usuario", description = "Endpoint de usuarios")
@Path("/usuario")
public class UsuarioService {

    private UserDaoImp db; // Una vez se instale la librería de sql será el encargado

    @GET // GET a specific user that has an id
    @ApiOperation(value = "get user by id", notes = "We get a user based of a id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        Usuario u = this.db.getUser(id);
        if (u == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(u).build();
    }



}

package edu.upc.dsa.services;

import edu.upc.dsa.models.UserDataManager;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
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

    @POST
    @ApiOperation(value = "add new usuario", notes = "Adding new user to database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/usuario/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("AddUser") UserDataManager addingUser) {

        if(addingUser == null){
            return Response.status(500).build();
        }
        Usuario u = this.bd.addUser(addingUser.getName(),addingUser.getEmail(),addingUser.getPassword());
        return Response.status(201).entity(u.getId()).build();
    }

    @PUT
    @ApiOperation(value = "update User data", notes = "updated")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Usuario.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/usuario/{name}")
    public Response updateUser(UserDataManager updatingUser) {

        Usuario u = this.bd.getUser(updatingUser.getName());

        if (u == null) return Response.status(404).build();
        else {
            this.bd.updateUser(updatingUser.getName(),updatingUser.getEmail(),updatingUser.getPassword());
            return Response.status(201).entity(u).build();
        }
    }




}

package edu.upc.dsa.services;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import edu.upc.dsa.util.GameManager;
import edu.upc.dsa.util.GameManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/user", description = "Endpoint to Students Service")
@Path("/")
public class GameService{

    private GameManager gm;

    public GameService(){
        this.gm = GameManagerImp.getInstance();
        if(this.gm.getNumUsers() == 0){
            this.gm.addUser("Marc","Vila","marclays");
            this.gm.addUser("Toni", "Norton", "tonilivo");
            this.gm.addUser("Ferran", "Lopez", "elbicho");

            this.gm.addObjectUser("marclays","Espada", "Espada gigante");
            this.gm.addObjectUser("marclays","Arco", "Arco magnifico");
            this.gm.addObjectUser("tonilivo", "Escudo", "Escudo gigante");
        }
    }

    @GET //GET all users sorted by lastname (alphabetically)
    @ApiOperation(value = "get all users", notes = "Returns list of all users sorted by lastname")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/User")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){

        List<User> allUsers = this.gm.getUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(allUsers) {};
        return Response.status(201).entity(entity).build() ;
    }

    @GET // GET a specific user that has an id
    @ApiOperation(value = "get user by id", notes = "We get a user based of a id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User u = this.gm.getUser(id);
        if (u == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "add new user", notes = "Adding new user to database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/User/{id}/{name}/{lastname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStudent(@PathParam("id") String id, @PathParam("name") String name, @PathParam("lastname") String lastname) {

        if(id == null || name == null || lastname == null){
            return Response.status(500).build();
        }
        this.gm.addUser(name,lastname,id);
        User u = this.gm.getUser(id);
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get objects that a user has", notes = "Getting objects from user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Objeto.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User/{id}/Objects")
    public Response getObjectsUser(@PathParam("id") String id){
        User u = this.gm.getUser(id);
        if(u == null) return Response.status(404).build();
        else{
            List<Objeto> objs = u.getObjects();
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objs) {};
            return Response.status(201).entity(entity).build() ;
        }
    }

    @PUT
    @ApiOperation(value = "add object to a user", notes = "Object add")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Objeto.class),
            @ApiResponse(code = 404, message = "Subject/Student not found")
    })
    @Path("/User/{id}")
    public Response enrollStudent(@PathParam("id") String id, Objeto obj) {

        User u = this.gm.getUser(id);

        if (u == null) return Response.status(404).build();
        else {
            this.gm.addObjectUser(id,obj.getId(),obj.getDescrp());
            return Response.status(201).entity(u).build();
        }
    }

    @PUT
    @ApiOperation(value = "update a user based on a ID", notes = "user updated (id cannot change) IMPORTANT")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User")
    public Response updateUser(User u) {

        User us = this.gm.getUser(u.getId());
        if (us == null || u == null) return Response.status(404).build();
        else {
            this.gm.updateUser(u.getName(),u.getLastname(),u.getMail(),u.getId());
            return Response.status(201).entity(us).build();
        }
    }
}

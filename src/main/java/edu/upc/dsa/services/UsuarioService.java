package edu.upc.dsa.services;

import edu.upc.dsa.models.UserDataManager;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.GameManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/usuario", description = "Endpoint de usuarios")
@Path("/usuario")
public class UsuarioService {

    private GameManager db; // Una vez se instale la librería de sql será el encargado

    @GET
    @ApiOperation(value = "Get All Users", notes = "Returns all the users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        ResultSet rs;
        List<Usuario> users=new LinkedList<>();

        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createSELECTALL("users"));

            while(rs.next()){
                users.add(new Usuario(rs.getString("nombre"),rs.getString("correo"),rs.getString("contraseña")));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        GenericEntity<List<Usuario>> entity = new GenericEntity<>(users){};
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "Get Info of User by username", notes = "You will see the info of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer=""),
    })
    @Path("/users/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@PathParam("username") String username) {
        ResultSet rs;
        Usuario u;
        if (username.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createQuerySELECT("users","username",username,"*"));

            rs.last();

            u = new Usuario(rs.getString("username"),rs.getString("correo") ,rs.getString("contraseña"));

            return Response.status(201).entity(u).build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }



    }

    @POST
    @ApiOperation(value = "Create new user", notes = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(UserDataManager u) {
        if (u.getName()==null || u.getEmail()==null)  return Response.status(500).entity(u).build();

        try {
            Session session = FactorySession.openSession();
            session.insertQuery(QueryHelper.createQueryINSERT(u,"users"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).entity(u).build();
    }

    @PUT
    @ApiOperation(value = "update User data", notes = "updated")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Usuario.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/usuario/{name}")
    public Response updateUser(UserDataManager updatingUser) {

        Usuario u = this.db.getUser(updatingUser.getName());

        if (u == null) return Response.status(404).build();
        else {
            try {
                Session session = FactorySession.openSession();
                int updating = session.insertQuery(QueryHelper.createQueryUPDATE("users","nombre",u));
                if (updating==0){
                    return Response.status(404).build();
                }
                else{
                    return Response.status(201).build();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }




}

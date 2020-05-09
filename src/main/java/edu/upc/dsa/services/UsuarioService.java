package edu.upc.dsa.services;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import dsa.grupo2.UserDaoImp;
import dsa.grupo2.models.User;
import dsa.grupo2.util.QueryHelper;
import edu.upc.dsa.models.UserDataManager;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.GameManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
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

    private UserDaoImp db; // Una vez se instale la librería de sql será el encargado

/*    @GET
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
            rs = session.query(QueryHelper.createQuerySELECT(Usuario, "user"));

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
*/

    @GET
    @ApiOperation(value = "Get Info of User by username", notes = "You will see the info of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer=""),
    })
    @Path("/users/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@PathParam("username") String username) {


        if (username.equals("")) return Response.status(404).build();
        try {

            User u = db.getUserByName(username);

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
           db.addUser(u.getName(),u.getEmail(),u.getPassword());
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


        User u = db.getUserByName(updatingUser.getOldname());

        if (u == null) return Response.status(404).build();
        else {
            try {
                db.updateUser(updatingUser.getName(),updatingUser.getEmail(),updatingUser.getPassword(),u.getId());
                return Response.status(201).build();
            }
            catch (Exception e){
                e.printStackTrace();
                return Response.status(404).build();
            }
        }
    }




}

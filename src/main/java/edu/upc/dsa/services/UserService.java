package edu.upc.dsa.services;

import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.RegisterCredentials;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserTO;
import edu.upc.dsa.util.UserManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api(value = "/user", description = "Endpoint de usuarios")
@Path("/user")
public class UserService {

    private UserManagerImp instance = UserManagerImp.getInstance();

    @GET
    @ApiOperation(value = "Get Info of User by username", notes = "You will see the info of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserTO.class, responseContainer=""),
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@PathParam("name") String name) {

        if (name.equals("")) return Response.status(404).build();
        else {
            try {
                User user = instance.getUserByName(name);
                UserTO userTO = new UserTO();
                userTO.setTO(user);
                return Response.status(201).entity(userTO).build();

            } catch (Exception ex) {
                ex.printStackTrace();
                return Response.status(404).build();
            }
        }

    }

    @POST
    @ApiOperation(value = "Create new user", notes = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UserTO.class),
            @ApiResponse(code = 409, message = "User Already exists"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(RegisterCredentials u) {
        if (u.getName()==null || u.getEmail()==null || u.getName().equals("") || u.getEmail().equals("")
        || u.getPassword() == null || u.getPassword().equals(""))  return Response.status(500).entity(u).build();

        else {
            try {
                User user = new User(u.getName(),u.getEmail(), u.getPassword());
                String id = instance.addUser(user);
                if (id != null) {
                    User newUser = instance.getUserByID(id);
                    UserTO userTO = new UserTO();
                    userTO.setTO(newUser);
                    return Response.status(201).entity(userTO).build();
                }
                else {
                    return Response.status(409).build();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return Response.status(500).build();
            }
        }
    }

    @PUT
    @ApiOperation(value = "update User data", notes = "updated")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response updateUser(User updatingUser, @PathParam("id") String oldId) {
        try {
            User u = instance.updateUser(updatingUser.getName(), updatingUser.getPassword(), updatingUser.getEmail(), oldId);
            if (u == null) {
                return Response.status(404).build();
            }
            else {
                return Response.status(201).entity(u).build();
            }
        }

        catch (Exception e) {
            return Response.status(404).build();
        }
    }




    @POST
    @ApiOperation(value = "Login", notes = "Check login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= String.class),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 409, message = "Wrong Login"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkLogin(Credentials cred) {
        String token = null;
        if (cred.getName()==null || cred.getPassword() == null)  return Response.status(400).build();

        try {
            User u = new User();
            u.setName(cred.getName());
            u.setPassword(cred.getPassword());
            token = instance.checkLogin(u);
            if (token.equals("FALSE")) {
                return Response.status(409).build();
            }

            else if (!token.equals("FALSE")) {
                return Response.status(201).entity(token).build();
            }
            else
                return Response.status(400).build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }
}

package edu.upc.dsa.services;

import dsa.grupo2.ItemDAOImp;
import dsa.grupo2.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/objetos", description = "Endpoint de objetos")
@Path("/objetos")
public class ObjetoService {


    @GET // Get item by ID
    @ApiOperation(value = "get objetos by username", notes = "get objetos by username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "item not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsById(@PathParam("id") String id) {
        ItemDAOImp itemDAOImp = new ItemDAOImp();
        dsa.grupo2.models.Item i = itemDAOImp.getItemById(id);
        if (i == null) {
            return Response.status(404).build();
        } else return Response.status(201).entity(i).build();
    }

    @GET // Get item by name
    @ApiOperation(value = "get objetos by username", notes = "get objetos by username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "item not found")
    })
    @Path("/objetos/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsByName(@PathParam("name") String name) {
        ItemDAOImp itemDAOImp = new ItemDAOImp();
        dsa.grupo2.models.Item i = itemDAOImp.getItemById(name);
        if (i == null) {
            return Response.status(404).build();
        } else return Response.status(201).entity(i).build();
    }
    @POST
    @ApiOperation(value = "Create new item", notes = "Adds a new item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= dsa.grupo2.models.Item.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/additem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Item item) {
        if (item.getName()==null || item.getId()==null)  return Response.status(500).entity(item).build();

        try {
            ItemDAOImp itemDAOImp = new ItemDAOImp();
            itemDAOImp.addItem(item);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).entity(item).build();
    }
}


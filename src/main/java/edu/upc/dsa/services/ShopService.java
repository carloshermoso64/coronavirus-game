package edu.upc.dsa.services;

import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserTO;
import edu.upc.dsa.util.ShopManager;
import edu.upc.dsa.util.ShopManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/tienda", description = "Endpoint de tienda")
@Path("/tienda")
public class ShopService {
    private ShopManager shopManager = ShopManagerImp.getInstance();

    @GET
    @ApiOperation(value = "Buy a mask", notes = "Buy a Mask")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
    })

    @Path("/{userid}/buymask")
    public Response buyMask(@PathParam("userid") String userId) {
        boolean bought = shopManager.buyMask(userId);

        if (bought) {
            return Response.status(200).build();
        }
        else {
            return Response.status(401).build();
        }
    }

    @GET
    @ApiOperation(value = "Buy a life", notes = "Buy a Mask")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
    })

    @Path("/{userid}/buymaxlife")
    public Response buyMaxLife(@PathParam("userid") String userId) {
        boolean bought = shopManager.buyLife(userId);

        if (bought) {
            return Response.status(200).build();
        }
        else {
            return Response.status(401).build();
        }
    }

    @GET
    @ApiOperation(value = "Buy a neuron", notes = "Buy a Mask")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED"),
    })
    @Path("/{userid}/buyneuron")
    public Response buyNeuron(@PathParam("userid") String userId) {
        boolean bought = shopManager.buyNeurons(userId);

        if (bought) {
            return Response.status(200).build();
        }
        else {
            return Response.status(401).build();
        }
    }
    @GET
    @ApiOperation(value = "Get all items on shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ShopItem.class, responseContainer="List"),
    })
    @Path("/getshop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop() {
        List<ShopItem> items = shopManager.getShop();
        GenericEntity<List<ShopItem>> entity = new GenericEntity<List<ShopItem>>(items) {};
        if (items.size() > 0) {
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }

    @PUT
    @ApiOperation(value = "change price of an item", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ShopItem.class),
            @ApiResponse(code = 401, message = "Need Admin Privileges"),
            @ApiResponse(code = 404, message = "Item not found"),

    })
    @Path("/updateItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop(ShopItem item) {

        ShopItem oldItem = shopManager.getItem(item.getName());
        if (oldItem == null) {
            return Response.status(404).build();
        }
        else {
            shopManager.updateProduct(item.getName(), item.getCost());
            ShopItem actItem = shopManager.getItem(item.getName());
            return Response.status(201).entity(actItem).build();
        }

    }


}

package edu.upc.dsa.services;


import edu.upc.dsa.models.*;
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
import java.util.ArrayList;
import java.util.List;

@Api(value = "/game", description = "Endpoint de partidas")
@Path("/game")

public class GameService {

    private GameManager gameManager = GameManagerImp.getInstance();

    @GET
    @ApiOperation(value = "get a level", notes = "get level ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = LevelTO.class),
            @ApiResponse(code = 404, message = "Level not found")
    })
    @Path("/level/{lvlnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendLevel(@PathParam("lvlnumber") int lvlNumber) {

        LevelTO lvl = gameManager.getLevel(lvlNumber);

        if (lvl != null)
            return Response.status(201).entity(lvl).build();

        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "get all levels", notes = "get all levels ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = LevelTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Level not found")
    })
    @Path("/levels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendLevels() {

        ArrayList<LevelTO> lvls  = (ArrayList<LevelTO>) gameManager.getAllLevels();
        GenericEntity<List<LevelTO>> entity = new GenericEntity<List<LevelTO>>(lvls) {};

        if (lvls.size() > 0)
            return Response.status(201).entity(entity).build();

        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "get the game of a user", notes = "get level ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class),
            @ApiResponse(code = 404, message = "Level not found")
    })
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendLevel(@PathParam("userId") String userId) {

        Game game = gameManager.getGame(userId);

        if (game != null)
            return Response.status(201).entity(game).build();

        return Response.status(404).build();
    }

    @PUT
    @ApiOperation(value = "notify level completed", notes = "updated")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Game.class),
    })
    @Path("/levelcompleted")
    public Response levelCompleted(CompletedLevel lvl) {
        try {
            Game game = gameManager.levelCompleted(lvl);

            return Response.status(201).entity(game).build();
        }

        catch (Exception e){
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "get the game of a user", notes = "get level ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Error")
    })
    @Path("/createlevel")
    public Response postLevel(LevelTO newLevel) {
        try {
            gameManager.addLevel(newLevel.getMap());
            return Response.status(201).build();
        }

        catch (Exception e) {
            return Response.status(500).build();
        }
    }

}



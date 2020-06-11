package edu.upc.dsa.services;


import edu.upc.dsa.models.*;
import edu.upc.dsa.util.StatsManager;
import edu.upc.dsa.util.StatsManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/stats", description = "Endpoint de historial y estadisticas")
@Path("/stats")
public class StatsService {

    private StatsManager statsManager = StatsManagerImp.getInstance();

    @GET
    @ApiOperation(value = "get ranking of users", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserTO.class, responseContainer="List"),
    })
    @Path("/userranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRanking() {
        List<UserTO> ranking = statsManager.getUsersByLevel();

        GenericEntity<List<UserTO>> entity = new GenericEntity<List<UserTO>>(ranking) {};
        if (ranking.size() > 0) {
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "get scores of a level", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = BestLevelTO.class, responseContainer="List"),
    })
    @Path("/levelscores/{levelnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestLevels(@PathParam("levelnumber") int level) {
        List<BestLevelTO> ranking = statsManager.getSortedBestScoresOfLevel(level);

        GenericEntity<List<BestLevelTO>> entity = new GenericEntity<List<BestLevelTO>>(ranking) {};
        if (ranking.size() > 0) {
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }


    @GET
    @ApiOperation(value = "get best scores of a user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = BestLevel.class, responseContainer="List"),
    })
    @Path("/userscores/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestLevels(@PathParam("username") String username) {
        List<BestLevelTO> ranking = statsManager.getSortedBestLevelsOfUser(username);

        GenericEntity<List<BestLevelTO>> entity = new GenericEntity<List<BestLevelTO>>(ranking) {};
        if (ranking.size() > 0) {
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }
}

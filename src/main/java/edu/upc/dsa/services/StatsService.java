package edu.upc.dsa.services;

import edu.upc.dsa.StatsManager;
import edu.upc.dsa.StatsManagerImpl;
import edu.upc.dsa.models.Stats;

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

@Api(value = "/stats", description = "EndPoint StatsService")
@Path("/stats")
public class StatsService {

    private StatsManager sm;

    public StatsService(){
        this.sm = StatsManagerImpl.getInstance();
        if (sm.size()==0) {
            this.sm.addStats(18500, 9,85, 15, 50);
            this.sm.addStats(740,3,0,4,35);
            this.sm.addStats(21000,12,0,39,90);
        }
    }

    @GET
    @ApiOperation(value = "Estadisticas de todas las partidas", notes = "toda la informacion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Stats.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats() {

        List<Stats> stats = this.sm.findAll();

        GenericEntity<List<Stats>> entity = new GenericEntity<List<Stats>>(stats) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Estadisticas de una partida concreta", notes = "informacion de partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Stats.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStat(@PathParam("id") String id) {
        Stats s = this.sm.getStats(id);
        if (s == null) return Response.status(404).build();
        else  return Response.status(201).entity(s).build();
    }

    @DELETE
    @ApiOperation(value = "delete a stat", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteStat(@PathParam("id") String id) {
        Stats s = this.sm.getStats(id);
        if (s == null) return Response.status(404).build();
        else this.sm .deleteStats(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateStat(Stats stats) {

        Stats s = this.sm.updateStats(stats);

        if (s == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Stats.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStat(Stats stats) {

        if (stats.getPuntuacion()== 0 || stats.getDias()==0 || stats.getSalud()==0 || stats.getAlimentos()==0 || stats.getEntretenimiento()==0)  return Response.status(500).entity(stats).build();
        this.sm.addStats(stats);
        return Response.status(201).entity(stats).build();
    }



}

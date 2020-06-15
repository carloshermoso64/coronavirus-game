package edu.upc.dsa.services;

import edu.upc.dsa.models.*;
import edu.upc.dsa.util.ForumManager;
import edu.upc.dsa.util.ForumManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/forum", description = "Endpoint de chat")
@Path("/forum")
public class ForumService {

    public ForumManager forumManager= ForumManagerImp.getInstance();


    @POST
    @Path("/newthread")
    @ApiOperation(value = "create new thread", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createThread(@FormDataParam("author") String author,
                                 @FormDataParam("name") String name,
                                @FormDataParam("content") String content) {

        try {
            forumManager.createThread(author,name,content);
            return Response.status(201).build();
        }

        catch (Exception e) {
            return Response.status(500).build();
        }

    }

    @GET
    @Path("/threads")
    @ApiOperation(value = "get all threads", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = ForumThread.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllThreads() {
        ArrayList<ForumThread> allThreads = (ArrayList<ForumThread>) forumManager.getAllThreads();
        GenericEntity<List<ForumThread>> entity = new GenericEntity<List<ForumThread>>(allThreads) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @Path("/{threadid}")
    @ApiOperation(value = "post new message in thread", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postMessage(@FormDataParam("author") String author,
                                 @FormDataParam("content") String content,
                                @PathParam("threadid") String threadId) {

        try {
            forumManager.addMessageToThread(author, content, threadId);
            return Response.status(201).build();
        }

        catch (Exception e) {
            return Response.status(500).build();
        }

    }

    @GET
    @Path("/{threadid}")
    @ApiOperation(value = "get all messages of thread", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = ForumThread.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Produces(MediaType.APPLICATION_JSON)

    public Response getThreadMessages(@PathParam("threadid") String threadId) {
        ArrayList<ForumMessage> allMessages = (ArrayList<ForumMessage>) forumManager.getThreadContent(threadId);
        GenericEntity<List<ForumMessage>> entity = new GenericEntity<List<ForumMessage>>(allMessages) {};
        return Response.status(200).entity(entity).build();
    }

}

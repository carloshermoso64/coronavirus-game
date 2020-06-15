package edu.upc.dsa.services;


import edu.upc.dsa.models.*;
import edu.upc.dsa.util.ChatManager;
import edu.upc.dsa.util.ChatManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/chat", description = "Endpoint de chat")
@Path("/chat")
public class ChatService {

    public ChatManager msgManager = ChatManagerImp.getInstance();

    @POST
    @ApiOperation(value = "Send message", notes = "Adds a message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UserTO.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(ReceivedMessage msg) {


        msgManager.addMessage(msg);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Get all messages", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Message.class, responseContainer="List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages() {
        List<Message> messages = msgManager.getAllMessages();
        GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(messages) {};
            return Response.status(201).entity(entity).build();
    }

}

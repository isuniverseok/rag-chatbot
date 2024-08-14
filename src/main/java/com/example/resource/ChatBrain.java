package com.example.resource;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

@Path("/api/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatBrain {

    private static final ConcurrentMap<String, ChatSession> chatSessions = new ConcurrentHashMap<>();
    //private static final Logger logger = Logger.getLogger(ChatBrain.class.getName());

    @POST
    @Path("/start")
    public Response startChat(ChatStartRequest request) {
        String chatThreadId = UUID.randomUUID().toString();
        ChatSession session = new ChatSession(chatThreadId, request.getAssetId());
        chatSessions.put(chatThreadId, session);

        return Response.ok(new ChatStartResponse(chatThreadId, "How can I help you?")).build();
    }

    @POST
    @Path("/message")
    public Response continueChat(ChatMessageRequest request) {
        ChatSession session = chatSessions.get(request.getChatThreadId());
        if (session == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Chat session not found").build();
        }

        String agentResponse = processUserMessage(session, request.getUserMessage());

        return Response.ok(new ChatMessageResponse(agentResponse)).build();
    }

    private String processUserMessage(ChatSession session, String userMessage) {
        return userMessage;
    }
}


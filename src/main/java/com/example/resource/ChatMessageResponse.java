package com.example.resource;

public class ChatMessageResponse {
    private String agentResponse;

    public ChatMessageResponse(String agentResponse) {
        this.agentResponse = agentResponse;
    }

    public String getAgentResponse() {
        return agentResponse;
    }
}

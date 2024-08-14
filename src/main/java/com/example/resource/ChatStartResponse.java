package com.example.resource;

public class ChatStartResponse {
    public final String chatThreadId;
    public final String message;

    public ChatStartResponse(String chatThreadId, String message) {
        this.chatThreadId = chatThreadId;
        this.message = message;
    }

    public String getChatThreadId() {
        return chatThreadId;
    }

    public String getMessage() {
        return message;
    }
}

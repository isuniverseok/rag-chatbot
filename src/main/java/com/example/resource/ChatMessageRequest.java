package com.example.resource;

public class ChatMessageRequest {
    private String chatThreadId;
    private String userMessage;

    public String getChatThreadId() {
        return chatThreadId;
    }

    public void setChatThreadId(String chatThreadId) {
        this.chatThreadId = chatThreadId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}

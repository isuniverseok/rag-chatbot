package com.example.resource;

public class ChatSession {
    private String chatThreadId;
    private String assetId;

    public ChatSession(String chatThreadId, String assetId) {
        this.chatThreadId = chatThreadId;
        this.assetId = assetId;
    }

    public String getChatThreadId() {
        return chatThreadId;
    }

    public String getAssetId() {
        return assetId;
    }
}


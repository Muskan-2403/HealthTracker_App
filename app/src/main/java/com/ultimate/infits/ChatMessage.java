package com.ultimate.infits;

public class ChatMessage {
    public  String senderId, receiverId,message,time;

    public ChatMessage(String senderId, String receiverId, String message, String time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.time = time;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}

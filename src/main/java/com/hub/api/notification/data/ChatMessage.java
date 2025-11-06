package com.hub.api.notification.data;

import lombok.Data;

@Data
public class ChatMessage {
    private String from;
    private String content;
    private String room; // optional: phòng/room
}


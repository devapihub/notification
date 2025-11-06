package com.hub.api.notification.controller;

import com.hub.api.notification.data.ChatMessage;
import com.hub.api.notification.data.ChatResponse;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // Client gửi tới /app/chat.send -> broadcast tới /topic/chat/{room}
    @MessageMapping("/chat.send")
    public void sendToRoom(@Payload ChatMessage msg) {
        String dest = "/topic/chat/" + (msg.getRoom() == null ? "global" : msg.getRoom());
        template.convertAndSend(dest, new ChatResponse(msg.getFrom(), msg.getContent()));
    }

    // Ví dụ: server trả lời riêng cho 1 user (nếu dùng user sessions)
    @MessageMapping("/chat.whisper")
    public void whisper(@Header("simpSessionId") String sessionId, @Payload ChatMessage msg) {
        template.convertAndSendToUser(sessionId, "/queue/whisper",
                new ChatResponse(msg.getFrom(), "(private) " + msg.getContent()));
    }
}


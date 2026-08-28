package org.example.controller;

import org.example.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message is required"));
        }
        
        String answer = chatService.chat(message);
        
        // Theo đề bài thì cần trả về "answer" và "sources".
        // Tuy nhiên trong phạm vi ChatClient trả về String, để lấy được Sources ta cần cấu hình phức tạp hơn.
        // Tạm thời trả về object answer.
        return ResponseEntity.ok(Map.of("answer", answer));
    }
}

package org.example.config;

import org.example.tools.AITools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, AITools aiTools) {
        return builder
                .defaultTools(aiTools)
                .build();
    }
}

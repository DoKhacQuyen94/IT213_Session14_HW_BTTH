package org.example.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public ChatService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String chat(String message) {
        return chatClient.prompt()
                .system("""
                    Bạn là Rikkei Internal AI Assistant.
                    Bạn chỉ được sử dụng thông tin có trong context được cung cấp từ hệ thống.
                    Nếu không tìm thấy thông tin trong tài liệu, hãy trả lời: "Xin lỗi, tôi không tìm thấy thông tin này trong tài liệu nội bộ."
                    Không được tự suy đoán hoặc bịa thông tin.
                    Sử dụng Tool khi cần truy vấn dữ liệu nghiệp vụ về nhân viên, phòng ban.
                    """)
                .user(message)
                .advisors(
                    QuestionAnswerAdvisor.builder(vectorStore).build()
                )
                .call()
                .content();
    }
}

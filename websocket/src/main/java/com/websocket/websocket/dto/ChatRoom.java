package com.websocket.websocket.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.socket.WebSocketSession;

import com.websocket.websocket.service.ChatService;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chatrooms")
public class ChatRoom {
    @Id
    private String id;
    private String name;
    @Builder.Default
    private Set<String> userIds = new HashSet<>(); // 참여한 유저 ID들
    private transient Set<WebSocketSession> sessions = new HashSet<>(); // 참여한 유저 세션들

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if(chatMessage.getType() == ChatMessage.MessageType.ENTER){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

}

package com.websocket.websocket.dto;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

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
@Document(collection = "chatmessages")
public class ChatMessage { 
    @Id
    private String id;

    // 채팅 메세지 클래스
    // 메세지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK, QUIT
    } 
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private long userCount;
    private LocalDateTime timestamp;

}

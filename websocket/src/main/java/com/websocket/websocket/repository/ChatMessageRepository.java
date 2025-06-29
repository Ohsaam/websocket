package com.websocket.websocket.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websocket.websocket.dto.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
}

package com.websocket.websocket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websocket.websocket.dto.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

}

package com.websocket.websocket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.websocket.dto.ChatMessage;
import com.websocket.websocket.dto.ChatRoom;
import com.websocket.websocket.repository.ChatMessageRepository;
import com.websocket.websocket.repository.ChatRoomRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    // 채팅방 생성
    public ChatRoom createRoom(String name){
        ChatRoom chatRoom = ChatRoom.builder()
            .id(UUID.randomUUID().toString())
            .name(name)
            .build();
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
    // 모든 채팅방 조회
    public List<ChatRoom> findAllRoom(){
        return chatRoomRepository.findAll();
    }

    // 특정 채팅방 조회
    public ChatRoom findRoomById(String id){
        return chatRoomRepository.findById(id).orElse(null);
    }

    public ChatMessage saveMessage(ChatMessage message){
        message.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> findMessagesByRoomId(String roomId){
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }

}

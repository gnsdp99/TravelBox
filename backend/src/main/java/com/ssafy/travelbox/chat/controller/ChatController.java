package com.ssafy.travelbox.chat.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.travelbox.chat.model.dao.ChatDao;
import com.ssafy.travelbox.chat.model.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatDao chatDao;

    @MessageMapping("/chat/{planId}")
    @SendTo("/topic/chat/{planId}")
    public ChatMessage chat(@DestinationVariable(value = "planId") int planId, @RequestBody ChatMessage chatMessage) {
        chatDao.saveMessage(chatMessage);
        return chatDao.findMessageById(chatMessage.getChatId());
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> findAllMessages(@PathVariable("planId") int planId) {
        List<ChatMessage> messages = chatDao.findAllMessages(planId);
        if (messages != null && !messages.isEmpty()) {
            return ResponseEntity.ok(messages) ;
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

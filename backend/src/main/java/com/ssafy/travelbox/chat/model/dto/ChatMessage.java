package com.ssafy.travelbox.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMessage {

    private int chatId;
    private int planId;
    private String senderId;
    private String senderName;
    private String content;
    private String sendDate;
}

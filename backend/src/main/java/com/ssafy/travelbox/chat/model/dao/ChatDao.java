package com.ssafy.travelbox.chat.model.dao;

import com.ssafy.travelbox.chat.model.dto.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatDao {

    List<ChatMessage> findAllMessages(int planId);

    ChatMessage findMessageById(int chatId);

    int saveMessage(ChatMessage message);
}

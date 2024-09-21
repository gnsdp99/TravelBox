package com.ssafy.travelbox.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chatbot")
@Slf4j
public class ChatBotController {
    @Value("${openai.api.key}")
    private String openaiApiKey;

    @PostMapping
    public ResponseEntity<Map<String, Object>> chat(@RequestBody Map<String, String> requestBody) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String userInput = requestBody.get("message");
        log.debug(userInput);
        String url = "https://api.openai.com/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 요청 생성
            String requestBodyString = objectMapper.writeValueAsString(Map.of(
                    "model", "gpt-3.5-turbo",
                    "messages", new JsonNode[]{
                            objectMapper.createObjectNode()
                                    .put("role", "user")
                                    .put("content", userInput)
                    }
            ));

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openaiApiKey);

            // HTTP 요청 생성
            HttpEntity<String> entity = new HttpEntity<>(requestBodyString, headers);

            // API 호출
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            String chatResponse = responseBody.get("choices").get(0).get("message").get("content").asText();
            status = HttpStatus.OK;
            resultMap.put("chat", chatResponse);

        } catch (Exception e) {
            log.error("Error while calling OpenAI API", e);
            resultMap.put("error", "Error while calling OpenAI API");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }
}

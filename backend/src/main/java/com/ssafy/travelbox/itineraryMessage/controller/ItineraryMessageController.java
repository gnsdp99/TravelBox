package com.ssafy.travelbox.itineraryMessage.controller;

import com.ssafy.travelbox.itineraryMessage.model.dto.ItineraryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItineraryMessageController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/itinerary/{planId}")
    @SendTo("/topic/itinerary/{planId}")
    public ItineraryMessage addItinerary(@DestinationVariable(value = "planId") int planId, ItineraryMessage message) {
        log.info("# planId = {}", planId);
        log.info("# message = {}", message);
        return message;
    }
}

package com.ssafy.travelbox.itineraryMessage.model.dto;

import com.ssafy.travelbox.itinerary.model.dto.Itinerary;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ItineraryMessage {

    private String userId;
    private Date date; // 변경된 날짜
    private Date prevDate; // 변경되기 전 날짜
    private List<Itinerary> list; // 변경된 날짜의 리스트
    private List<Itinerary> prevList; // 변경되기 전 날짜의 리스트
}

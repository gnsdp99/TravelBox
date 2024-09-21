package com.ssafy.travelbox.itinerary.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class Itinerary {

    private int planId;
    private int contentId;
    private String memo;
    private Date date; // 여행 날짜
    private int indexNumber; // drag-and-drop 인덱스
    private String title;
    private String image;
    private String address;
    private String latitude;
    private String longitude;
}

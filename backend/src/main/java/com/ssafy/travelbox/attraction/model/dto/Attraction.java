package com.ssafy.travelbox.attraction.model.dto;

import lombok.Data;

@Data
public class Attraction {

    private int contentId;
    private int contentTypeId;
    private double latitude;
    private double longitude;
    private String title;
    private String overview;
    private String image;
    private String address;
}

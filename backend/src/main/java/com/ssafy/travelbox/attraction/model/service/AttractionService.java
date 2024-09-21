package com.ssafy.travelbox.attraction.model.service;

import com.ssafy.travelbox.attraction.model.dto.Attraction;

import java.util.List;

public interface AttractionService {

    List<Attraction> findAllAttraction();

    List<Attraction> findAllAttractionByTitle(String title);
}

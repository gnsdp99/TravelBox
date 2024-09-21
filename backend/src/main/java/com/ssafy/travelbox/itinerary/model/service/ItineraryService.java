package com.ssafy.travelbox.itinerary.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.travelbox.itinerary.model.dto.Itinerary;

import java.util.List;
import java.util.Map;

public interface ItineraryService {

    int addItinerary(Itinerary itinerary);

    void changeOrder(List<Itinerary> list) throws JsonProcessingException;

    int removeAndReOrder(Itinerary itinerary);

    Itinerary findItinerary(Map<String, String> param);

    List<Itinerary> findItineraries(int planId);

    Itinerary findLastItinerary(int planId);
}

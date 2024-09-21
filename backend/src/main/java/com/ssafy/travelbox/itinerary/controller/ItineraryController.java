package com.ssafy.travelbox.itinerary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.travelbox.itinerary.model.dao.ItineraryDao;
import com.ssafy.travelbox.itinerary.model.dto.Itinerary;
import com.ssafy.travelbox.itinerary.model.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itinerary")
@Slf4j
public class ItineraryController {

    private final ItineraryService itineraryService;

    @GetMapping("/{planId}")
    public ResponseEntity<?> list(@PathVariable("planId") int planId) {

        List<Itinerary> list = itineraryService.findItineraries(planId);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{planId}/last")
    public ResponseEntity<?> findLastOne(@PathVariable("planId") int planId) {

        Itinerary itinerary = itineraryService.findLastItinerary(planId);
        if (itinerary != null) {
            return ResponseEntity.ok(itinerary);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Itinerary itinerary) {

        if (itinerary != null) {
            itineraryService.addItinerary(itinerary);
            return ResponseEntity.ok(itinerary);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<?> changeOrder(@RequestBody List<Itinerary> list) throws JsonProcessingException {

        if (list != null && !list.isEmpty()) {
            itineraryService.changeOrder(list);
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> remove(@RequestParam Map<String, String> param) {

        Itinerary itinerary = itineraryService.findItinerary(param);
        if (itinerary != null) {
            itineraryService.removeAndReOrder(itinerary);
            return ResponseEntity.ok(itinerary);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

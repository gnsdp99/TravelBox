package com.ssafy.travelbox.attraction.controller;

import com.ssafy.travelbox.attraction.model.dto.Attraction;
import com.ssafy.travelbox.attraction.model.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("")
    public ResponseEntity<?> list() {

        List<Attraction> list = attractionService.findAllAttraction();
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> listByTitle(@PathVariable("title") String title) {

        List<Attraction> list = attractionService.findAllAttractionByTitle(title);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

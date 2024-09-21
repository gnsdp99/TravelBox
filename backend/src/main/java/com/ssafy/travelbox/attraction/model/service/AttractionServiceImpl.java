package com.ssafy.travelbox.attraction.model.service;

import com.ssafy.travelbox.attraction.model.dao.AttractionDao;
import com.ssafy.travelbox.attraction.model.dto.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionDao attractionDao;

    @Override
    public List<Attraction> findAllAttraction() {
        return attractionDao.selectAll();
    }

    @Override
    public List<Attraction> findAllAttractionByTitle(String title) {
        return attractionDao.selectAllByTitle(title);
    }
}

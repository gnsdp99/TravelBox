package com.ssafy.travelbox.itinerary.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.travelbox.itinerary.model.dao.ItineraryDao;
import com.ssafy.travelbox.itinerary.model.dto.Itinerary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryDao itineraryDao;

    @Override
    public int addItinerary(Itinerary itinerary) {
        return itineraryDao.insert(itinerary);
    }

    @Override
    public void changeOrder(List<Itinerary> list) throws JsonProcessingException {
        for (Itinerary itinerary : list) {
            itineraryDao.update(itinerary);
        }
    }

    @Override
    public int removeAndReOrder(Itinerary itinerary) {
        int result = itineraryDao.delete(itinerary);
        itineraryDao.updateIndexAfterDelete(itinerary);
        return result;
    }

    @Override
    public Itinerary findItinerary(Map<String, String> param) {
        return itineraryDao.selectOne(param);
    }

    @Override
    public List<Itinerary> findItineraries(int planId) {
        return itineraryDao.selectAll(planId);
    }

    @Override
    public Itinerary findLastItinerary(int planId) {
        return itineraryDao.selectLastOne(planId);
    }
}

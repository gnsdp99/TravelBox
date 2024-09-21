package com.ssafy.travelbox.itinerary.model.dao;

import com.ssafy.travelbox.itinerary.model.dto.Itinerary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItineraryDao {

    int insert(Itinerary itinerary);

    int update(Itinerary itinerary);

    int updateIndexAfterDelete(Itinerary itinerary); // 삭제한 행이 포함된 날짜 인덱스 재정렬

    int delete(Itinerary itinerary);

    Itinerary selectOne(Map<String, String> param);

    List<Itinerary> selectAll(int planId);

    Itinerary selectLastOne(int planId);
}

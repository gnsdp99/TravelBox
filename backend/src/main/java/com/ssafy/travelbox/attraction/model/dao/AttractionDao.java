package com.ssafy.travelbox.attraction.model.dao;

import com.ssafy.travelbox.attraction.model.dto.Attraction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionDao {

    List<Attraction> selectAll();

    List<Attraction> selectAllByTitle(String title);
}

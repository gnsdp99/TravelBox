package com.ssafy.travelbox.user.model.mapper;

import com.ssafy.travelbox.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface UserMapper {

    UserDto login(UserDto userDto) throws SQLException;
    UserDto userInfo(String userId) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    Object getRefreshToken(String userid) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    void signUp(UserDto userDto) throws SQLException;
    boolean existsById(String userid) throws SQLException;
    void modify(UserDto userDto) throws SQLException;
    void delete(String userId) throws SQLException;
    String getPassword(String userId) throws SQLException;
}

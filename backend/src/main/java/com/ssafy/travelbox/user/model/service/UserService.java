package com.ssafy.travelbox.user.model.service;

import com.ssafy.travelbox.user.model.dto.UserDto;

public interface UserService {

    UserDto login(UserDto userDto) throws Exception;
    UserDto userInfo(String userId) throws Exception;
    void saveRefreshToken(String userId, String refreshToken) throws Exception;
    Object getRefreshToken(String userId) throws Exception;
    void deleRefreshToken(String userId) throws Exception;
    void signUp(UserDto userDto) throws Exception;
    boolean existsById(String userId) throws Exception;
    void modify(UserDto userDto) throws Exception;
    void delete(String userId) throws Exception;
    String getPassword(String userId) throws Exception;
}

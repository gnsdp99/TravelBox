package com.ssafy.travelbox.user.model.service;

import com.ssafy.travelbox.user.model.mapper.UserMapper;
import com.ssafy.travelbox.user.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        super();
        this.userMapper = userMapper;
    }
    @Override
    public UserDto login(UserDto userDto) throws Exception {
        return userMapper.login(userDto);
    }

    @Override
    public UserDto userInfo(String userId) throws Exception {
        return userMapper.userInfo(userId);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", userId);
        map.put("token", refreshToken);
        userMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userId) throws Exception {
        return userMapper.getRefreshToken(userId);
    }

    @Override
    public void deleRefreshToken(String userId) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", userId);
        map.put("token", null);
        userMapper.deleteRefreshToken(map);
    }

    @Override
    public void signUp(UserDto userDto) throws Exception {
       userMapper.signUp(userDto);
    }

    @Override
    public boolean existsById(String userId) throws Exception {
        return userMapper.existsById(userId);
    }

    @Override
    public void modify(UserDto userDto) throws Exception {
        userMapper.modify(userDto);
    }

    @Override
    public void delete(String userId) throws Exception {
        userMapper.delete(userId);
    }

    @Override
    public String getPassword(String userId) throws Exception {
        return userMapper.getPassword(userId);
    }


}

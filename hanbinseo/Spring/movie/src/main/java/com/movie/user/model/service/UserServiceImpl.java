package com.movie.user.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.user.model.UserDto;
import com.movie.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
		return userMapper.loginUser(map);
	}

}

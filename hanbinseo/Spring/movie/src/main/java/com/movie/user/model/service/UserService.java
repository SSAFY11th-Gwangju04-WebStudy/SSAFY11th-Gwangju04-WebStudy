package com.movie.user.model.service;

import java.util.Map;

import com.movie.user.model.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;

	void joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(Map<String, String> map) throws Exception;
}

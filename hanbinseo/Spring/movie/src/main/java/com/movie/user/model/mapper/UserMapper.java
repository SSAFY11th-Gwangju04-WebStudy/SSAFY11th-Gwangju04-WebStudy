package com.movie.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.movie.user.model.UserDto;

@Mapper
public interface UserMapper {

	int idCheck(String id) throws SQLException;

	void joinUser(UserDto memberDto) throws SQLException;

	UserDto loginUser(Map<String, String> map) throws SQLException;

}

package com.movie.movie.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.movie.movie.model.MovieDto;
import com.movie.movie.model.FileInfoDto;

@Mapper
public interface MovieMapper {
	void writeMovie(MovieDto movieDto) throws SQLException;

	List<MovieDto> listMovie() throws SQLException;

	MovieDto getMovie(int id) throws SQLException;

	void modifyMovie(MovieDto movieDto) throws SQLException;

	void deleteMovie(int id, String path) throws SQLException;
	
	List<FileInfoDto> fileInfoList(int id) throws Exception;
}

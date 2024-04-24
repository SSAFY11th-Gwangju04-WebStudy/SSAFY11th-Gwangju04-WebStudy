package com.movie.movie.model.service;

import java.util.List;
import java.util.Map;

import com.movie.movie.model.FileInfoDto;
import com.movie.movie.model.MovieDto;

public interface MovieService {
	void writeMovie(MovieDto movieDto) throws Exception;

	List<MovieDto> listMovie() throws Exception;

	MovieDto getMovie(int id) throws Exception;

	void modifyMovie(MovieDto movieDto) throws Exception;

	void deleteMovie(int id, String path) throws Exception;
}

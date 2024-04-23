package com.movie.movie.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.movie.model.FileInfoDto;
import com.movie.movie.model.MovieDto;
import com.movie.movie.model.mapper.MovieMapper;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;

	@Override
	@Transactional
	public void writeMovie(MovieDto movieDto) throws Exception {
		movieMapper.writeMovie(movieDto);
		List<FileInfoDto> fileInfos = movieDto.getFileInfos();
	}

	@Override
	public List<MovieDto> listMovie() throws Exception {
		return movieMapper.listMovie();
	}

	@Override
	public MovieDto getMovie(int id) throws Exception {
		return movieMapper.getMovie(id);
	}

	@Override
	public void modifyMovie(MovieDto movieDto) throws Exception {
		movieMapper.modifyMovie(movieDto);
	}

	@Override
	public void deleteMovie(int id, String path) throws Exception {
		movieMapper.deleteMovie(id, path);
	}

}

package com.movie.movie.model;

import java.util.List;

import lombok.Data;

@Data
public class MovieDto {
	private int id;
	private String title;
	private String director;
	private String genre;
	private int runningTime;
	private String img;
	private List<FileInfoDto> fileInfos;
}

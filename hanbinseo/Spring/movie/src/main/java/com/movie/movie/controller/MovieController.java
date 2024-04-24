package com.movie.movie.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.movie.model.MovieDto;
import com.movie.movie.model.service.MovieService;
import com.movie.movie.model.FileInfoDto;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private final String UPLOAD_PATH = "/uploads";

	@Autowired
	private ServletContext servletContext;
	@Autowired
	private MovieService movieService;

	@GetMapping("/write")
	public String write() {
		return "movie/write";
	}

	@PostMapping("/write")
	public String write(MovieDto movieDto, @RequestParam("upfile") MultipartFile[] files,
			RedirectAttributes redirectAttributes) throws Exception {

		if (!files[0].isEmpty()) {
			String realPath = servletContext.getRealPath(UPLOAD_PATH);
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = realPath + File.separator + today;
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}

			movieService.writeMovie(movieDto);
		}
		return "redirect:/movie/list";
	}

	@GetMapping("/list")
	public String list(Model model) throws Exception {
		List<MovieDto> list = movieService.listMovie();
		model.addAttribute(list);
		return "movie/list";
	}

	@GetMapping("/view")
	public String view(@RequestParam("id") int id, Model model) throws Exception {
		MovieDto movieDto = movieService.getMovie(id);
		model.addAttribute(movieDto);
		return "movie/view";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("articleno") int articleNo, Model model) throws Exception {
		MovieDto movieDto = movieService.getMovie(articleNo);
		model.addAttribute("movie", movieDto);
		return "movie/modify";
	}

	@PostMapping("/modify")
	public String modify(MovieDto movieDto, RedirectAttributes redirectAttributes) throws Exception {
		movieService.modifyMovie(movieDto);
		return "redirect:/movie/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id, RedirectAttributes redirectAttributes) throws Exception {
		movieService.deleteMovie(id, servletContext.getRealPath(UPLOAD_PATH));
		return "redirect:/movie/list";
	}
}

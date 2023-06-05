package com.api.project.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.api.project.model.Film;
import com.api.project.model.FilmSession;
import com.api.project.model.Uploader;
import com.api.project.service.UploaderFilmService;
import com.api.project.service.ViewerFilmService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/viewer")
public class ViewerFilmController {
	@Autowired
	private ViewerFilmService viewerFilmService;
	
	@GetMapping("/films")
	public String findAllFilms(Model model) {
		List<Film> films = viewerFilmService.findAllFilms();
		model.addAttribute("films", films);
		return "viewer-film-list";
	}
	
	@PostMapping("/films")
	public String findAllFilmSessionsByCity(@RequestParam("city") String city, Model model) {
		List<FilmSession> filmSessions = viewerFilmService.findAllFilmSessionsByCity(city);
		model.addAttribute("filmSessions", filmSessions);
		return "viewer-film-session-list";
	}
	
	@GetMapping("/film/{encodedTitle}")
	public String showFilmDetails(@PathVariable("encodedTitle") String encodedTitle, Model model) {
		String title = UriUtils.decode(encodedTitle, "UTF-8");
		Film film = viewerFilmService.findFilmByTitle(title);
		if (film != null) {
			model.addAttribute("film", film);
			return "viewer-film-details";
		} else {
            model.addAttribute("errorMessage", "Internal error while searching film " + title + ". Please try again later.");
            List<Film> films = viewerFilmService.findAllFilms();
            model.addAttribute("films", films);
            return "viewer-film-list";
		}
	}
}
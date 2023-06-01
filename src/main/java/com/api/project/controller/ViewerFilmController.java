package com.api.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.project.model.Film;
import com.api.project.service.UploaderFilmService;


@Controller
@RequestMapping("/viewer-film")
public class ViewerFilmController {
	@Autowired
	private final ViewerFilmService viewerFilmService;

    public ViewerFilmController(ViewerFilmService viewerFilmService) {
        this.viewerFilmService = viewerFilmService;
    }
    
    @GetMapping("/all-films")
	public String findAllFilms(Model model) {
	    List<FilmSession> films = viewerFilmService.findAllFilms();

    @GetMapping("/sessions-by-movie")
    public List<FilmSession> getMovieSessionsByMovieName(@RequestParam String movieName) {
        return viewerFilmService.getMovieSessionsByMovieName(movieName);
    }

    @GetMapping("/sessions-by-city")
    public List<MovieSession> getMovieSessionsByCity(@RequestParam String city) {
        return viewerFilmService.getMovieSessionsByCity(city);
    }

}

package com.api.project.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import com.api.project.model.Film;
import com.api.project.model.FilmSession;
import com.api.project.service.ViewerFilmService;

@Controller
@RequestMapping("/viewer")
public class ViewerFilmController {
	@Autowired
	private ViewerFilmService viewerFilmService;
	
	// Redirected to list of films
	@GetMapping("")
	public String redirectToAllFilms() {
		return "redirect:/viewer/films";
	}
	
	// View all films in database
	@GetMapping("/films")
	public String findAllFilms(Model model) {
		List<Film> films = viewerFilmService.findAllFilms();
		model.addAttribute("films", films);
		return "viewer-film-list";
	}
	
	// Find films by city or title
	@PostMapping("/search")
	public String findFilmSessionsByCityAndFilmsByTitle(@RequestParam(required = false) String city,
			@RequestParam(required = false) String title, Model model) {
		if (city != "" && title == "") {
			List<Film> filmsInCity = viewerFilmService.findAllFilmsByCity(city);
			System.out.println(filmsInCity);
			if (filmsInCity.isEmpty()) {
				model.addAttribute("errorSearch", "No films found in " + city);
			} else {
				model.addAttribute("films", filmsInCity);
			}
		} else if (title != "" && city == "") {
			List<Film> filmsContainingTitle = viewerFilmService.findAllFilmsByTitleContaining(title);
			if (filmsContainingTitle.isEmpty()) {
				model.addAttribute("errorSearch", "No films found in with title " + title);
			} else {
				model.addAttribute("films", filmsContainingTitle);
			}
		} else if (title != "" && city != "") {
			model.addAttribute("errorSearch", "Only one field is allowed.");
			List<Film> films = viewerFilmService.findAllFilms();
			model.addAttribute("films", films);
		} else {
			model.addAttribute("errorSearch", "Error while searching films. Try again later.");
			List<Film> films = viewerFilmService.findAllFilms();
			model.addAttribute("films", films);			
		}
		return "viewer-film-list";
	}
	
	// Show details of selected film
	@GetMapping("/film/{encodedTitle}")
	public String showFilmDetails(@PathVariable("encodedTitle") String encodedTitle, Model model) {
		String title = UriUtils.decode(encodedTitle, "UTF-8");
		Film film = viewerFilmService.findFilmByTitle(title);
		if (film != null) {
			List<FilmSession> filmSessions = viewerFilmService.findAllFilmSessionsByFilm(film);
	        Random random = new Random();
	        Double randomImdbRating = 7.0 + (random.nextDouble() * (9.8 - 7.0));
	        randomImdbRating = Math.round(randomImdbRating * 10.0) / 10.0;
	        Integer randomPopularityRating = random.nextInt(61) + 70;
	        model.addAttribute("film", film);
			model.addAttribute("filmSessions", filmSessions);
			model.addAttribute("randomImdbRating", randomImdbRating);
			model.addAttribute("randomPopularityRating", randomPopularityRating);
			return "viewer-film-details";
		} else {
            model.addAttribute("errorMessage", "Internal error while searching film " + title + ". Please try again later.");
            List<Film> films = viewerFilmService.findAllFilms();
            model.addAttribute("films", films);
            return "viewer-film-list";
		}
	}
}
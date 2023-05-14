package com.api.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.project.model.FilmModel;
import com.api.project.service.FilmService;

@Controller
@RequestMapping("/film")
public class FilmController {
	@Autowired
	private FilmService filmService;
    
	@GetMapping("/list")
	public ResponseEntity<List<FilmModel>> getAllFilms() {
		List<FilmModel> films = filmService.getAllFilms();
		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/film_id/{id}")
	public ResponseEntity<FilmModel> getFilmById(@PathVariable("id") Long id) {
		FilmModel film = filmService.getFilmById(id);
		return film != null ? ResponseEntity.ok(film) : ResponseEntity.notFound().build();
//		return film != null ? ResponseEntity.ok(film) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film with id " + id.toString() + " not found!");
	}
	
	@GetMapping("/user_id/{userId}")
	public ResponseEntity<List<FilmModel>> getAllFilmsByUserId(@PathVariable("userId") Long userId) {
		List<FilmModel> films = filmService.getAllFilmsByUserId(userId);
		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<FilmModel>> getAllFilmsByTitle(@PathVariable("title") String title) {
		List<FilmModel> films = filmService.getAllFilmsByTitle(title);
		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
	}
}
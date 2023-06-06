package com.api.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.Film;
import com.api.project.model.FilmSession;
import com.api.project.repository.FilmRepository;
import com.api.project.repository.FilmSessionRepository;

@Service
public class ViewerFilmService {
	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private FilmSessionRepository filmSessionRepository;
	
    public List<Film> findAllFilms() {
    	List<Film> films = (List<Film>) filmRepository.findAll();
    	if (films.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return films;
    }
    
    public List<Film> findAllFilmsByCity(String city) {
        List<FilmSession> filmSessionsInCity = filmSessionRepository.findAllByCity(city);
        Set<Film> films = filmSessionsInCity.stream()
                .map(FilmSession::getFilm)
                .collect(Collectors.toSet());
        return new ArrayList<>(films);
    }
        
    public List<Film> findAllFilmsByTitleContaining(String title) {
    	List<Film> films = filmRepository.findByTitleContaining(title);
    	if (films.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return films;
    }
    
    public Film findFilmByTitle(String title) {
    	Film film = filmRepository.findByTitle(title);
    	return film;
    }
    
    public List<FilmSession> findAllFilmSessionsByFilm(Film film) {
    	List<FilmSession> filmSessions = filmSessionRepository.findAllByFilm(film);
    	if (filmSessions.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return filmSessions;
    }
}

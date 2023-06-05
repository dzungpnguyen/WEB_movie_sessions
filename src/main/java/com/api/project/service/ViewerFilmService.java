package com.api.project.service;

import java.util.Collections;
import java.util.List;

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
    
    public List<FilmSession> findAllFilmSessionsByCity(String city) {
    	List<FilmSession> filmSessions = filmSessionRepository.findAllByCity(city);
    	if (filmSessions.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return filmSessions;
    }
    
    public Film findFilmByTitle(String title) {
    	Film film = filmRepository.findByTitle(title);
    	return film;
    }
}

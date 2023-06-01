package com.api.project.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.Film;
import com.api.project.model.Uploader;
import com.api.project.repository.FilmRepository;
import com.api.project.repository.UploaderRepository;

@Service

public class ViewerFilmService {
	@Autowired
	private final FilmRepository movieRepository;
    private final FilmSessionRepository movieSessionRepository;

    public ViewerFilmService(FilmRepository filmRepository, FilmSessionRepository filmSessionRepository) {
        this.filmRepository = filmRepository;
        this.filmSessionRepository = filmSessionRepository;
    }
    

    // Find all films sessions in the database
    public List<FilmSession> findAllFilms() {
    	List<FilmSession> filmSessions = (List<FilmSession>) filmSessionRepository.findAll();
    	if (filmSessions.isEmpty()) {
    		return Collections.emptyList();
//    		throw new EntityNotFoundException("[Entity Not Found]: No films found in the database.");
    	}
    	return filmSessions;
    }
    
    	//find all sessions for one given movie 
    public List<FilmSession> getMovieSessionsByMovieName(String title) {
        return filmSessionRepository.findByMovieName(title);
    }

    	//find all movies for one given city 
    public List<FilmSession> getMovieSessionsByCity(String city) {
        return filmSessionRepository.findByCity(city);
    }


}
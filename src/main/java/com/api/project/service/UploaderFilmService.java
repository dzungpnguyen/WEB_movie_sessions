package com.api.project.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.Film;
import com.api.project.model.FilmSession;
import com.api.project.model.Uploader;
import com.api.project.repository.FilmRepository;
import com.api.project.repository.FilmSessionRepository;
import com.api.project.repository.UploaderRepository;

@Service
public class UploaderFilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmSessionRepository filmSessionRepository;
    @Autowired
    private UploaderRepository uploaderRepository;
    @Autowired
    private ViewerFilmService viewerFilmService;
   
    // Find a film by film id
    public Film findFilmById(Long id) {
      Optional<Film> film = filmRepository.findById(id);
      return film.orElse(null);
    }
    
    // Find all films in database
    public List<Film> findAllFilms() {
    	List<Film> films = (List<Film>) filmRepository.findAll();
    	if (films.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return films;
    }
    
    // Find all films of an uploader by uploader id
    public List<Film> findAllFilmsByUploaderId(Long uploaderId) {
    	Optional<Uploader> uploader = uploaderRepository.findById(uploaderId);
    	if (uploader.isPresent()) {
    		List<Film> filmsByUploader = filmRepository.findAllByUploader(uploader.get());
    		if (filmsByUploader.isEmpty()) {
    			return Collections.emptyList();
    		} else {
          return filmsByUploader;
        }
    	} else {
    		return Collections.emptyList();
    	}
    }
    
    // Find all films of an uploader
    public List<Film> findAllFilmsByUploader(Uploader uploader) {
		List<Film> filmsByUploader = filmRepository.findAllByUploader(uploader);
		if (filmsByUploader.isEmpty()) {
			return Collections.emptyList();
		}
		return filmsByUploader;
    }

    // Save a film
    public Film saveFilm(Film newFilm) {
		Film savedFilm = filmRepository.save(newFilm);
		// handle error
		return savedFilm;
    }
    
    // Save a list of films
    public List<Film> saveAllFilms(List<Film> newFilms) {
		List<Film> savedFilms = (List<Film>) filmRepository.saveAll(newFilms);
		// handle error
		return savedFilms;
    }
    
    // Delete a film by film id
    public boolean deleteFilmById(Long id) {
    	Optional<Film> optionalFilm = filmRepository.findById(id);
    	Film film = optionalFilm.orElse(null);
		try {
			if (film != null) {
				List<FilmSession> filmSessions = viewerFilmService.findAllFilmSessionsByFilm(film);
				filmSessionRepository.deleteAll(filmSessions);
				filmRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
        } catch (Exception e) {
            return false;
        }
    }
    
    // Save a film session
    public FilmSession saveFilmSession(FilmSession newFilmSession) {
    	FilmSession savedFilmSession = filmSessionRepository.save(newFilmSession);
    	return savedFilmSession;
    }
    
    // Delete a film session by film session id
    public boolean deleteFilmSessionById(Long id) {
        try {
        	filmSessionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Delete all films in the database
    public void deleteAllFilms(List<Film> films) {
    	filmRepository.deleteAll(films);
    }
}

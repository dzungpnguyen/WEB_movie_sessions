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
public class UploaderFilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private UploaderRepository uploaderRepository;
    
    /*
     * Main film services for Uploaders
     */
    
    // Find all films in the database
    public List<Film> findAllFilms() {
    	List<Film> films = (List<Film>) filmRepository.findAll();
    	if (films.isEmpty()) {
    		return Collections.emptyList();
//    		throw new EntityNotFoundException("[Entity Not Found]: No films found in the database.");
    	}
    	return films;
    }
    
    // Find all films by uploader's id
    public List<Film> findAllFilmsByUploaderId(Long uploaderId) {
    	Optional<Uploader> uploader = uploaderRepository.findById(uploaderId);
    	if (uploader.isPresent()) {
    		List<Film> filmsByUploader = filmRepository.findAllByUploader(uploader.get());
    		if (filmsByUploader.isEmpty()) {
    			return Collections.emptyList();
    		}
    		return filmsByUploader;
    	} else {
    		return Collections.emptyList();
    	}
    }
    
    // Find all films by uploader
    public List<Film> findAllFilmsByUploader(Uploader uploader) {
		List<Film> filmsByUploader = filmRepository.findAllByUploader(uploader);
		if (filmsByUploader.isEmpty()) {
			return Collections.emptyList();
		}
		return filmsByUploader;
    }

    // Update an existing film or add a new film
    public Film saveFilm(Film newFilm) {
		Film savedFilm = filmRepository.save(newFilm);
		// handle error
		return savedFilm;
    }
    
    // Add multiple new films
    public List<Film> saveAllFilms(List<Film> newFilms) {
		List<Film> savedFilms = (List<Film>) filmRepository.saveAll(newFilms);
		// handle error
		return savedFilms;
    }
    
    // Delete a film by id
    public boolean deleteFilmById(Long id) {
        try {
        	filmRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Delete multiple films
    public void deleteAllFilms(List<Film> films) {
    	filmRepository.deleteAll(films);
    	// handle error
    }
}

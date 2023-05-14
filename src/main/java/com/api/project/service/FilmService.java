package com.api.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.FilmModel;
import com.api.project.repository.FilmRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    
//    public FilmService() {
//    	List<FilmModel> films = new ArrayList<FilmModel>();
//    	List<String> actors = new ArrayList<String>();
//    	actors.add("Dzung1");
//    	actors.add("Dzung2");
//    	LocalDate start = LocalDate.of(2021, 12, 1);
//    	LocalDate end = LocalDate.of(2021, 12, 31);    	
////    	FilmModel film = FilmModel.builder()
////    		    .id(1L)
////    		    .title("Film Title")
////    		    .duration(120.5)
////    		    .language("English")
////    		    .subtitles(Arrays.asList("English", "French"))
////    		    .director("John Doe")
////    		    .actors(Arrays.asList("Actor 1", "Actor 2"))
////    		    .minimumAge(18)
////    		    .startDate(LocalDate.of(2021, 12, 1))
////    		    .endDate(LocalDate.of(2022, 1, 1))
////    		    .build();
////    	films.add(film1);
//    }
    
    public List<FilmModel> getAllFilms() {
    	List<FilmModel> films = (List<FilmModel>) filmRepository.findAll();
    	if (films.isEmpty()) {
    		throw new EntityNotFoundException("No films found in the database");
    	}
    	return films;
    }

    public FilmModel getFilmById(Long id) {
    	Optional<FilmModel> film = filmRepository.findById(id);
    	if (film.isEmpty()) {
    		throw new EntityNotFoundException("No film found with the id: " + id.toString());
    	}
    	return film.orElse(null);
    }
    
    public List<FilmModel> getAllFilmsByUserId(Long userId) {
    	List<FilmModel> films = filmRepository.findAllByUserId(userId);
    	if (films.isEmpty()) {
    		throw new EntityNotFoundException("User with id " + userId.toString() + " have not uploaded any films yet");
    	}
    	return films;
    }
    
    public List<FilmModel> getAllFilmsByTitle(String title) {
    	List<FilmModel> films = filmRepository.findAllByTitle(title);
    	if (films.isEmpty()) {
    		throw new EntityNotFoundException("No films found with the title: " + title);
    	}
    	return films;
    }
    
//    public FilmModel getFilmByTitle(String title) {
//    	Optional<FilmModel> film = filmRepository.findByTitle(title);
//    	if (film.isEmpty()) {
//    		throw new EntityNotFoundException("No film found with the title: " + title);
//    	}
//    	return film.orElse(null);
//    }
}


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
@RequestMapping("/uploader")
public class UploaderFilmController {
	@Autowired
	private UploaderFilmService uploaderFilmService;
    
//	@GetMapping("/film-list")
//	public ResponseEntity<List<FilmModel>> findAllFilmsByUploaderId(Long uploaderId) {
//		List<FilmModel> films = uploaderFilmService.findAllFilmsByUploaderId(uploaderId);
//		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/my-films")
	public String findAllFilms(Model model) {
	    List<Film> films = uploaderFilmService.findAllFilms();
	    if (films.isEmpty()) {
	        model.addAttribute("message", "No films found in your database.");
	    } else {
	        model.addAllAttributes(Map.of(
	        	      "films", films,
	        	      "message", films.size() + " film(s) found in your database."
	        	    ));
	    }
	    return "uploader-film-list";
	}
	
//	@GetMapping("/my-films")
//	public String findAllFilmsByUploader(Model model, Uploader uploader) {
//	    List<Film> films = uploaderFilmService.findAllFilmsByUploader(uploader);
//	    if (films.isEmpty()) {
//	        model.addAttribute("message", "No films found in your database.");
//	    } else {
//	        model.addAllAttributes(Map.of(
//	        	      "uploader", uploader,
//	        	      "films", films,
//	        	      "message", films.size() + " films found in your database."
//	        	    ));
//	    }
//	    return "uploader-film-list";
//	}

	@GetMapping("/add-film/form")
	public String showAddFilmForm(Model model) {
	    model.addAttribute("film", new Film());
		return "uploader-add-film";
	}
		
	@PostMapping("/add-film/action")
    public String addNewFilm(@ModelAttribute("film") Film film, RedirectAttributes redirectAttributes) {
        Film newFilm = uploaderFilmService.saveFilm(film);
        if (newFilm != null) {
            redirectAttributes.addFlashAttribute("addingSuccess", "Film " + newFilm.getTitle() + " added successfully in your database.");
            redirectAttributes.addFlashAttribute("newFilm", newFilm);
        } else {
            redirectAttributes.addFlashAttribute("addingFailure", "Failed to add new film to your database.");
        }
        return "redirect:/uploader/my-films";
    }
	
    @DeleteMapping("/delete/{filmId}")
    public ResponseEntity<String> deleteFilm(@PathVariable("filmId") Long filmId) {
        boolean filmIsDeleted = uploaderFilmService.deleteFilmById(filmId);
    	return filmIsDeleted ? ResponseEntity.ok("Film with id " + filmId + " deleted successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting film");
    }


	
//	@GetMapping("/film_id/{id}")
//	public ResponseEntity<FilmModel> getFilmById(@PathVariable("id") Long id) {
//		FilmModel film = filmService.getFilmById(id);
//		return film != null ? ResponseEntity.ok(film) : ResponseEntity.notFound().build();
////		return film != null ? ResponseEntity.ok(film) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film with id " + id.toString() + " not found!");
//	}
	
//	@GetMapping("/user_id/{userId}")
//	public ResponseEntity<List<FilmModel>> getAllFilmsByUserId(@PathVariable("userId") Long userId) {
//		List<FilmModel> films = filmService.getAllFilmsByUserId(userId);
//		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
//	}
//	
//	@GetMapping("/title/{title}")
//	public ResponseEntity<List<FilmModel>> getAllFilmsByTitle(@PathVariable("title") String title) {
//		List<FilmModel> films = filmService.getAllFilmsByTitle(title);
//		return films != null ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
//	}
}
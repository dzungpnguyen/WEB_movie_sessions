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
import com.api.project.model.FilmSession;
import com.api.project.model.Uploader;
import com.api.project.service.UploaderFilmService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/uploader")
public class UploaderFilmController {
	@Autowired
	private UploaderFilmService uploaderFilmService;

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
			model.addAttribute("sessionUploader", registeredUploader);
	        return "dashboard";
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
	
	@GetMapping("/my-films")
	public String findAllFilms(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
		if (registeredUploader != null) {
			List<Film> films = uploaderFilmService.findAllFilms();
			model.addAttribute("sessionUploader", registeredUploader);
			if (films.isEmpty()) {
				model.addAttribute("message", "No films found in your database.");
			} else {
				model.addAllAttributes(Map.of(
						"films", films,
						"message", films.size() + " film(s) found in your database."
						));
			}
			return "uploader-film-list";
		} else {
			return "redirect:/sign-in";
		}
	}
	
	@GetMapping("/add-film")
	public String showAddFilmForm(HttpSession session, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	model.addAllAttributes(Map.of(
	    			"sessionUploader", registeredUploader,
	    			"film", new Film()
	    			));
	    	return "uploader-add-film";	    	
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
		
	@GetMapping("/add-session")
	public String showAddSessionForm(HttpSession session, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	model.addAllAttributes(Map.of(
	    			"sessionUploader", registeredUploader,
	    			"filmSession", new FilmSession()
	    			));
	    	return "uploader-add-session";
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
		
	@PostMapping("/add-film")
    public String addNewFilm(@ModelAttribute("film") Film film, HttpSession session, RedirectAttributes redirectAttributes) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	        Film newFilm = uploaderFilmService.saveFilm(film);
	        if (newFilm != null) {
	            redirectAttributes.addFlashAttribute("addingSuccessMessage", "Film " + newFilm.getTitle() + " added successfully.");
	            redirectAttributes.addFlashAttribute("newFilm", newFilm);
	        } else {
	            redirectAttributes.addFlashAttribute("addingFailureMessage", "Failed to add new film.");
	        }
	        return "redirect:/uploader/my-films";
	    } else {
	    	return "redirect:/sign-in";
	    }
    }
	
    @DeleteMapping("/delete/{filmId}")
    public ResponseEntity<String> deleteFilm(@PathVariable("filmId") Long filmId, HttpSession session) {
		boolean filmIsDeleted = uploaderFilmService.deleteFilmById(filmId);
		return filmIsDeleted ? ResponseEntity.ok("Film with id " + filmId + " deleted successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting film");    		
    }
}
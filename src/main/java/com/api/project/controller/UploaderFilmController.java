package com.api.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// Show dashboard page
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
	
	// Get all films in the database
	@GetMapping("/my-films")
	public String findAllFilms(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
		if (registeredUploader != null) {
			List<Film> films = uploaderFilmService.findAllFilms();
			model.addAttribute("sessionUploader", registeredUploader);
			if (films.isEmpty()) {
				model.addAttribute("message", "No films found in your database.");
			} else {
				model.addAttribute("films", films);
				model.addAttribute("message", films.size() + " film(s) found in your database.");
			}
			return "uploader-film-list";
		} else {
			return "redirect:/sign-in";
		}
	}
	
	// Show form to add new film
	@GetMapping("/add-film")
	public String showAddFilmForm(HttpSession session, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	model.addAttribute("sessionUploader", registeredUploader);
	    	model.addAttribute("film", new Film());
	    	return "uploader-add-film";	    	
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
	
	// Add new film
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
	
	// Show form to add new film session
	@GetMapping("/add-session/{filmId}")
	public String showAddSessionForm(@PathVariable("filmId") Long filmId, Model model, HttpSession session) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	Film selectedFilm = uploaderFilmService.findFilmById(filmId);
	    	model.addAttribute("sessionUploader", registeredUploader);
	    	model.addAttribute("selectedFilm", selectedFilm);
	    	model.addAttribute("filmSession", new FilmSession());
	    	return "uploader-add-session";
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
	
	// Add new film session
	@PostMapping("/add-session/{filmId}")
	public String addNewSession(@ModelAttribute("filmSession") FilmSession filmSession, @PathVariable("filmId") Long filmId,
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	if (filmSession != null) {
				Film film = uploaderFilmService.findFilmById(filmId);
				filmSession.setFilm(film);
				filmSession.setWeekday();
				filmSession.setEndHour();
				uploaderFilmService.saveFilmSession(filmSession);
	            redirectAttributes.addFlashAttribute("addingSessionSuccessMessage", "New Session for Film " + filmSession.getFilm().getTitle() + " added successfully.");
	            redirectAttributes.addFlashAttribute("newFilmSession", filmSession);
	    	} else {
	            redirectAttributes.addFlashAttribute("addingSessionFailureMessage", filmSession);	    		
	    	}
	        return "redirect:/uploader/my-films";
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
	
	// Delete a film by film id
	// @DeleteMapping("/delete/{filmId}")
    @GetMapping("/delete/{filmId}")
    public String deleteFilm(@PathVariable("filmId") Long filmId, HttpSession session, RedirectAttributes redirectAttributes) {
		boolean filmIsDeleted = uploaderFilmService.deleteFilmById(filmId);
		if (filmIsDeleted) {
			redirectAttributes.addFlashAttribute("deletingSuccessMessage", "Film with ID " + filmId + " is deleted!");
		} else {
			redirectAttributes.addFlashAttribute("deletingFailureMessage", "Error while deleting film with ID " + filmId + ". Try again later.");
		}
		return "redirect:/uploader/my-films";
    }
    
    // Delete a film session by film session id
    // @DeleteMapping("/delete-session/{filmSessionId}")
    @GetMapping("/delete-session/{filmSessionId}")
    public String deleteFilmSession(@PathVariable("filmSessionId") Long filmSessionId, HttpSession session, RedirectAttributes redirectAttributes) {
		boolean filmSessionIsDeleted = uploaderFilmService.deleteFilmSessionById(filmSessionId);
		if (filmSessionIsDeleted) {
			redirectAttributes.addFlashAttribute("deletingSessionSuccessMessage", "Film with ID " + filmSessionId + " is deleted!");
		} else {
			redirectAttributes.addFlashAttribute("deletingSessionFailureMessage", "Error while deleting film with ID " + filmSessionId + ". Try again later.");
		}
		return "redirect:/uploader/my-films";
	}

    // Show form to update a film
	@GetMapping("/update/{filmId}")
	public String showUpdateFilmForm(@PathVariable("filmId") Long filmId, Model model, HttpSession session) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
			Film selectedFilm = uploaderFilmService.findFilmById(filmId);
	    	model.addAttribute("sessionUploader", registeredUploader);
			model.addAttribute("selectedFilm", selectedFilm);
			model.addAttribute("newFilm", new Film());	
			model.addAttribute("test", "test-value");
	    	return "uploader-update-film";
	    } else {
	    	return "redirect:/sign-in";
	    }
	}
	
	// Update a film
	@PostMapping("/update/{filmId}")
    public String updateAFilm(@RequestParam("newId") Long newId, @RequestParam("newTitle") String newTitle,
    		@RequestParam("newDuration") Double newDuration, @RequestParam("newLanguage") String newLanguage,
    		@RequestParam("newSubtitles") String newSubtitles, @RequestParam("newDirector") String newDirector,
    		@RequestParam("newActors") String newActors, @RequestParam("newMinimumAge") Integer newMinimumAge,
    		@RequestParam("newStartDate") LocalDate newStartDate, @RequestParam("newEndDate") LocalDate newEndDate,
    		@PathVariable("filmId") Long filmId, @ModelAttribute("newfilm") Film film,
    		HttpSession session, RedirectAttributes redirectAttributes) {
		Uploader registeredUploader = (Uploader) session.getAttribute("registeredUploader");
	    if (registeredUploader != null) {
	    	Film newFilm = new Film();
	    	newFilm.setId(newId);
	    	newFilm.setTitle(newTitle);
	    	newFilm.setDuration(newDuration);
	    	newFilm.setLanguage(newLanguage);
	    	newFilm.setSubtitles(newSubtitles);
	    	newFilm.setDirector(newDirector);
	    	newFilm.setActors(newActors);
	    	newFilm.setMinimumAge(newMinimumAge);
	    	newFilm.setStartDate(newStartDate);
	    	newFilm.setEndDate(newEndDate);
	        Film selectedfilm = uploaderFilmService.saveFilm(newFilm);
	        if (selectedfilm != null) {
	            redirectAttributes.addFlashAttribute("updatingSuccessMessage", "Film " + selectedfilm.getTitle() + " updated successfully.");
	            redirectAttributes.addFlashAttribute("selectedfilm", selectedfilm);
	        } else {
	            redirectAttributes.addFlashAttribute("updatingFailureMessage", "Failed to update film.");
	        }
	        return "redirect:/uploader/my-films";
	    } else {
	    	return "redirect:/sign-in";
	    }
    }
}
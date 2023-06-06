package com.api.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.project.model.Film;
import com.api.project.model.FilmSession;
import com.api.project.model.Uploader;
import com.api.project.repository.FilmRepository;
import com.api.project.repository.FilmSessionRepository;
import com.api.project.repository.UploaderRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
    private UploaderRepository uploaderRepository;

	@Autowired
    private FilmRepository filmRepository;

	@Autowired
    private FilmSessionRepository filmSessionRepository;

    @Override
    public void run(String... args) {
        // Pre-populate the list of Uploaders
    	Uploader uploader1 = new Uploader((long) 1, "HarryPotter", "password");
        Uploader uploader2 = new Uploader((long) 2, "Woody", "password");
        Uploader uploader3 = new Uploader((long) 3, "Loki", "password");
        uploaderRepository.saveAll(Arrays.asList(uploader1, uploader2, uploader3));
        
        // Pre-populate the list of Films
        Film film1 = new Film(1L, "Inception", 2.5, "English", "English", "Christopher Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
        		13, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 8, 15), uploader1);
        Film film2 = new Film(2L, "Parasite", 2.17, "Korean", "English", "Bong Joon-ho", "Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong",
        		15, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 9, 30), uploader2);
        Film film3 = new Film((long) 3, "The Shawshank Redemption", 2.42, "English", "English", "Frank Darabont", "Tim Robbins, Morgan Freeman, Bob Gunton",
        		16, LocalDate.of(2023, 6, 15), LocalDate.of(2023, 8, 30), uploader3);
        Film film4 = new Film((long) 4, "Pulp Fiction", 2.58, "English", "English", "Quentin Tarantino", "John Travolta, Uma Thurman, Samuel L. Jackson",
        		18, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 8, 25), uploader1);
        Film film5 = new Film((long) 5, "The Dark Knight", 2.5, "English", "English", "Christopher Nolan", "Christian Bale, Heath Ledger, Aaron Eckhart",
        		13, LocalDate.of(2023, 6, 25), LocalDate.of(2023, 9, 5), uploader2);
        Film film6 = new Film((long) 6, "The Godfather", 2.92, "English", "English", "Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan",
        		17, LocalDate.of(2023, 7, 10), LocalDate.of(2023, 9, 20), uploader3);
        Film film7 = new Film((long) 7, "La La Land", 2.17, "English", "English", "Damien Chazelle", "Ryan Gosling, Emma Stone, John Legend",
        		12, LocalDate.of(2023, 7, 15), LocalDate.of(2023, 10, 1), uploader1);
        Film film8 = new Film((long) 8, "Schindler's List", 3.25, "English, German, Polish", "English", "Steven Spielberg", "Liam Neeson, Ben Kingsley, Ralph Fiennes",
        		16, LocalDate.of(2023, 7, 20), LocalDate.of(2023, 9, 15), uploader2);
        Film film9 = new Film((long) 9, "Fight Club", 2.33, "English", "English", "David Fincher", "Brad Pitt, Edward Norton, Helena Bonham Carter",
        		18, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 10, 31), uploader3);
        Film film10 = new Film((long) 10, "The Matrix", 2.25, "English", "English", "Lana Wachowski", "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
        		15, LocalDate.of(2023, 8, 10), LocalDate.of(2023, 10, 20), uploader1);
        filmRepository.saveAll(Arrays.asList(film1, film2, film3, film4, film5, film6, film7, film8, film9, film10));
        
        //Pre-populate the list of Film Sessions
        FilmSession filmSession1 = new FilmSession(1L, film1, LocalDate.of(2023, 6, 12), "Monday",
        		LocalTime.of(18, 0), LocalTime.of(20, 30), "Le Cinéma Paradis", "123 Rue de Paris, 75001 Paris", "Paris");
        FilmSession filmSession2 = new FilmSession(2L, film1, LocalDate.of(2023, 6, 14), "Wednesday",
        		LocalTime.of(10, 30), LocalTime.of(13, 0), "Ciné Lumière", "111 Rue du Vieux Lyon, 69005 Lyon", "Lyon");
        FilmSession filmSession3 = new FilmSession(3L, film1, LocalDate.of(2023, 6, 16), "Friday",
        		LocalTime.of(21, 20), LocalTime.of(23, 50), "Le Cinéma Mystère", "55 Quai de la Fosse, 44000 Nantes", "Nantes");
        
        FilmSession filmSession4 = new FilmSession(4L, film2, LocalDate.of(2023, 10, 3), "Tuesday",
        		LocalTime.of(18, 0), LocalTime.of(20, 10), "L'Écran Magique", "456 Avenue des Champs-Élysées, 75008 Paris", "Paris");
        FilmSession filmSession5 = new FilmSession(5L, film2, LocalDate.of(2023, 10, 5), "Thursday",
        		LocalTime.of(13, 25), LocalTime.of(15, 35), "L'Écran Bleu", "222 Avenue des Frères Lumière, 69008 Lyon", "Lyon");
        FilmSession filmSession6 = new FilmSession(6L, film2, LocalDate.of(2023, 10, 7), "Saturday",
        		LocalTime.of(11, 30), LocalTime.of(13, 40), "L'Écran Enchanté", "66 Rue du Calvaire, 44000 Nantes", "Nantes");

        FilmSession filmSession7 = new FilmSession(7L, film3, LocalDate.of(2023, 6, 16), "Friday",
        		LocalTime.of(14, 0), LocalTime.of(16, 25), "Le Grand Cinéma", "789 Rue du Louvre, 75002 Paris", "Paris");
        FilmSession filmSession8 = new FilmSession(8L, film3, LocalDate.of(2023, 6, 18), "Sunday",
        		LocalTime.of(19, 15), LocalTime.of(21, 40), "Cinéphile Plaza", "333 Rue Garibaldi, 69007 Lyon", "Lyon");
        FilmSession filmSession9 = new FilmSession(9L, film3, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "Cinéphile Palace", "10 Boulevard Saint-Germain, 75005 Paris", "Paris");
        
        FilmSession filmSession10 = new FilmSession(10L, film4, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "Le Cinéma Étoile", "321 Rue de Rivoli, 75004 Paris", "Paris");
        
        FilmSession filmSession11 = new FilmSession(11L, film5, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "Le Cinéma Paradis", "123 Rue de Paris, 75001 Paris", "Paris");
        
        FilmSession filmSession12 = new FilmSession(12L, film6, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "Ciné Lumière", "111 Rue du Vieux Lyon, 69005 Lyon", "Lyon");
        
        FilmSession filmSession13 = new FilmSession(13L, film7, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "Le Cinéma Mystère", "55 Quai de la Fosse, 44000 Nantes", "Nantes");
        
        FilmSession filmSession14 = new FilmSession(14L, film8, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "L'Écran Magique", "456 Avenue des Champs-Élysées, 75008 Paris", "Paris");
        
        FilmSession filmSession15 = new FilmSession(15L, film9, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "L'Écran Bleu", "222 Avenue des Frères Lumière, 69008 Lyon", "Lyon");
        
        FilmSession filmSession16 = new FilmSession(16L, film10, LocalDate.of(2023, 6, 20), "Tuesday",
        		LocalTime.of(9,55), LocalTime.of(12, 20), "L'Écran Enchanté", "66 Rue du Calvaire, 44000 Nantes", "Nantes");

        filmSessionRepository.saveAll(Arrays.asList(filmSession1, filmSession2, filmSession3, filmSession4, filmSession5,
        		filmSession6, filmSession7, filmSession8, filmSession9, filmSession10, filmSession11, filmSession12,
        		filmSession13, filmSession14, filmSession15, filmSession16));
    }
}

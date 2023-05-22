package com.api.project.model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_sessions")
public class FilmSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    
    private LocalDate calendarDate;
    private String weekDay;
//    private transient DayOfWeek weekDay;
    private LocalTime startHour;
    private LocalTime endHour;
    private String cinema;
    private String city;
    
    public FilmSession() {}
    
    public FilmSession(Long id, Film film, LocalDate calendarDate, String weekDay,
    		LocalTime startHour, LocalTime endHour, String cinema, String city) {
    	this.id = id;
    	this.film = film;
    	this.calendarDate = calendarDate;
    	this.weekDay = weekDay;
    	this.startHour = startHour;
    	this.endHour = endHour;
    	this.cinema = cinema;
    	this.city = city;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Film getFilm() {
    	return this.film;
    }
    
    public void setFilm(Film film) {
    	this.film = film;
    }
    
    public LocalDate getCalendarDate() {
    	return this.calendarDate;
    }

    public void setCalendarDate(LocalDate calendarDate) {
        this.calendarDate = calendarDate;
        DayOfWeek weekDay = calendarDate.getDayOfWeek();
        this.weekDay = weekDay.name();
    }

    public String getWeekDay() {
        return this.weekDay;
    }
    
    public LocalTime getStartHour() {
    	return this.startHour;
    }
    
    public void setStartHour(LocalTime startHour) {
    	this.startHour = startHour;
    	setEndHour();
    }

    public void setEndHour() {
    	Long durationInMinutes = this.film.formatDuration();
        if (this.startHour != null && durationInMinutes != null) {
            this.endHour = startHour.plusMinutes(durationInMinutes);
        }
    }
    
    public LocalTime getEndHour() {
    	return this.endHour;
    }
    
    public void setEndHour(LocalTime endHour) {
    	this.endHour = endHour;
    }
    
    public String getCinema() {
    	return this.cinema;
    }
    
    public void setCinema(String cinema) {
    	this.cinema = cinema;
    }
    
    public String getCity() {
    	return this.city;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
}
package com.api.project.model;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FILMS")
public class FilmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Mapping
    private Long userId;
    
    private String title;
    private Double duration;
    private String language;
    private String subtitles;
    private String director;
    private String actors;
    private Integer minimumAge;
    private LocalDate startDate;
    private LocalDate endDate;

    public FilmModel() {}

    public FilmModel(Long id, Long userId, String title, Double duration, String language, String subtitles, String director,
            String actors, Integer minimumAge, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.subtitles = subtitles;
        this.director = director;
        this.actors = actors;
        this.minimumAge = minimumAge;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String toJson(List<String> myList) {
    	String myJson = new Gson().toJson(myList);
    	return myJson;
    }
    
    public List<String> toList(String myJson) {
    	List<String> myList = new Gson().fromJson(myJson, new TypeToken<List<String>>() {}.getType());
    	return myList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
    	return this.userId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getDuration() {
        return this.duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Integer getMinimumAge() {
        return this.minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

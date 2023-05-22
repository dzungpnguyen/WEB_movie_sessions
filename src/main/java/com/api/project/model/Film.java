package com.api.project.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double duration;
    private String language;
    private String subtitles;
    private String director;
    private String actors;
    private Integer minimumAge;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private Uploader uploader;
    @OneToMany(mappedBy = "film")
    private Set<FilmSession> filmSessions = new HashSet<>();

    public Film() {}

    public Film(Long id, String title, Double duration, String language, String subtitles, String director,
            String actors, Integer minimumAge, LocalDate startDate, LocalDate endDate, Uploader uploader) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.subtitles = subtitles;
        this.director = director;
        this.actors = actors;
        this.minimumAge = minimumAge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.uploader = uploader;
        this.filmSessions = new HashSet<>();
    }
    
//    public String toJson(List<String> myList) {
//    	String myJson = new Gson().toJson(myList);
//    	return myJson;
//    }
//    
//    public List<String> toList(String myJson) {
//    	List<String> myList = new Gson().fromJson(myJson, new TypeToken<List<String>>() {}.getType());
//    	return myList;
//    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public Long formatDuration() {
    	return (long) (this.duration * 60);
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
    
    public Uploader getUploader() {
    	return this.uploader;
    }
    
    public void setUploader(Uploader uploader) {
    	this.uploader = uploader;
    }
    
    public Set<FilmSession> getFilmSessions() {
    	return this.filmSessions;
    }
    
    public void setFilmSessions(Set<FilmSession> filmSessions) {
    	this.filmSessions = filmSessions;
    }
}

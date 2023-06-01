package com.api.project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="uploaders")
public class Uploader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
	private String username;
    @Column(name = "password", nullable = false)	
    private String password;
    
    @OneToMany(mappedBy = "uploader")
    private List<Film> uploadedFilms = new ArrayList<>();
	
	public Uploader() {}

	public Uploader(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Film> getUploadedFilms() {
		return this.uploadedFilms;
	}
	
	public void setUploadedFilms(List<Film> uploadedFilms) {
		this.uploadedFilms = uploadedFilms;
	}
}

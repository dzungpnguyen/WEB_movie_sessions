package com.api.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.Film;
import com.api.project.model.Uploader;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {
	List<Film> findAllByUploader(Uploader uploader);
//	Optional<FilmModel> findByTitle(String title);
//	
//	List<FilmModel> findAllByUserId(Long userId);
}
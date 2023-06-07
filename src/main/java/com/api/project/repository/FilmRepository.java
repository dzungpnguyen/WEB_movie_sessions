package com.api.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.Film;
import com.api.project.model.Uploader;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {
	List<Film> findAllByUploader(Uploader uploader);
	Film findByTitle(String title);
	List<Film> findByTitleContaining(String title);
//	List<FilmModel> findAllByUploaderId(Long userId);
}
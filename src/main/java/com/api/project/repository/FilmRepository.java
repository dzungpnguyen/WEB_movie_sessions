package com.api.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.FilmModel;

@Repository
public interface FilmRepository extends CrudRepository<FilmModel, Long> {
	List<FilmModel> findAllByTitle(String title);
	Optional<FilmModel> findByTitle(String title);
	
	List<FilmModel> findAllByUserId(Long userId);
}
package com.api.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.FilmSession;

@Repository
public interface FilmSessionRepository extends CrudRepository<FilmSession, Long> {
	List<FilmSession> findAllByCity(String city);
}
package com.api.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.Uploader;

@Repository
public interface UploaderRepository extends CrudRepository<Uploader, Long>{
//	Optional<Uploader> findByUsername(String username);
}
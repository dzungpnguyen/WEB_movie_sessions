package com.api.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.project.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long>{
	Optional<UserModel> findByUsername(String username);
}
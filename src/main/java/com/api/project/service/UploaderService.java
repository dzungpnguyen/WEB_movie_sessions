package com.api.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.Uploader;
import com.api.project.repository.UploaderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UploaderService {
	@Autowired
	private UploaderRepository userRepository;
	
//	// Find uploader by id
//	public UploaderModel findUploaderById(Long id) {
//		Optional<UploaderModel> uploader = userRepository.findById(id);
//    	if (uploader.isEmpty()) {
//    		throw new EntityNotFoundException("No user found with the id: " + id.toString());
//    	}
//    	return uploader.orElse(null);
//	}
}

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
	private UploaderRepository uploaderRepository;
	
	public Uploader findUploaderByUsername(String username) {
		Optional<Uploader> uploader = uploaderRepository.findByUsername(username);
//		if (uploader.isEmpty()) {
//			throw new EntityNotFoundException("No user found with the username: " + username);
//		}
		return uploader.orElse(null);
	}
	
	public Uploader saveUploader(Uploader newUploader) {
		return uploaderRepository.save(newUploader);
	}
}

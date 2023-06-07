package com.api.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.Uploader;
import com.api.project.repository.UploaderRepository;

@Service
public class UploaderService {
	@Autowired
	private UploaderRepository uploaderRepository;
	
	// Find uploader by username
	public Uploader findUploaderByUsername(String username) {
		Optional<Uploader> uploader = uploaderRepository.findByUsername(username);
		return uploader.orElse(null);
	}
	
	// Save an uploader
	public Uploader saveUploader(Uploader newUploader) {
		return uploaderRepository.save(newUploader);
	}
}

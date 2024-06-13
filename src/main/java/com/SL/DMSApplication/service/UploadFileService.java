package com.SL.DMSApplication.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SL.DMSApplication.model.UploadFile;

@Service
public interface UploadFileService {

	
	 
	void saveFile(MultipartFile file) throws IOException;

	List<UploadFile> getUploadedFiles();

	Optional<UploadFile> getFileId(Long fileId);
	
	 public String getUploadDir();
}

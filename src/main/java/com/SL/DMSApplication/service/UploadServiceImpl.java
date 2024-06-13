package com.SL.DMSApplication.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SL.DMSApplication.model.UploadFile;
import com.SL.DMSApplication.repository.UploadFileRepository;

@Service
public class UploadServiceImpl implements UploadFileService {
	
	@Value("${upload.dir}")
    private String uploadDir;

	@Autowired
    private  UploadFileRepository uploadFileRepository;

  
    @Override
    public void saveFile(MultipartFile file) throws IOException {
        // Save the file on the local file system
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(path, bytes);

        // Save file information in the database
        UploadFile uploadedFile = new UploadFile();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadFileRepository.save(uploadedFile);
    }


	@Override
	public List<UploadFile> getUploadedFiles() {
		return uploadFileRepository.findAll();
	}


	@Override
	public Optional<UploadFile> getFileId(Long fileId) {
		return uploadFileRepository.findById(fileId);
	}


	@Override
	public String getUploadDir() {
		return uploadDir;
	}

	
}

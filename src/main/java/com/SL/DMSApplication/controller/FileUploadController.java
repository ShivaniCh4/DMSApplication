package com.SL.DMSApplication.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.SL.DMSApplication.model.UploadFile;
import com.SL.DMSApplication.service.UploadFileService;

@Controller
public class FileUploadController {

	  @Value("${upload.dir}")
	    private String uploadDir;

	    @Autowired
	   private UploadFileService uploadFileService;

		@GetMapping("/")
		public String home()
		{
			return "index";
		}
		
		@PostMapping("/upload")
	    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a file to upload");
	            return "index";
	        }

	        try {
	        	uploadFileService.saveFile(file);
	            model.addAttribute("message", "File uploaded successfully: " + file.getOriginalFilename());
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
	        }

	        return "index";
	    }
		
		
		 @PostMapping("/getFile")
		    public String getFileById(@RequestParam("fileId") Long fileId, Model model) {
		        Optional<UploadFile> file = uploadFileService.getFileId(fileId);
		        if (file.isPresent()) {
		            UploadFile uploadedFile = file.get();
		            model.addAttribute("fileDetail", uploadedFile);

		            String filePath = uploadFileService.getUploadDir() + uploadedFile.getFileName();
		            model.addAttribute("filePath", filePath);
		        } else {
		            model.addAttribute("message", "File not found with ID: " + fileId);
		        }

		        return "index";
		    }
}

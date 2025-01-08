package com.spring.ecmmvc.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "C:\\Users\\User\\trainingJava\\springproject\\07-MVC\\ecm-mvc\\src\\main\\resources\\static\\images";

    public void saveFileToResources(MultipartFile file) {
        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Use createDirectories to create parent directories if needed
            }

            // Resolve the file path within the upload directory
            Path filePath = uploadPath.resolve(file.getOriginalFilename());

            // Save the file to the specified path
            file.transferTo(filePath.toFile());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }
    }
}

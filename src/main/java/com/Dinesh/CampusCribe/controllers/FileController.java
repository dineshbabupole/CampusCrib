package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Value("${app.image.path}")
    private String path;

    @PostMapping("/upload")
    public String upload(@RequestPart MultipartFile file) throws IOException {
        return fileService.uploadFile(path,file);
    }
    @GetMapping("/get/{fileName}")
    public void getFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream file=fileService.getFile(fileName,path);
        String contentType = Files.probeContentType(Paths.get(path, fileName));
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        response.setContentType(contentType);
        StreamUtils.copy(file, response.getOutputStream());
    }
}

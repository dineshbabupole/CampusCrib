package com.Dinesh.CampusCribe.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import com.Dinesh.CampusCribe.Exceptions.FileAlreadyExistsException;
import com.Dinesh.CampusCribe.Exceptions.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {


    public String uploadFile(String path, MultipartFile file) throws IOException {
        if(Files.exists(Paths.get(path+File.separator+file.getOriginalFilename())))
            throw new FileAlreadyExistsException("file already Exists");
        String fileName=file.getOriginalFilename();
        String filePath=path+ File.separator+fileName;
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(),Paths.get(filePath));
     return fileName;
    }

    public InputStream getFile(String fileName,String path) throws java.io.FileNotFoundException {
        String filePath=path+File.separator+fileName;
        if(!Files.exists(Paths.get(filePath)))throw new FileNotFoundException("file is not found");
        return new FileInputStream(filePath);
    }
    public String dltFile(String fileName, String path) throws java.io.FileNotFoundException {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new java.io.FileNotFoundException("File not found: " + filePath);
        }

        if (file.delete()) {
            return "File deleted successfully: " + fileName;
        } else {
            throw new RuntimeException("Failed to delete file: " + fileName);
        }
    }

}

package com.files.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadImage(String path, MultipartFile file) throws Exception;
    String deleteFile(String path, MultipartFile file) throws Exception;
    public String[] getFile();
}

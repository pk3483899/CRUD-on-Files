package com.files.controller;


import com.files.payload.FileResponse;
import com.files.service.FileService;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")//comming from application properties line no 6.
    private String path;
    @PostMapping("/upload")
        public ResponseEntity<FileResponse > fileUpload(
                @RequestParam("image") MultipartFile image  // have to assign as a key
                ){
        String fileName=null;
        try {
             fileName = fileService.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Image  is not uploaded !!"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new FileResponse(fileName,"File  is successfully uploaded"), HttpStatus.OK);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<FileResponse > fileDelete(
            @RequestParam("image") MultipartFile image
    ) {
        String fileName = null;
        try {
            fileName = fileService.deleteFile(path, image);
        } catch (
                IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Image  is not deleted !!"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new FileResponse(fileName, "File  is Deleted"), HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<String[]> getFiles(){
//        File f=new File("C:\\Users\\pk348\\OneDrive\\OneDrive\\Desktop\\Numetry\\crudwithfiles\\crudwithfiles\\images\\");
//        String[] list = f.list();
        String[] file = fileService.getFile();
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
}

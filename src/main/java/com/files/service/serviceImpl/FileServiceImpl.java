package com.files.service.serviceImpl;

import com.files.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{
        //file name
        String name = file.getOriginalFilename();

        //full path
        String filePath = path + File.separator + name;

        //create folder if not created
        File f = new File(path);
        if (!f.exists()){
          f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public String deleteFile(String path, MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();

        //full path
        String filePath = path + File.separator + name;

        //create folder if not created
        File f = new File(filePath);
        if (f.exists()){
            f.delete();
        }
        return name;
    }

    @Override
    public String[] getFile() {
        File f=new File("C:\\Users\\pk348\\OneDrive\\OneDrive\\Desktop\\Numetry\\crudwithfiles\\crudwithfiles\\images\\");
        String[] list = f.list();
        return list;
    }

//    public static ResponseEntity<String[]> getFiles(){
//        File f=new File("C:\\Users\\pk348\\OneDrive\\OneDrive\\Desktop\\Numetry\\crudwithfiles\\crudwithfiles\\images\\");
//        String[] list = f.list();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

}

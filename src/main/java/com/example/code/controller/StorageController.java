package com.example.code.controller;

import com.example.code.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/photo")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
        return new ResponseEntity<>(storageService.uploadPhoto(file), HttpStatus.OK);
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String filename){
        byte[] data = storageService.downloadFile(filename);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename){
        return new ResponseEntity<>(storageService.deleteFile(filename), HttpStatus.OK);
    }

}

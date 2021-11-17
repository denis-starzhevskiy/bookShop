package com.example.code.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    private Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadPhoto(MultipartFile file){
        File fileObj = multipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + file.getOriginalFilename();
    }

    public byte[] downloadFile(String filename){
        S3ObjectInputStream inputStream;
        try (S3Object s3Object = s3Client.getObject(bucketName, filename)) {
            inputStream = s3Object.getObjectContent();
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        }catch (IOException e) {
            return null;
        }
    }

    public String deleteFile(String filename){
        s3Client.deleteObject(bucketName, filename);
        return filename + " removed";
    }

    private File multipartFileToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }catch (IOException io){
            logger.error("Error converting multipartfile to file");
        }
        return convertedFile;
    }

}

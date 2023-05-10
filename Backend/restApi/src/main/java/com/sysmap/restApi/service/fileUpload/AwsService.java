package com.sysmap.restApi.service.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.util.Objects;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class AwsService {

    @Autowired
    private AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile, String fileName) throws Exception {
        var fileUri = "";

        try {
            var fileConverted = convertMultipartfileToFile(multipartFile);

            amazonS3.putObject(new PutObjectRequest("bucket-apirest", fileName, fileConverted)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            fileUri = "http://s3.localhost.localstack.cloud:4566" + "/" + "bucket-apirest" + "/" + fileName;

            fileConverted.delete();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return fileUri;
    }

    private File convertMultipartfileToFile(MultipartFile multipartFile) throws IOException {
        var convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        var fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();

        return convFile;
    }
}

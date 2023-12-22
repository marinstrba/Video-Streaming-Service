package com.video.streaming.service.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {
    private final AmazonS3 awsS3Client;
    private static final String S3NAME = "com.marinstrba";
    @Override
    public String uploadFile (MultipartFile file)
    {
       var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
       var key = UUID.randomUUID().toString() + "." + filenameExtension;
       var metadata = new ObjectMetadata();
       metadata.setContentLength(file.getSize());
       metadata.setContentType(file.getContentType());

       try{
           awsS3Client.putObject(S3NAME, key, file.getInputStream(), metadata);
       } catch (IOException ioException)
       {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                   "An exception occurred while uploading the file.");
       }

       awsS3Client.setObjectAcl(S3NAME, key, CannedAccessControlList.PublicRead);
        return constructS3Url(key);
    }
    private String constructS3Url(String key) {
        URL url = awsS3Client.getUrl(S3Service.S3NAME, key);
        return url.toString();
    }
}
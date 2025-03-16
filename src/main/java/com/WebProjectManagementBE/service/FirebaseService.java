package com.WebProjectManagementBE.service;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseService {


    public boolean uploadFile(String fileName, MultipartFile file) {
        Bucket bucket = StorageClient.getInstance().bucket();
        try {
            bucket.create(fileName, file.getBytes());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(fileName);
        if (blob == null) {
            return false;
        }
        else {
            blob.delete();
            return true;
        }
    }

    public String generateFileName(MultipartFile file) {
        String original = file.getOriginalFilename();
        if (original == null) {
            return null;
        }
        String[] temp = original.split("\\.");
        String extension = temp[temp.length - 1];
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger number = new BigInteger(1, md.digest(original.getBytes(StandardCharsets.UTF_8)));
        String result = number.toString(16);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        result += formatter.format(cld.getTime()) + "." + extension;
        return result;
    }
}
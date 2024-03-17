package com.qcpg.backendqcpg.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CpgService {
    private final RestTemplate restTemplate;

    @Value("${custom.cpg.url}")
    private String cpgUrl;

    @Value("${custom.cpg.volumes}")
    private String cpgVolumes;

    public CpgService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String executeCpgCommand(String command) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(command, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(cpgUrl + "/execute", request, String.class);

        return response.getBody();
    }

    public String uploadFiles(MultipartFile[] files) throws IOException {
        String uploadPath;
        if (files.length > 1) {
            String folderName = "upload_" + System.currentTimeMillis();
            Path folderPath = Paths.get(cpgVolumes).resolve(folderName);
            Files.createDirectories(folderPath);
            uploadPath = folderName;

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Path destinationFile = folderPath.resolve(Paths.get(file.getOriginalFilename()))
                            .normalize().toAbsolutePath();
                    file.transferTo(destinationFile);
                }
            }
        } else if (files.length == 1) {
            MultipartFile file = files[0];
            if (!file.isEmpty()) {
                Path destinationFile = Paths.get(cpgVolumes).resolve(Paths.get(file.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                file.transferTo(destinationFile);
                uploadPath = file.getOriginalFilename();
            } else {
                uploadPath = "";
            }
        } else {
            uploadPath = "";
        }
        return uploadPath;
    }
}

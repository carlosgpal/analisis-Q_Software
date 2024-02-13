package com.qcpg.backendqcpg.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CpgService {
    private final RestTemplate restTemplate;

    @Value("${custom.cpg.url}")
    private String cpgUrl;

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
}

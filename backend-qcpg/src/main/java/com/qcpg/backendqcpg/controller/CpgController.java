package com.qcpg.backendqcpg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcpg.backendqcpg.service.CpgService;

@RestController
@RequestMapping("/cpg")
public class CpgController {

    @Autowired
    private CpgService cpgService;

    @Value("${spring.neo4j.authentication.username}")
    private String neo4juser;

    @Value("${spring.neo4j.authentication.password}")
    private String neo4jpass;

    public static class CommandRequest {
        private String command;

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody CommandRequest commandRequest) {
        try {
            Map<String, String> commandMap = new HashMap<>();
            commandMap.put("command", "cpg-neo4j/build/install/cpg-neo4j/bin/cpg-neo4j --host=neo4j --user=" + neo4juser
                    + " --password=" + neo4jpass + " /app/uploads/" + commandRequest.getCommand());

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonCommand = objectMapper.writeValueAsString(commandMap);

            String output = cpgService.executeCpgCommand(jsonCommand);
            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Execute processing error: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("files") MultipartFile[] files) {
        try {
            String uploadPath = cpgService.uploadFiles(files);
            return ResponseEntity.ok().body(Map.of("path", uploadPath));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading files", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
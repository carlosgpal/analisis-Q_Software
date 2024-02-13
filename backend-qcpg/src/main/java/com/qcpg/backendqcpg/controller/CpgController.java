package com.qcpg.backendqcpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qcpg.backendqcpg.service.CpgService;

@RestController
@RequestMapping("/cpg")
public class CpgController {

    @Autowired
    private CpgService cpgService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody String command) {
        String output = cpgService.executeCpgCommand(command);
        return ResponseEntity.ok(output);
    }
}
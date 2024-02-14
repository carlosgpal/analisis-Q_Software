package com.qcpg.backendqcpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qcpg.backendqcpg.service.Neo4jService;

@RestController
@RequestMapping("/neo4j")
public class Neo4jController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("/nodes/{id}")
    public ResponseEntity<?> getNode(@PathVariable Long id) {
        return ResponseEntity.ok(neo4jService.getNodePropertiesById(id));
    }

    @GetMapping("/nodes")
    public ResponseEntity<?> getAllNodes() {
        return ResponseEntity.ok(neo4jService.getAllNodes());
    }
}

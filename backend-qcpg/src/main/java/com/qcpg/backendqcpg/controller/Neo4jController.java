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

    @GetMapping("/entireGraph")
    public ResponseEntity<?> getEntireGraph() {
        return ResponseEntity.ok(neo4jService.getGraph());
    }

    @GetMapping("/ast")
    public ResponseEntity<?> getAstGraph() {
        return ResponseEntity.ok(neo4jService.getAst());
    }

    @GetMapping("/cfg")
    public ResponseEntity<?> getCfgGraph() {
        return ResponseEntity.ok(neo4jService.getCfg());
    }

    @GetMapping("/pdg")
    public ResponseEntity<?> getPdgGraph() {
        return ResponseEntity.ok(neo4jService.getPdg());
    }
}

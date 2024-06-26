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

    @GetMapping("/numQubits")
    public ResponseEntity<?> getNumQubits() {
        return ResponseEntity.ok(neo4jService.getNumQubits());
    }

    @GetMapping("/numClassicBits")
    public ResponseEntity<?> getNumClassicBits() {
        return ResponseEntity.ok(neo4jService.getNumClassicBits());
    }

    @GetMapping("/mappingBits")
    public ResponseEntity<?> getMappingBits() {
        return ResponseEntity.ok(neo4jService.getMappingBits());
    }

    @GetMapping("/numGates")
    public ResponseEntity<?> getNumGates() {
        return ResponseEntity.ok(neo4jService.getNumGates());
    }

    @GetMapping("/mappingGates")
    public ResponseEntity<?> getMappingGates() {
        return ResponseEntity.ok(neo4jService.getMappingGates());
    }

    @GetMapping("/numMeasures")
    public ResponseEntity<?> getNumMeasures() {
        return ResponseEntity.ok(neo4jService.getNumMeasures());
    }

    @GetMapping("/mappingMeasures")
    public ResponseEntity<?> getMappingMeasures() {
        return ResponseEntity.ok(neo4jService.getMappingMeasures());
    }

    @GetMapping("/moreInfoQubitsBits")
    public ResponseEntity<?> getMoreInfoQubitsBits() {
        return ResponseEntity.ok(neo4jService.getMoreInfoQubitsBits());
    }

    @GetMapping("/moreInfoQubitsGates")
    public ResponseEntity<?> getMoreInfoQubitsGates() {
        return ResponseEntity.ok(neo4jService.getMoreInfoQubitsGates());
    }

    @GetMapping("/moreInfoQubitsMeasures")
    public ResponseEntity<?> getMoreInfoQubitsMeasures() {
        return ResponseEntity.ok(neo4jService.getMoreInfoQubitsMeasures());
    }

    @GetMapping("/getFiles")
    public ResponseEntity<?> getFiles() {
        return ResponseEntity.ok(neo4jService.getFiles());
    }

    @GetMapping("/getNumPatterns")
    public ResponseEntity<?> getNumPatterns() {
        return ResponseEntity.ok(neo4jService.getNumPatterns());
    }

    @GetMapping("/statePreparation")
    public ResponseEntity<?> getStatePreparation() {
        return ResponseEntity.ok(neo4jService.getStatePreparation());
    }

    @GetMapping("/uniformSuperposition")
    public ResponseEntity<?> getUniformSuperposition() {
        return ResponseEntity.ok(neo4jService.getUniformSuperposition());
    }

    @GetMapping("/creatingEntanglement")
    public ResponseEntity<?> getCreatingEntanglement() {
        return ResponseEntity.ok(neo4jService.getCreatingEntanglement());
    }

    @GetMapping("/moreInfoCreatingEntanglement")
    public ResponseEntity<?> getMoreInfoCreatingEntanglement() {
        return ResponseEntity.ok(neo4jService.getMoreInfoCreatingEntanglement());
    }
}

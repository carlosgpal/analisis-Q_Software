package com.qcpg.backendqcpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcpg.backendqcpg.dto.GenericNodeDTO;
import com.qcpg.backendqcpg.repository.GenericNodeRepository;

import java.util.List;

@Service
public class Neo4jService {

    @Autowired
    private GenericNodeRepository repository;

    public List<GenericNodeDTO> getNodePropertiesById(Long id) {
        return repository.findNodeById(id);
    }

    public List<GenericNodeDTO> getAllNodes() {
        return repository.findAllNodes();
    }
}

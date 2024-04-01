package com.qcpg.backendqcpg.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.qcpg.backendqcpg.dto.GenericGraphDTO;
import com.qcpg.backendqcpg.dto.GenericNodeDTO;
import com.qcpg.backendqcpg.dto.GenericRelationshipDTO;
import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.model.GenericRelationship;
import com.qcpg.backendqcpg.repository.GenericNodeRepository;

@Service
public class Neo4jService {

        @Autowired
        private GenericNodeRepository nodeRepository;

        @Autowired
        private ModelMapper modelMapper;

        public GenericGraphDTO getGraph() {
                List<GenericNode> nodes = nodeRepository.getAllNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getAllRelationships(nodeIds);

                List<GenericNodeDTO> nodeDTOs = nodes.stream()
                                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                                .collect(Collectors.toList());

                List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                                .collect(Collectors.toList());

                edgeDTOs.forEach(edgeDTO -> {
                        edgeDTO.setSource(edgeDTO.getSource().toString());
                        edgeDTO.setTarget(edgeDTO.getTarget().toString());
                });

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "entireGraph");
        }

        public GenericGraphDTO getAst() {
                List<GenericNode> nodes = nodeRepository.getAstNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getAstRelationships(nodeIds);

                List<GenericNodeDTO> nodeDTOs = nodes.stream()
                                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                                .collect(Collectors.toList());

                List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                                .collect(Collectors.toList());

                edgeDTOs.forEach(edgeDTO -> {
                        edgeDTO.setSource(edgeDTO.getSource().toString());
                        edgeDTO.setTarget(edgeDTO.getTarget().toString());
                });

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "ast");
        }

        public GenericGraphDTO getCfg() {
                List<GenericNode> nodes = nodeRepository.getCfgNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getCfgRelationships(nodeIds);

                List<GenericNodeDTO> nodeDTOs = nodes.stream()
                                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                                .collect(Collectors.toList());

                List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                                .collect(Collectors.toList());

                edgeDTOs.forEach(edgeDTO -> {
                        edgeDTO.setSource(edgeDTO.getSource().toString());
                        edgeDTO.setTarget(edgeDTO.getTarget().toString());
                });

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "cfg");
        }

        public GenericGraphDTO getPdg() {
                List<GenericNode> nodes = nodeRepository.getPdgNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getPdgRelationships(nodeIds);

                List<GenericNodeDTO> nodeDTOs = nodes.stream()
                                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                                .collect(Collectors.toList());

                List<GenericRelationshipDTO> edgeDTOs = edges.stream()
                                .map(edge -> modelMapper.map(edge, GenericRelationshipDTO.class))
                                .collect(Collectors.toList());

                edgeDTOs.forEach(edgeDTO -> {
                        edgeDTO.setSource(edgeDTO.getSource().toString());
                        edgeDTO.setTarget(edgeDTO.getTarget().toString());
                });

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "pdg");
        }
}

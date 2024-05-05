package com.qcpg.backendqcpg.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
                // Conjuntos para almacenar nodos y aristas únicos
                List<GenericNode> allNodes = new ArrayList<>();
                List<GenericRelationship> allEdges = new ArrayList<>();

                List<GenericNode> astNodes = nodeRepository.getAstNodes();
                List<Long> astNodeIds = astNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
                allNodes.addAll(astNodes);
                allEdges.addAll(nodeRepository.getAstRelationships(astNodeIds));

                List<GenericNode> cfgNodes = nodeRepository.getCfgNodes();
                List<Long> cfgNodeIds = cfgNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
                allNodes.addAll(cfgNodes);
                allEdges.addAll(nodeRepository.getCfgRelationships(cfgNodeIds));

                List<GenericNode> pdgNodes = nodeRepository.getPdgNodes();
                List<Long> pdgNodeIds = pdgNodes.stream().map(GenericNode::getId).collect(Collectors.toList());
                allNodes.addAll(pdgNodes);
                allEdges.addAll(nodeRepository.getPdgRelationships(pdgNodeIds));

                List<GenericNodeDTO> nodeDTOs = allNodes.stream()
                                .distinct()
                                .map(node -> modelMapper.map(node, GenericNodeDTO.class))
                                .collect(Collectors.toList());

                List<GenericRelationshipDTO> edgeDTOs = allEdges.stream()
                                .distinct()
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
                System.out.println(nodeIds);
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

        public int getNumQubits() {
                List<GenericNode> nodes = nodeRepository.getNumQubits();
                return nodes.size();
        }

        public int getNumClassicBits() {
                List<GenericNode> nodes = nodeRepository.getNumClassicBits();
                return nodes.size();
        }

        public GenericGraphDTO getMappingBits() {
                List<GenericNode> nodes = nodeRepository.getMappingBitsNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getMappingBitsRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "mappingBits");
        }

        public int getNumGates() {
                List<GenericNode> nodes = nodeRepository.getNumGates();
                return nodes.size();
        }

        public GenericGraphDTO getMappingGates() {
                List<GenericNode> nodes = nodeRepository.getMappingGatesNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getMappingGatesRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "mappingGates");
        }

        public int getNumMeasures() {
                List<GenericNode> nodes = nodeRepository.getNumMeasures();
                return nodes.size() / 2;
        }

        public GenericGraphDTO getMappingMeasures() {
                List<GenericNode> nodes = nodeRepository.getMappingMeasuresNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getMappingMeasuresRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "mappingMeasures");
        }
}

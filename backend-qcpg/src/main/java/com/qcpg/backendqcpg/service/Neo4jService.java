package com.qcpg.backendqcpg.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.qcpg.backendqcpg.dto.BitsResponseDTO;
import com.qcpg.backendqcpg.dto.EntanglementDTO;
import com.qcpg.backendqcpg.dto.FilesResponseDTO;
import com.qcpg.backendqcpg.dto.GatesResponseDTO;
import com.qcpg.backendqcpg.dto.GenericGraphDTO;
import com.qcpg.backendqcpg.dto.GenericNodeDTO;
import com.qcpg.backendqcpg.dto.GenericRelationshipDTO;
import com.qcpg.backendqcpg.dto.MeasuresResponseDTO;
import com.qcpg.backendqcpg.dto.NumPatternsDTO;
import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.model.GenericRelationship;
import com.qcpg.backendqcpg.repository.GenericNodeRepository;

@Service
public class Neo4jService {

        @Value("${custom.cpg.volumes}")
        private String cpgVolumes;

        @Autowired
        private GenericNodeRepository nodeRepository;

        @Autowired
        private ModelMapper modelMapper;

        public GenericGraphDTO getGraph() {
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

        public List<BitsResponseDTO> getMoreInfoQubitsBits() {
                GenericGraphDTO graph = getMappingBits();

                Map<String, GenericNodeDTO> nodesById = graph.getNodes().stream()
                                .collect(Collectors.toMap(GenericNodeDTO::getId, node -> node));

                Map<String, Set<String>> qubitToBits = new HashMap<>();

                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        String sourceId = edge.getSource();
                        String targetId = edge.getTarget();

                        if (nodesById.get(sourceId).getLabels().contains("QuantumBitReference") &&
                                        nodesById.get(targetId).getLabels().contains("ClassicBitReference")) {
                                Set<String> bits = qubitToBits.computeIfAbsent(nodesById.get(sourceId).getName(),
                                                k -> new HashSet<>());
                                bits.add(nodesById.get(targetId).getName());
                        }
                }

                List<BitsResponseDTO> response = qubitToBits.entrySet().stream()
                                .sorted(Map.Entry.comparingByKey())
                                .map(entry -> new BitsResponseDTO(entry.getKey(), new ArrayList<>(entry.getValue())))
                                .collect(Collectors.toList());

                return response;
        }

        public List<GatesResponseDTO> getMoreInfoQubitsGates() {
                GenericGraphDTO graph = getMappingGates();

                Map<String, GenericNodeDTO> nodesById = graph.getNodes().stream()
                                .collect(Collectors.toMap(GenericNodeDTO::getId, node -> node));

                Map<String, GatesResponseDTO> gatesCount = new TreeMap<>();
                int totalX = 0, totalY = 0, totalZ = 0, totalH = 0, totalCX = 0, totalT = 0, totalS = 0, totalTDG = 0,
                                totalSDG = 0;

                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        String sourceId = edge.getSource();
                        String targetId = edge.getTarget();

                        if (nodesById.get(sourceId).getLabels().contains("QuantumBit")) {
                                GenericNodeDTO gateNode = nodesById.get(targetId);
                                if (gateNode != null && gateNode.getLabels().stream()
                                                .anyMatch(label -> label.contains("QuantumGate"))) {
                                        GatesResponseDTO count = gatesCount.computeIfAbsent(
                                                        nodesById.get(sourceId).getName(),
                                                        k -> new GatesResponseDTO(k, 0, 0, 0, 0, 0, 0, 0, 0, 0));

                                        if (gateNode.getLabels().contains("QuantumGateX")) {
                                                count.setGatesX(count.getGatesX() + 1);
                                                totalX++;
                                        } else if (gateNode.getLabels().contains("QuantumGateY")) {
                                                count.setGatesY(count.getGatesY() + 1);
                                                totalY++;
                                        } else if (gateNode.getLabels().contains("QuantumGateZ")) {
                                                count.setGatesZ(count.getGatesZ() + 1);
                                                totalZ++;
                                        } else if (gateNode.getLabels().contains("QuantumGateH")) {
                                                count.setGatesH(count.getGatesH() + 1);
                                                totalH++;
                                        } else if (gateNode.getLabels().contains("QuantumGateCX")) {
                                                count.setGatesCX(count.getGatesCX() + 1);
                                                totalCX++;
                                        } else if (gateNode.getLabels().contains("QuantumGateT")) {
                                                count.setGatesT(count.getGatesT() + 1);
                                                totalT++;
                                        } else if (gateNode.getLabels().contains("QuantumGateS")) {
                                                count.setGatesS(count.getGatesS() + 1);
                                                totalS++;
                                        } else if (gateNode.getLabels().contains("QuantumGateTdg")) {
                                                count.setGatesTDG(count.getGatesTDG() + 1);
                                                totalTDG++;
                                        } else if (gateNode.getLabels().contains("QuantumGateSdg")) {
                                                count.setGatesSDG(count.getGatesSDG() + 1);
                                                totalSDG++;
                                        }
                                }
                        }
                }

                gatesCount.put("ALL",
                                new GatesResponseDTO("TODAS", totalX, totalY, totalZ, totalH, totalCX / 2, totalT,
                                                totalS, totalTDG,
                                                totalSDG));

                return new ArrayList<>(gatesCount.values());
        }

        public List<MeasuresResponseDTO> getMoreInfoQubitsMeasures() {
                GenericGraphDTO graph = getMappingMeasures();

                Map<String, GenericNodeDTO> nodesById = graph.getNodes().stream()
                                .collect(Collectors.toMap(GenericNodeDTO::getId, node -> node));

                List<MeasuresResponseDTO> measuresList = new ArrayList<>();
                int idCounter = 1;

                for (GenericNodeDTO node : graph.getNodes()) {
                        if (node.getLabels().contains("CallExpression") && node.getName().contains("measure")) {
                                String fileType = node.getFile().endsWith(".py") ? "python" : "qasm";
                                List<String> qubits = new ArrayList<>();
                                List<String> classicBits = new ArrayList<>();

                                for (GenericRelationshipDTO edge : graph.getEdges()) {
                                        if (edge.getSource().equals(node.getId())) {
                                                GenericNodeDTO targetNode = nodesById.get(edge.getTarget());
                                                if (fileType.equals("python")) {
                                                        if (targetNode.getLabels()
                                                                        .contains("InitializerListExpression")) {
                                                                List<String> items = parseInitializerList(
                                                                                targetNode.getCode());
                                                                if (qubits.isEmpty()) {
                                                                        qubits = items;
                                                                } else {
                                                                        classicBits = items;
                                                                }
                                                        }
                                                } else {
                                                        if (targetNode.getName().startsWith("q[")) {
                                                                qubits.add(targetNode.getName());
                                                        } else if (targetNode.getName().startsWith("c[")) {
                                                                classicBits.add(targetNode.getName());
                                                        }
                                                }
                                        }
                                }

                                for (int i = 0; i < qubits.size() && i < classicBits.size(); i++) {
                                        measuresList.add(new MeasuresResponseDTO(String.valueOf(idCounter++),
                                                        qubits.get(i), classicBits.get(i)));
                                }
                        }
                }

                return measuresList.stream()
                                .sorted(Comparator.comparing(m -> Integer
                                                .parseInt(m.getQubit().substring(2, m.getQubit().length() - 1))))
                                .collect(Collectors.toList());
        }

        private List<String> parseInitializerList(String code) {
                List<String> results = new ArrayList<>();
                String stripped = code.replaceAll("[\\[\\]]", "");
                String[] parts = stripped.split(",\\s*");
                for (String part : parts) {
                        results.add("q[" + part.trim() + "]");
                }
                return results;
        }

        public List<FilesResponseDTO> getFiles() {
                List<GenericNode> nodes = nodeRepository.getFiles();
                List<FilesResponseDTO> files = new ArrayList<>();

                for (GenericNode node : nodes) {
                        FilesResponseDTO dto = new FilesResponseDTO();
                        dto.setId(node.getId().longValue());

                        String fullPath = node.getFile();
                        String fileName = fullPath.substring(fullPath.lastIndexOf('/') + 1);
                        dto.setName(fileName);

                        String contentPath;

                        if (fullPath.contains("/app/uploads")) {
                                contentPath = fullPath.replace("/app/uploads", cpgVolumes);
                        } else {
                                contentPath = cpgVolumes + "/" + fullPath;
                        }

                        String fileContent = "";

                        fileContent = loadFileContent(contentPath);
                        dto.setContent(fileContent);

                        files.add(dto);
                }

                return files;
        }

        private String loadFileContent(String filePath) {
                try {
                        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
                } catch (IOException e) {
                        return "Error reading file";
                }
        }

        public NumPatternsDTO getNumPatterns() {
                int statePreparation = 0;
                int uniformSuperposition = 0;
                int creatingEntanglement = 0;

                if (!nodeRepository.getStatePreparationNodes().isEmpty()) {
                        statePreparation++;
                }

                if (!nodeRepository.getUniformSuperpositionNodes().isEmpty()) {
                        uniformSuperposition++;
                }

                GenericGraphDTO graph = getCreatingEntanglement();
                Map<String, String> nodeMappings = new HashMap<>();

                for (GenericNodeDTO node : graph.getNodes()) {
                        nodeMappings.put(node.getId(), node.getName());
                }

                Map<String, String> quBit1Targets = new HashMap<>();

                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        if (edge.getType().equals("QU_BIT_1")) {
                                quBit1Targets.put(edge.getSource(), edge.getTarget());
                        }
                }

                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        if (edge.getType().equals("QU_BIT_0")) {
                                creatingEntanglement++;
                        }
                }
                return new NumPatternsDTO(statePreparation, uniformSuperposition, creatingEntanglement);
        }

        public GenericGraphDTO getStatePreparation() {
                List<GenericNode> nodes = nodeRepository.getStatePreparationNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getStatePreparationRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "statePreparation");
        }

        public GenericGraphDTO getUniformSuperposition() {
                List<GenericNode> nodes = nodeRepository.getUniformSuperpositionNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getUniformSuperpositionRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "uniformSuperposition");
        }

        public GenericGraphDTO getCreatingEntanglement() {
                List<GenericNode> nodes = nodeRepository.getCreatingEntanglementNodes();
                List<Long> nodeIds = nodes.stream()
                                .map(GenericNode::getId)
                                .collect(Collectors.toList());
                List<GenericRelationship> edges = nodeRepository.getCreatingEntanglementRelationships(nodeIds);

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

                return new GenericGraphDTO(nodeDTOs, edgeDTOs, "creatingEntanglement");
        }

        public List<EntanglementDTO> getMoreInfoCreatingEntanglement() {
                GenericGraphDTO graph = getCreatingEntanglement();
                List<EntanglementDTO> entanglementDTOs = new ArrayList<>();
                Map<String, String> nodeMappings = new HashMap<>();

                for (GenericNodeDTO node : graph.getNodes()) {
                        nodeMappings.put(node.getId(), node.getName());
                }

                Map<String, String> quBit1Targets = new HashMap<>();

                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        if (edge.getType().equals("QU_BIT_1")) {
                                quBit1Targets.put(edge.getSource(), edge.getTarget());
                        }
                }

                long idCounter = 1;
                for (GenericRelationshipDTO edge : graph.getEdges()) {
                        if (edge.getType().equals("QU_BIT_0")) {
                                String qb1TargetId = edge.getTarget();
                                String qb1 = nodeMappings.get(qb1TargetId);

                                String qb2TargetId = quBit1Targets.get(edge.getSource());
                                String qb2 = nodeMappings.get(qb2TargetId);

                                if (qb1 != null && qb2 != null) {
                                        entanglementDTOs.add(new EntanglementDTO(idCounter++, qb1, qb2));
                                }
                        }
                }

                return entanglementDTOs;
        }
}

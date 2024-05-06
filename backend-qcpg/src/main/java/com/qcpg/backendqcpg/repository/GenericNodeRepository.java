package com.qcpg.backendqcpg.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.model.GenericRelationship;

import java.util.List;

@Repository
public interface GenericNodeRepository extends Neo4jRepository<GenericNode, Long> {

        @Query("MATCH (root)-[:AST*]->(child)\r\n" +
                        "WHERE (root:FunctionDeclaration OR root:QuantumCircuit) AND NOT root.fullName=\"measure\"\r\n"
                        +
                        "WITH root, COLLECT(DISTINCT child) AS children\r\n" +
                        "UNWIND [root] + children AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getAstNodes();

        @Query("MATCH (sourceNode)-[r:(AST|USAGE)]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds \r\n" +
                        "AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getAstRelationships(List<Long> selectedNodeIds);

        @Query("MATCH path = (start:Statement)-[:(STATEMENTS|EOG)]->(end:Statement)\r\n" +
                        "UNWIND nodes(path) AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getCfgNodes();

        @Query("MATCH (sourceNode)-[r:(STATEMENTS|EOG|CPG_NODE|CALLEE)]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getCfgRelationships(List<Long> selectedNodeIds);

        @Query("MATCH (a)-[:DFG]->(b)\r\n" +
                        "WITH a, b\r\n" +
                        "UNWIND [a, b] AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getPdgNodes();

        @Query("MATCH (sourceNode)-[r:(DFG|CPG_NODE)]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getPdgRelationships(List<Long> selectedNodeIds);

        @Query("MATCH (n) WHERE 'QuantumBit' IN labels(n) AND 'Declaration' IN labels(n) RETURN n")
        List<GenericNode> getNumQubits();

        @Query("MATCH (n) WHERE 'ClassicBit' IN labels(n) AND 'Declaration' IN labels(n) RETURN n")
        List<GenericNode> getNumClassicBits();

        @Query("MATCH path = (qn:QuantumNode)-[r:(DFG|REFERENCES)]->(cb:ClassicBitReference)\r\n" +
                        "UNWIND nodes(path) AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getMappingBitsNodes();

        @Query("MATCH (sourceNode)-[r:(DFG|REFERENCES)]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getMappingBitsRelationships(List<Long> selectedNodeIds);

        @Query("MATCH (n) WHERE 'QuantumGate' IN labels(n) RETURN n")
        List<GenericNode> getNumGates();

        @Query("MATCH (q:QuantumBit)-[:RELEVANT_FOR_GATES]->(g:QuantumGate)\r\n" +
                        "WITH q, g\r\n" +
                        "UNWIND [q, g] AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getMappingGatesNodes();

        @Query("MATCH (sourceNode)-[r:RELEVANT_FOR_GATES]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getMappingGatesRelationships(List<Long> selectedNodeIds);

        @Query("MATCH (n:Statement) WHERE (n.fullName=\"measure\" OR n.fullName=\"UNKNOWN.measure\") RETURN n")
        List<GenericNode> getNumMeasures();

        @Query("MATCH path = (m:Statement)-[r:ARGUMENTS]->(qn)\r\n" +
                        "WHERE (m.fullName=\"measure\" OR m.fullName=\"UNKNOWN.measure\")\r\n" +
                        "UNWIND nodes(path) AS node\r\n" +
                        "RETURN DISTINCT id(node) AS id, node.fullName AS fullName, node.file AS file, node.code AS code, labels(node) AS labels")
        List<GenericNode> getMappingMeasuresNodes();

        @Query("MATCH (sourceNode)-[r:ARGUMENTS]->(targetNode)\r\n" +
                        "WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds\r\n" +
                        "RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
        List<GenericRelationship> getMappingMeasuresRelationships(List<Long> selectedNodeIds);
}
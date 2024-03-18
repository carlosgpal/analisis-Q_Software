package com.qcpg.backendqcpg.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.model.GenericRelationship;

import java.util.List;

@Repository
public interface GenericNodeRepository extends Neo4jRepository<GenericNode, Long> {

    @Query("MATCH (n) WITH n.fullName AS fullName, COLLECT(n)[0] AS uniqueNode RETURN id(uniqueNode) AS id, uniqueNode.fullName AS fullName, uniqueNode.file AS file, labels(uniqueNode) AS labels")
    List<GenericNode> getAllNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getAllRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n) WHERE any(label IN labels(n) WHERE label IN ['Statement', 'Expression', 'Declaration']) WITH n.fullName AS fullName, COLLECT(n)[0] AS uniqueNode RETURN id(uniqueNode) AS id, uniqueNode.fullName AS fullName, uniqueNode.file AS file, labels(uniqueNode) AS labels")
    List<GenericNode> getAstNodes();

    @Query("MATCH (sourceNode)-[r:AST]->(targetNode) WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getAstRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n) WHERE 'Statement' IN labels(n) OR 'Expression' IN labels(n) WITH n.fullName AS fullName, COLLECT(n)[0] AS uniqueNode RETURN id(uniqueNode) AS id, uniqueNode.fullName AS fullName, uniqueNode.file AS file, labels(uniqueNode) AS labels")
    List<GenericNode> getCfgNodes();

    @Query("MATCH (sourceNode)-[r:EOG]->(targetNode) WHERE id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getCfgRelationships(List<Long> selectedNodeIds);

    @Query("MATCH (n) WHERE 'Statement' IN labels(n) OR 'Expression' IN labels(n) OR 'Declaration' IN labels(n) WITH n.fullName AS fullName, COLLECT(n)[0] AS uniqueNode RETURN id(uniqueNode) AS id, uniqueNode.fullName AS fullName, uniqueNode.file AS file, labels(uniqueNode) AS labels")
    List<GenericNode> getPdgNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) WHERE type(r) IN ['DFG', 'CONTROL_FLOW', 'DATA_DEPENDENCY'] AND id(sourceNode) IN $selectedNodeIds AND id(targetNode) IN $selectedNodeIds RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getPdgRelationships(List<Long> selectedNodeIds);
}
package com.qcpg.backendqcpg.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.model.GenericRelationship;

import java.util.List;

@Repository
public interface GenericNodeRepository extends Neo4jRepository<GenericNode, Long> {

    @Query("MATCH (n) RETURN id(n) AS id, n.fullName AS fullName, labels(n) AS labels")
    List<GenericNode> getAllNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getAllRelationships();

    @Query("MATCH (n) WHERE any(label IN labels(n) WHERE label IN ['Statement', 'Expression', 'Declaration']) RETURN id(n) AS id, n.fullName AS fullName, labels(n) AS labels")
    List<GenericNode> getAstNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) WHERE type(r) IN ['AST'] AND any(label IN labels(sourceNode) WHERE label IN ['Statement', 'Expression', 'Declaration']) RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getAstRelationships();

    @Query("MATCH (n) WHERE 'Statement' IN labels(n) OR 'Expression' IN labels(n) RETURN id(n) AS id, n.fullName AS fullName, labels(n) AS labels")
    List<GenericNode> getCfgNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) WHERE type(r) IN ['EOG'] AND ('Statement' IN labels(sourceNode) OR 'Expression' IN labels(sourceNode)) RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getCfgRelationships();

    @Query("MATCH (n) WHERE 'Statement' IN labels(n) OR 'Expression' IN labels(n) OR 'Declaration' IN labels(n) RETURN id(n) AS id, n.fullName AS fullName, labels(n) AS labels")
    List<GenericNode> getPdgNodes();

    @Query("MATCH (sourceNode)-[r]->(targetNode) WHERE type(r) IN ['DFG', 'CONTROL_FLOW', 'DATA_DEPENDENCY'] AND ('Statement' IN labels(sourceNode) OR 'Expression' IN labels(sourceNode) OR 'Declaration' IN labels(sourceNode)) RETURN id(r) AS id, type(r) AS type, id(sourceNode) AS sourceNodeId, id(targetNode) AS targetNodeId")
    List<GenericRelationship> getPdgRelationships();
}
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
}
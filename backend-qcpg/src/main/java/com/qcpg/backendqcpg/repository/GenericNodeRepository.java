package com.qcpg.backendqcpg.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qcpg.backendqcpg.dto.GenericNodeDTO;
import com.qcpg.backendqcpg.model.GenericNode;

import java.util.List;

@Repository
public interface GenericNodeRepository extends Neo4jRepository<GenericNode, Long> {

    @Query("MATCH (n) WHERE id(n) = $id RETURN n")
    List<GenericNodeDTO> findNodeById(@Param("id") Long id);

    @Query("MATCH (n) RETURN id(n) as id, labels(n) as labels")
    List<GenericNodeDTO> findAllNodes();
}
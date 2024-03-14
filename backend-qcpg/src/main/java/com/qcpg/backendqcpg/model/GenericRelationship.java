package com.qcpg.backendqcpg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.RelationshipId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class GenericRelationship {

    @RelationshipId
    private Long id;

    @Property("type")
    private String type;

    @Property("sourceNodeId")
    private Long sourceNodeId;

    @Property("targetNodeId")
    private Long targetNodeId;
}
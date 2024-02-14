package com.qcpg.backendqcpg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class GenericNode {

    @Id
    private Long id;

    @Property("properties")
    @CompositeProperty
    Map<String, Object> properties;

    @Property("labels")
    List<String> labels;

}
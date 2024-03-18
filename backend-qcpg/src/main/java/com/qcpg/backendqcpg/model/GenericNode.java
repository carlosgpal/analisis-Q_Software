package com.qcpg.backendqcpg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node("GenericNode")
public class GenericNode {

    @Id
    private Long id;

    @Property("fullName")
    private String fullName;

    @Property("file")
    private String file;

    @Property("labels")
    private List<String> labels;

}
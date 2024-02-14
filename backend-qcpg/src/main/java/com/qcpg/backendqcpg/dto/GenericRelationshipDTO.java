package com.qcpg.backendqcpg.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericRelationshipDTO {
    private Long id;
    private Map<String, Object> properties;
    private List<String> labels;
}

package com.qcpg.backendqcpg.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericGraphDTO {
    List<GenericNodeDTO> nodes;
    List<GenericRelationshipDTO> edges;
}

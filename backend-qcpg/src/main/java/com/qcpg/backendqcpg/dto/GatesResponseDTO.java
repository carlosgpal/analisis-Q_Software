package com.qcpg.backendqcpg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatesResponseDTO {
    private String id;
    private int gatesX;
    private int gatesH;
    private int gatesCX;
}

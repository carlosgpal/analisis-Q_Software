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
    private int gatesY;
    private int gatesZ;
    private int gatesH;
    private int gatesCX;
    private int gatesT;
    private int gatesS;
    private int gatesTDG;
    private int gatesSDG;
}

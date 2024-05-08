package com.qcpg.backendqcpg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesResponseDTO {
    private Long id;
    private String name;
    private String content;
}

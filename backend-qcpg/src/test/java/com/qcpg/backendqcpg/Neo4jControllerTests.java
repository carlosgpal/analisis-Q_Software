package com.qcpg.backendqcpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.qcpg.backendqcpg.controller.Neo4jController;
import com.qcpg.backendqcpg.dto.GenericGraphDTO;
import com.qcpg.backendqcpg.service.Neo4jService;

import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;

@WebMvcTest(Neo4jController.class)
public class Neo4jControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Neo4jService neo4jService;

    @Test
    public void testGetEntireGraph() throws Exception {
        GenericGraphDTO mockGraph = new GenericGraphDTO();
        Mockito.when(neo4jService.getGraph()).thenReturn(mockGraph);

        mockMvc.perform(get("/neo4j/entireGraph"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").exists());
    }
}
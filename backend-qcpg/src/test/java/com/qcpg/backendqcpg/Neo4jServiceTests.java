package com.qcpg.backendqcpg;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import com.qcpg.backendqcpg.dto.GenericGraphDTO;
import com.qcpg.backendqcpg.dto.GenericNodeDTO;
import com.qcpg.backendqcpg.model.GenericNode;
import com.qcpg.backendqcpg.repository.GenericNodeRepository;
import com.qcpg.backendqcpg.service.Neo4jService;

import java.util.Collections;

@SpringBootTest
public class Neo4jServiceTests {

    @Autowired
    private Neo4jService neo4jService;

    @MockBean
    private GenericNodeRepository nodeRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetGraph() {
        GenericNode mockNode = new GenericNode();
        Mockito.when(nodeRepository.getAstNodes()).thenReturn(Collections.singletonList(mockNode));
        Mockito.when(modelMapper.map(any(GenericNode.class), any())).thenReturn(new GenericNodeDTO());

        GenericGraphDTO result = neo4jService.getGraph();

        assertThat(result.getNodes()).hasSize(1);
        assertThat(result.getEdges()).hasSize(0);
    }

    @Test
    public void testGetAst() {
        GenericNode mockNode = new GenericNode();
        mockNode.setId(1L);
        Mockito.when(nodeRepository.getAstNodes()).thenReturn(Collections.singletonList(mockNode));
        Mockito.when(nodeRepository.getAstRelationships(any())).thenReturn(Collections.emptyList());
        Mockito.when(modelMapper.map(any(GenericNode.class), any())).thenReturn(new GenericNodeDTO());

        GenericGraphDTO result = neo4jService.getAst();

        assertThat(result.getNodes()).hasSize(1);
        assertThat(result.getEdges()).isEmpty();
    }

    @Test
    public void testGetCfg() {
        GenericNode mockNode = new GenericNode();
        mockNode.setId(1L);
        Mockito.when(nodeRepository.getCfgNodes()).thenReturn(Collections.singletonList(mockNode));
        Mockito.when(nodeRepository.getCfgRelationships(any())).thenReturn(Collections.emptyList());
        Mockito.when(modelMapper.map(any(GenericNode.class), any())).thenReturn(new GenericNodeDTO());

        GenericGraphDTO result = neo4jService.getCfg();

        assertThat(result.getNodes()).hasSize(1);
        assertThat(result.getEdges()).isEmpty();
    }

    @Test
    public void testGetPdg() {
        GenericNode mockNode = new GenericNode();
        mockNode.setId(1L);
        Mockito.when(nodeRepository.getPdgNodes()).thenReturn(Collections.singletonList(mockNode));
        Mockito.when(nodeRepository.getPdgRelationships(any())).thenReturn(Collections.emptyList());
        Mockito.when(modelMapper.map(any(GenericNode.class), any())).thenReturn(new GenericNodeDTO());

        GenericGraphDTO result = neo4jService.getPdg();

        assertThat(result.getNodes()).hasSize(1);
        assertThat(result.getEdges()).isEmpty();
    }
}
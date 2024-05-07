package com.qcpg.backendqcpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import com.qcpg.backendqcpg.service.CpgService;

@SpringBootTest
public class CpgServiceTests {

    @Autowired
    private CpgService cpgService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testExecuteCpgCommand() {
        String command = "{\"command\":\"some command\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(command, headers);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.eq(request), Mockito.eq(String.class)))
                .thenReturn(new ResponseEntity<>("Command executed successfully", HttpStatus.OK));

        String result = cpgService.executeCpgCommand(command);
        assertThat(result).isEqualTo("Command executed successfully");
    }
}
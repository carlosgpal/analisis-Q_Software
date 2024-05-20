package com.qcpg.backendqcpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import com.qcpg.backendqcpg.service.CpgService;

@SpringBootTest
@AutoConfigureMockMvc
public class CpgControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CpgService cpgService;

    @Test
    public void testExecuteCommandSuccess() throws Exception {
        String expectedOutput = "Command executed successfully";
        Mockito.when(cpgService.executeCpgCommand(Mockito.anyString())).thenReturn(expectedOutput);

        mockMvc.perform(post("/cpg/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"command\":\"testCommand\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedOutput));
    }

    @Test
    public void testExecuteCommandFailure() throws Exception {
        String expectedOutput = "Execute processing error";
        Mockito.when(cpgService.executeCpgCommand(Mockito.anyString()))
                .thenThrow(new RuntimeException("Failed to execute command"));

        mockMvc.perform(post("/cpg/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"command\":\"testCommand\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString(expectedOutput)));
    }

    @Test
    public void testFileUploadWithEmptyFilesArray() throws Exception {
        mockMvc.perform(post("/cpg/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }
}

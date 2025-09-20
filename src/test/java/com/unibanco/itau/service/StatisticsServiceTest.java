package com.unibanco.itau.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithAnonymousUser
    void checkStatistics_Return403Forbidden() throws Exception {
        mockMvc.perform(get("/api/transacao"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void checkStatistics_Return200Ok() throws Exception {
        mockMvc.perform(get("/api/transacao"))
                .andExpect(status().isOk());
    }
}

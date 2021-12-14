package com.example.demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class LogsRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @WithMockUser(roles = "ADMIN")
    @Test
    public void testWithAdminLogsRestController() throws Exception {
        this.mockMvc
                .perform(get("/api/logs/all"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testWithModeratorLogsRestController() throws Exception {
        this.mockMvc
                .perform(get("/api/logs/all"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testWithUSERLogsRestController() throws Exception {
        this.mockMvc
                .perform(get("/api/logs/all"))
                .andExpect(status().isForbidden());
    }
}

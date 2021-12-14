package com.example.demo.web;

import com.example.demo.web.exeptions.ForbiddenActionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class LogControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testWithAdminShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(get("/logs"))
                .andExpect(view().name("log-all"));
    }

    @WithMockUser(roles = "MODERATOR")
    @Test()
    public void testWithModeratorShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(get("/logs")).andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "USER")
    @Test()
    public void testWithUserShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(get("/logs")).andExpect(status().isForbidden());
    }

}

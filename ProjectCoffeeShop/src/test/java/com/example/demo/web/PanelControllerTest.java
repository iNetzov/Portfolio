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
public class PanelControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @WithMockUser(roles = "ADMIN")
    @Test
    public void testWithAdminAdminPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/admin"))
                .andExpect(view().name("index-AdminPanel"));
    }


    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testWithModeratorAdminPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/admin"))
                .andExpect(status().isForbidden());
    }


    @WithMockUser(roles = "USER")
    @Test
    public void testWithUserAdminPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/admin"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testWithAdminModeratorPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/moderator"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testWithMODERATORModeratorPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/moderator"))
                .andExpect(view().name("index-ModeratorPanel"));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testUSERModeratorPanel() throws Exception {
        this.mockMvc
                .perform(get("/panel/moderator"))
                .andExpect(status().isForbidden());
    }


}

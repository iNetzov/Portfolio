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
public class StoreControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @WithMockUser(roles = "ADMIN")
    @Test
    public void testStoresAddWithAdmin() throws Exception {
        this.mockMvc
                .perform(get("/stores/add"))
                .andExpect(view().name("stores-add"));
    }


    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testStoresAddWithModerator() throws Exception {
        this.mockMvc
                .perform(get("/stores/add"))
                .andExpect(view().name("stores-add"));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testStoresAddWithUser() throws Exception {
        this.mockMvc
                .perform(get("/stores/add"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testStoresAllWith() throws Exception {
        this.mockMvc
                .perform(get("/stores/all"))
                .andExpect(view().name("store-all"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testStoresAllWithAdmin() throws Exception {
        this.mockMvc
                .perform(get("/stores/all"))
                .andExpect(view().name("store-all"));
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testStoresAllWithModerator() throws Exception {
        this.mockMvc
                .perform(get("/stores/all"))
                .andExpect(view().name("store-all"));
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testDeleteWithModerator() throws Exception {
        this.mockMvc
                .perform(get("/stores/delete"))
                .andExpect(view().name("stores-delete"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDeleteWithAdmin() throws Exception {
        this.mockMvc
                .perform(get("/stores/delete"))
                .andExpect(view().name("stores-delete"));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testDeleteWithUser() throws Exception {
        this.mockMvc
                .perform(get("/stores/delete"))
                .andExpect(status().isForbidden());
    }
}

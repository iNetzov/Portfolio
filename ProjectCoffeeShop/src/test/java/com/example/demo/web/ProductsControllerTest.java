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
public class ProductsControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testIndexShouldReturnCorrectPage() throws Exception {
        this.mockMvc
                .perform(get("/products/all"))
                .andExpect(view().name("products-all"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testAddPageADMIN() throws Exception {
        this.mockMvc
                .perform(get("/products/add"))
                .andExpect(view().name("products-add"));
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testAddPageMODERATOR() throws Exception {
        this.mockMvc
                .perform(get("/products/add"))
                .andExpect(view().name("products-add"));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testAddPageUSER() throws Exception {
        this.mockMvc
                .perform(get("/products/add"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testDeletePageUSER() throws Exception {
        this.mockMvc
                .perform(get("/products/delete"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "MODERATOR")
    @Test
    public void testDeletePageMODERATOR() throws Exception {
        this.mockMvc
                .perform(get("/products/delete"))
                .andExpect(view().name("product-delete"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDeletePageADMIN() throws Exception {
        this.mockMvc
                .perform(get("/products/delete"))
                .andExpect(view().name("product-delete"));
    }


}

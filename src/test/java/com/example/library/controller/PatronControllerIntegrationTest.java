package com.example.library.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatronControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateAndRetrievePatron() throws Exception {
        String validPatronJson = "{ \"name\": \"Test Patron\", \"email\": \"test@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(validPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/patrons/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Patron")) 
                .andExpect(jsonPath("$.email").value("test@example.com")); 
    }

    @Test
    public void testCreatePatronWithInvalidEmailFormat() throws Exception {
        String invalidPatronJson = "{ \"name\": \"Test Patron\", \"email\": \"invalid_email\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(invalidPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Please provide a valid email address"));
    }

    @Test
    public void testCreateAndUpdatePatron() throws Exception {
        String validPatronJson = "{ \"name\": \"Test Patron\", \"email\": \"test@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(validPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String updatedPatronJson = "{ \"name\": \"Updated Test Patron\", \"email\": \"updated_test@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/patrons/{id}", 1)
                .content(updatedPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Test Patron"))
                .andExpect(jsonPath("$.email").value("updated_test@example.com"));
    }

    @Test
    public void testDeletePatron() throws Exception {
        String validPatronJson = "{ \"name\": \"Test Patron\", \"email\": \"test@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(validPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/patrons/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/patrons/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTwoPatronsAndRetrieveAllPatrons() throws Exception {
        String firstPatronJson = "{ \"name\": \"First Test Patron\", \"email\": \"first@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(firstPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String secondPatronJson = "{ \"name\": \"Second Test Patron\", \"email\": \"second@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(secondPatronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/patrons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("First Test Patron"))
                .andExpect(jsonPath("$[0].email").value("first@example.com"))
                .andExpect(jsonPath("$[1].name").value("Second Test Patron"))
                .andExpect(jsonPath("$[1].email").value("second@example.com"));
    }
}

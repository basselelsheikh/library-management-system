package com.example.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateBook() throws Exception {
        String validBookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(validBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void testCreateBookWithMissingTitle() throws Exception {
        String invalidBookJson = "{ \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(invalidBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Title is required"));
    }

    @Test
    public void testCreateAndRetrieveBookById() throws Exception {
        String validBookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(validBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.title").value("Test Book")) 
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void testCreateTwoBooksAndRetrieveAllBooks() throws Exception {
        String firstBookJson = "{ \"title\": \"First Test Book\", \"author\": \"First Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(firstBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String secondBookJson = "{ \"title\": \"Second Test Book\", \"author\": \"Second Test Author\", \"publicationYear\": 2023, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(secondBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].title").value("First Test Book"))
                .andExpect(jsonPath("$[0].author").value("First Test Author"))
                .andExpect(jsonPath("$[1].title").value("Second Test Book"))
                .andExpect(jsonPath("$[1].author").value("Second Test Author"));
    }

    @Test
    public void testCreateAndUpdateBook() throws Exception {
        String validBookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(validBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String updatedBookJson = "{ \"title\": \"Updated Test Book\", \"author\": \"Updated Test Author\", \"publicationYear\": 2023, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/books/{id}", 1)
                .content(updatedBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Test Book"))
                .andExpect(jsonPath("$.author").value("Updated Test Author"))
                .andExpect(jsonPath("$.publicationYear").value(2023));
    }

    @Test
    public void testCreateAndDeleteBook() throws Exception {
        String validBookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 121212 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(validBookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

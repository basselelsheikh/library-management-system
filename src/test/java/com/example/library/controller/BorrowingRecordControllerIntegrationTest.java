package com.example.library.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowingRecordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBorrowBookSuccessfully() throws Exception {
        String bookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 122121 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String patronJson = "{ \"name\": \"Test Patron\", \"email\": \"test@example.com\" }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/patrons")
                .content(patronJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/borrow/{bookId}/patron/{patronId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAnotherPatronTryingToBorrowSameBookBeforeReturn() throws Exception {
        String bookJson = "{ \"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2022, \"isbn\": 122121 }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

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
                .post("/api/borrow/{bookId}/patron/{patronId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/borrow/{bookId}/patron/{patronId}", 1, 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

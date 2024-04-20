package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.service.BorrowingService;

@RestController
@RequestMapping("/api")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok("Book returned successfully.");
    }

}

package com.example.library.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class BorrowingRecord {
    @EmbeddedId
    private BorrowingRecordId id;
    
    @ManyToOne
    @MapsId("patronId")
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public BorrowingRecord() {
    }

    public BorrowingRecord(BorrowingRecordId id, LocalDate borrowingDate, LocalDate returnDate) {
        this.id = id;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public BorrowingRecordId getId() {
        return id;
    }

    public void setId(BorrowingRecordId id) {
        this.id = id;
    }

    // public Patron getPatron() {
    //     return patron;
    // }

    // public void setPatron(Patron patron) {
    //     this.patron = patron;
    // }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

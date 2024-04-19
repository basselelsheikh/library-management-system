package com.example.library.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BorrowingRecordId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    public BorrowingRecordId() {
    }

    public BorrowingRecordId(Patron patron, Book book) {
        this.patron = patron;
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowingRecordId)) return false;
        BorrowingRecordId that = (BorrowingRecordId) o;
        return Objects.equals(getPatron(), that.getPatron()) &&
                Objects.equals(getBook(), that.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatron(), getBook());
    }
}
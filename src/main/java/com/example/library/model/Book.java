package com.example.library.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @PositiveOrZero(message = "Publication year must be a positive or zero value")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @OneToMany(mappedBy = "book")
    Set<BorrowingRecord> borrowingRecords;
    

    public Book() {
    }

    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Transient
    public boolean isAvailableForBorrowing(List<BorrowingRecord> borrowingRecords) {
        // If there are no borrowing records, the book is available
        if (borrowingRecords == null || borrowingRecords.isEmpty()) {
            return true;
        }

        // Check if there is any borrowing record with a null return date (indicating it's still borrowed)
        return borrowingRecords.stream()
                .anyMatch(record -> record.getBook().equals(this) && record.getReturnDate() != null);
    }
}

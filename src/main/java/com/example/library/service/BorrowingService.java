package com.example.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.exception.ErrorCodes;
import com.example.library.exception.LibraryException;
import com.example.library.model.Book;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.Patron;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRecordRepository;
import com.example.library.repository.PatronRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowingService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowingService(BorrowingRecordRepository borrowingRecordRepository,
            BookRepository bookRepository,
            PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new LibraryException("Book not found with ID: " + bookId, ErrorCodes.BOOK_NOT_FOUND));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new LibraryException("Patron not found with id: " + patronId, ErrorCodes.PATRON_NOT_FOUND));

        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findByBookId(bookId);

        if (!book.isAvailableForBorrowing(borrowingRecords)) {
                throw new LibraryException("Book with ID " + bookId + " is already borrowed.", ErrorCodes.BOOK_ALREADY_BORROWED);
        }
        BorrowingRecord borrowingRecord = new BorrowingRecord(LocalDate.now(), book, patron, null);

        borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));

        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("No active borrowing record found for book with id " + bookId
                        + " and patron with id " + patronId));

        borrowingRecord.setReturnDate(LocalDate.now());

        borrowingRecordRepository.save(borrowingRecord);
    }

}

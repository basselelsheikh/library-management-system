package com.example.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        return optionalPatron.orElse(null);
    }

    public Patron addPatron(Patron patron) {
        // Perform any necessary validation or business logic before saving the patron
        
        // Save the patron using the PatronRepository
        return patronRepository.save(patron);
    }
}

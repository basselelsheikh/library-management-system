package com.example.library.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Patron;
import com.example.library.service.PatronService;

@RestController
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/api/patrons")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable("id") Long id) {
        Patron patron = patronService.getPatronById(id);
        if (patron != null) {
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/patrons")
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        Patron savedPatron = patronService.addPatron(patron);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @PutMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable("id") Long id, @RequestBody Patron updatedPatron) {
        Patron existingPatron = patronService.getPatronById(id);
        if (existingPatron != null) {
            updatedPatron.setId(id); // Ensure the updated patron has the correct ID
            Patron savedPatron = patronService.updatePatron(updatedPatron);
            return new ResponseEntity<>(savedPatron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/patrons/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable("id") Long id) {
        boolean deleted = patronService.deletePatronById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

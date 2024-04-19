package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}

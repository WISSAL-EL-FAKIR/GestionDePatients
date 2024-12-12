package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String nom);
}

package com.ServicePatient.GestionDePatients.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServicePatient.GestionDePatients.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNomComplet(String nomComplet);
}

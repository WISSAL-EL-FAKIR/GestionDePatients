package com.ServicePatient.GestionDePatients.services;

import java.util.List;

import com.ServicePatient.GestionDePatients.entities.Patient;

public interface ServicePatient {

    public Patient addPatient(Patient p);
    public Patient updatePatient(Long id, Patient p);
    public void deletePatient(Long id);
    public Patient getPatientByNom(String nom);
    public List<Patient> getAllPatient();
    public Patient getPatientById(Long idPatient);
    public Boolean checkPatient(Long idPatient);


}

package com.services;

import java.util.List;

import com.entities.Patient;

public interface ServicePatient {

    public Patient addPatient(Patient p);
    public Patient updatePatient(Long id, Patient p);
    public void deletePatient(Long id);
    public Patient getPatient(String nom);
    public List<Patient> getAllPatient();

}

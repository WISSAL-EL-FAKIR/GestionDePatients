package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Patient;
import com.repository.PatientRepository;

@Service
public class ServicePatientImp implements ServicePatient{

   @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient p) {
        return patientRepository.save(p);
    }

    @Override
    public Patient updatePatient(Long id, Patient p) {
        p.setId(id);
        return patientRepository.save(p);
    }

    @Override
    public void deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        }
    }

    @Override
    public Patient getPatient(String nom) {
        return patientRepository.findByNom(nom);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
  

}

package com.ServicePatient.GestionDePatients.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ServicePatient.GestionDePatients.entities.Patient;
import com.ServicePatient.GestionDePatients.repository.PatientRepository;
import org.springframework.web.server.ResponseStatusException;

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
    public Patient getPatientByNom(String nom) {
        return patientRepository.findByNomComplet(nom);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long idPatient) {
        return patientRepository.findById(idPatient)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @Override
    public Boolean checkPatient(Long idPatient) {
        return patientRepository.existsById(idPatient);
    }


}

package com.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entities.Patient;
import com.services.ServicePatient;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
public class PatientControlleur {

    @Autowired
    private ServicePatient service; 

    @GetMapping
    public List<Patient> getAllPatients() {
        return service.getAllPatient();
    }

    // Gère les requêtes POST pour ajouter un nouveau patient
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    // Gère les requêtes GET pour obtenir un patient par son nom
    @GetMapping("/{nomPatient}")
    public Patient getPatientByNom(@PathVariable String nom) {
        return service.getPatient(nom);
    }

    // Gère les requêtes PUT pour mettre à jour un patient existant
    @PutMapping("/{idPatient}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return service.updatePatient(id, patient);
    }

    // Gère les requêtes DELETE pour supprimer un patient par son ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
    }
}


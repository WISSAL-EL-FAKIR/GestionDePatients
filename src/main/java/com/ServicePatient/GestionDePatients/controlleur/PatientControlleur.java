package com.ServicePatient.GestionDePatients.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ServicePatient.GestionDePatients.entities.Patient;
import com.ServicePatient.GestionDePatients.services.ServicePatient;

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

    @GetMapping("/{idPatient}")
    public Patient getPatientById(@PathVariable Long idPatient){
        return service.getPatientById(idPatient);
    }


    // Gère les requêtes POST pour ajouter un nouveau patient
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    // Gère les requêtes GET pour obtenir un patient par son nom
    @GetMapping("/search/{nomPatient}")
    public Patient getPatientByNom(@PathVariable String nomPatient) {
        return service.getPatientByNom(nomPatient);
    }

    // Gère les requêtes PUT pour mettre à jour un patient existant
    @PutMapping("/{idPatient}")
    public Patient updatePatient(@PathVariable Long idPatient, @RequestBody Patient patient) {
        return service.updatePatient(idPatient, patient);
    }

    // Gère les requêtes DELETE pour supprimer un patient par son ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
    }

    @GetMapping("/check/{idPatient}")
    public Boolean checkPatient(@PathVariable Long idPatient){
        return service.checkPatient(idPatient);
    }
}


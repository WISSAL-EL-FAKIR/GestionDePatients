package com.ServicePatient.GestionDePatients.services;

import com.ServicePatient.GestionDePatients.entities.Patient;
import com.ServicePatient.GestionDePatients.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class ServicePatientTest {
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private ServicePatientImp servicePatient;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPatientById(){
        Patient patient = new Patient();
        patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Patient result = servicePatient.getPatientById(patient.getId());
        assertNotNull(result);
        assertEquals(patient.getId(),result.getId());
        verify(patientRepository,times(1)).findById(patient.getId());

    }

    @Test
    public void getAllPatients(){
        List<Patient> patients = Arrays.asList(new Patient(),new Patient());
        when(patientRepository.findAll()).thenReturn(patients);
        List<Patient> result = servicePatient.getAllPatient();
        assertNotNull(result);
        assertEquals(2,result.size());
        verify(patientRepository,times(1)).findAll();
    }

    @Test
    public void getPatientByNom(){
        Patient patient = new Patient();
        patient.setNomComplet("nom patient");
        when(patientRepository.findByNomComplet("nom patient")).thenReturn(patient);
        Patient result = servicePatient.getPatientByNom("nom patient");
        assertNotNull(result);
        assertEquals("nom patient",result.getNomComplet());
        verify(patientRepository,times(1)).findByNomComplet("nom patient");
    }

    @Test
    public void addPatient(){
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNomComplet("patient test");
        when(patientRepository.save(patient)).thenReturn(patient);
        Patient result = servicePatient.addPatient(patient);
        assertNotNull(result);
        assertEquals(1L,result.getId());
        assertEquals("patient test",result.getNomComplet());
        verify(patientRepository,times(1)).save(patient);

    }

    @Test
    public void testUpdatePatient(){
        Long patientId = 1L;
        Patient updatedPatient = new Patient();
        updatedPatient.setNomComplet("update Patient");

        // Simuler le comportement du mock
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        // Appeler la méthode à tester
        Patient result = servicePatient.updatePatient(patientId, updatedPatient);

        // Vérifications
        assertNotNull(result);
        assertEquals(patientId, result.getId());
        assertEquals("update Patient", result.getNomComplet());
        assertEquals(1L,result.getId());

        // Vérification des interactions avec le mock
        verify(patientRepository, times(1)).save(updatedPatient);

    }

    @Test
    public void testDeletePatient(){
        when(patientRepository.existsById(1L)).thenReturn(true);
        servicePatient.deletePatient(1L);
        verify(patientRepository,times(1)).existsById(1L);
        verify(patientRepository,times(1)).deleteById(1L);
    }

    @Test
    public void testCheckPatient(){
        when(patientRepository.existsById(1L)).thenReturn(true);
        Boolean result = servicePatient.checkPatient(1L);
        assertNotNull(result);
        assertEquals(true,result);
        verify(patientRepository,times(1)).existsById(1L);

        //cas contraire
        when(patientRepository.existsById(9999L)).thenReturn(false);
        Boolean result2 = servicePatient.checkPatient(9999L);
        assertNotNull(result2);
        assertEquals(false,result2);
        verify(patientRepository,times(1)).existsById(9999L);
    }








}

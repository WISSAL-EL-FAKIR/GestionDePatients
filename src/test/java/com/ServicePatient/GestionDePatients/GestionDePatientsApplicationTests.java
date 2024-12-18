package com.ServicePatient.GestionDePatients;

import com.ServicePatient.GestionDePatients.entities.Patient;
import com.ServicePatient.GestionDePatients.repository.PatientRepository;
import com.ServicePatient.GestionDePatients.services.ServicePatient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = GestionDePatientsApplication.class)
@AutoConfigureMockMvc
class GestionDePatientsApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ServicePatient servicePatient;
	@Autowired
	private ObjectMapper objectMapper;

	private Patient patient;

	@BeforeEach
	public void setUp(){
		patient = new Patient();
		patient.setNomComplet("Test_patient");
		patient.setEmail("test_patient@gmail.com");
		patient.setLieuDeNaissance("ville_test");
		patientRepository.save(patient);

	}
	@Test
	@Transactional
	@Rollback
	void shouldFetchAllPatients() throws Exception{
		int size = (int) patientRepository.count();
		mockMvc.perform(get("/patients"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()",is(size)));
	}

	@Test
	@Transactional
	@Rollback
	void shouldFetchPatientById() throws Exception{
		mockMvc.perform(get("/patients/"+patient.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomComplet",is(patient.getNomComplet())))
				.andExpect(jsonPath("$.email",is(patient.getEmail())))
				.andExpect(jsonPath("$.lieuDeNaissance",is(patient.getLieuDeNaissance())));
	}

	@Test
	@Transactional
	@Rollback
	void shouldFetchPatientByNom() throws Exception{
		mockMvc.perform(get("/patients/search/"+patient.getNomComplet()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email",is(patient.getEmail())))
				.andExpect(jsonPath("$.lieuDeNaissance",is(patient.getLieuDeNaissance())));
	}

	@Test
	@Transactional
	@Rollback
	void shouldCreatePatient()throws Exception{
		ResultActions response = mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient)));
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomComplet",is(patient.getNomComplet())))
				.andExpect(jsonPath("$.email",is(patient.getEmail())))
				.andExpect(jsonPath("$.lieuDeNaissance",is(patient.getLieuDeNaissance())));

	}

	@Test
	@Transactional
	@Rollback
	void shouldUpdateUser()throws Exception{
		Patient updatedPateint = new Patient();
		updatedPateint.setNomComplet("test_update");
		updatedPateint.setEmail("update@gmail.com");
		mockMvc.perform(put("/patients/"+patient.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPateint)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomComplet",is(updatedPateint.getNomComplet())))
				.andExpect(jsonPath("$.email",is(updatedPateint.getEmail())));
	}

	@Test
	@Transactional
	@Rollback
	void shouldDeletePatient()throws Exception{
		mockMvc.perform(delete("/patients/"+patient.getId()))
				.andExpect(status().isOk());
		mockMvc.perform(get("/patients/"+patient.getId()))
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	@Rollback
	void shouldCheckPatient()throws Exception{
		mockMvc.perform(get("/patients/check/"+patient.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",is(true)));
		mockMvc.perform(get("/patients/check/9999"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",is(false)));
	}

	@AfterEach
	public void tearDown(){
		patientRepository.delete(patient);
	}


	@Test
	void contextLoads() {
	}

}

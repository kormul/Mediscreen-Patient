package com.mediscreen.patient.service;

import java.util.List;

import javax.validation.Valid;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;

public interface PatientService {
	
	public List<Patient> getAllPatients();
	
	public List<PatientDTO> getAllPatientsDTO();
	
	public Patient getPatientById(int id);
	
	public boolean createPatient(@Valid PatientDTO patientDTO);
	
	public boolean deletePatient(int id);
	
	public boolean updatePatient(@Valid PatientDTO patientDTO);


}

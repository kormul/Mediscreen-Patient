package com.mediscreen.patient.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.ConvertModelDTO;
import com.mediscreen.patient.service.PatientService;

@RestController
public class PatientRestController {

	private Logger logger = LogManager.getLogger();
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ConvertModelDTO convertModelDTO;
	
	
	@GetMapping("/rest/patient/list")
	public List<PatientDTO> getListPatient() {
		
		logger.info("Get : Rest List Patient");

		
		return patientService.getAllPatientsDTO();
	}
	
	@GetMapping("/rest/patient")
	public PatientDTO getGetPatient(@RequestParam(value = "id", required = true) int id) {
		
		logger.info("Get : Rest Patient");
		
		Patient patient = patientService.getPatientById(id);

		return convertModelDTO.patientToPatientDto(patient);
	}
	
	
}

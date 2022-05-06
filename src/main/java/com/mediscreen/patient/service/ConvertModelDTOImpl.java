package com.mediscreen.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;

@Service
public class ConvertModelDTOImpl implements ConvertModelDTO{

	private Logger logger = LogManager.getLogger();

	@Override
	public PatientDTO patientToPatientDto(Patient patient) {
		logger.debug("ConvertModelDTO : patientToPatientDto");

		
		return new PatientDTO(
				patient.getId(),
				patient.getFirstName(), 
				patient.getLastName(), 
				patient.getDateOfBirth(), 
				patient.isMan(), 
				patient.getPhone(), 
				patient.getAddress().getAddress(),
				patient.getAddress().getCity(),
				patient.getAddress().getZip(),
				patient.getAddress().getCountry());
	}

}

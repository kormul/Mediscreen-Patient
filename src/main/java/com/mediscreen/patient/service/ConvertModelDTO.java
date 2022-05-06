package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;

public interface ConvertModelDTO {
	
	public PatientDTO patientToPatientDto(Patient patient);


}

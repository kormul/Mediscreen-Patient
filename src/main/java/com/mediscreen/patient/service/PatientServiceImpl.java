package com.mediscreen.patient.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Address;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.AddressRepository;
import com.mediscreen.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressService addressService;

	public List<Patient> getAllPatients(){
	
		logger.debug("PatientService : getAllPatient");
	
		return patientRepository.findAll();
	}
	

	@Override
	public List<PatientDTO> getAllPatientsDTO() {
		logger.debug("PatientService : getAllPatientsDTO");

		
		List<PatientDTO> patientDTOs = new ArrayList<>();
		
		for(Patient patient : this.getAllPatients()) {
			patientDTOs.add(new PatientDTO(
					patient.getId(),
					patient.getFirstName(), 
					patient.getLastName(), 
					patient.getDateOfBirth(), 
					patient.isMan(), 
					patient.getPhone(), 
					patient.getAddress().getAddress(),
					patient.getAddress().getCity(),
					patient.getAddress().getZip(),
					patient.getAddress().getCountry())
					);
		}
		
		return patientDTOs;
		
	}
	
	public Patient getPatientById(int id){
		logger.debug("PatientService : getPatientById");
	
		return patientRepository.getById(id);
	}

	@Override
	public boolean createPatient(@Valid PatientDTO patientDTO) {
		logger.debug("PatientService : createPatient");
		
		Patient patient = new Patient();
		Address address = new Address();
		
		patient.setFirstName(patientDTO.getFirstName());
		patient.setLastName(patientDTO.getLastName());
		patient.setDateOfBirth(patientDTO.getDateOfBirth());
		patient.setMan(patientDTO.isMan());
		patient.setAddress(address);
		patient.setPhone(patientDTO.getPhone());
		
		address.setAddress(patientDTO.getAddress());
		address.setCity(patientDTO.getCity());
		address.setZip(patientDTO.getZip());
		address.setCountry(patientDTO.getCountry());
		
		addressRepository.save(address);
		patientRepository.save(patient);

		return true;
	}

	@Override
	public boolean updatePatient(@Valid PatientDTO patientDTO) {
		logger.debug("PatientService : updatePatient");

		Patient patient = patientRepository.getById(patientDTO.getId());
		
		Address address = patient.getAddress();
		
		patient.setFirstName(patientDTO.getFirstName());
		patient.setLastName(patientDTO.getLastName());
		patient.setDateOfBirth(patientDTO.getDateOfBirth());
		patient.setMan(patientDTO.isMan());
		patient.setPhone(patientDTO.getPhone());
		
		address.setAddress(patientDTO.getAddress());
		address.setCity(patientDTO.getCity());
		address.setZip(patientDTO.getZip());
		address.setCountry(patientDTO.getCountry());
		
		addressRepository.save(address);
		patientRepository.save(patient);

		return true;
	}

	@Override
	public boolean deletePatient(int id) {
		logger.debug("PatientService : deletePatient");

		Patient patientTodelete = getPatientById(id);
		if(patientTodelete == null) {
			return false;
		}
		Address addressToDelete = patientTodelete.getAddress();
		patientRepository.delete(patientTodelete);
		addressService.deleteAddreess(addressToDelete);
		
		return true;
	}

}

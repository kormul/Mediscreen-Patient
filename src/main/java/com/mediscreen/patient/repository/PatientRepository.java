package com.mediscreen.patient.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mediscreen.patient.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{
	
	List<Patient> findAll();

	Patient getById(int id);
	
	void deleteById(int id);
	
	void delete(Patient patient);

}

package com.mediscreen.patient.repository;

import org.springframework.data.repository.CrudRepository;

import com.mediscreen.patient.model.Address;


public interface AddressRepository extends CrudRepository<Address, Integer>{

	void delete(Address address);
	
}

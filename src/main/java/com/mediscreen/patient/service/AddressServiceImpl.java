package com.mediscreen.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.model.Address;
import com.mediscreen.patient.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	private Logger logger = LogManager.getLogger();

	@Autowired
	private AddressRepository addressRepository;
	
	public Address getAddressById(int id) {
		logger.debug("AddressService : getAddressById");
		return addressRepository.findById(id).get();
	}

	@Override
	public boolean deleteAddreess(Address address) {
		logger.debug("AddressService : deleteAddreess");

		addressRepository.delete(address);
		return true;
		
	}

}

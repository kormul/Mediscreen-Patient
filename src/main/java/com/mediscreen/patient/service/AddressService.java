package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Address;

public interface AddressService {
	
	public Address getAddressById(int id);
	
	public boolean deleteAddreess(Address address);
	
}

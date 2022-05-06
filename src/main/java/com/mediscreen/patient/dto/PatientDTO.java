package com.mediscreen.patient.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

	private int id;
	
	@NotNull(message = "Fist Name is mandatory")
	private String firstName;
	
	@NotNull(message = "Last Name is mandatory")
	private String lastName;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "BirthDate is mandatory")
	private Date dateOfBirth;

	@NotNull(message = "Gender is mandatory")
	private boolean man;
	
	@NotNull(message = "Phone is mandatory")
	private String phone;
    
	@NotNull(message = "Address is mandatory")
	private String address;
	
	@NotNull(message = "City is mandatory")
	private String city;
	
	@NotNull(message = "ZIP is mandatory")
	private String zip;
	
	@NotNull(message = "Country is mandatory")
	private String country;
	
}

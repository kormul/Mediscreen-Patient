package com.mediscreen.patient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	@Column(name="address")
	@NotNull(message = "address is mandatory")
	private String address;
	
	@Column(name="city")
	@NotNull(message = "city is mandatory")
	private String city;
	
	@Column(name="zip")
	@NotNull(message = "zip is mandatory")
	private String zip;
	
	@Column(name="country")
	@NotNull(message = "country is mandatory")
	private String country;
	
}

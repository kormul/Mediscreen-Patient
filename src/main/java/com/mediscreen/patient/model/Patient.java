package com.mediscreen.patient.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Fist Name is mandatory")
	@Column(name="firstName")
	private String firstName;
	
	@NotNull(message = "Last Name is mandatory")
	@Column(name="lastName")
	private String lastName;
	
	@NotNull(message = "Birthday is mandatory")
	@Column(name="dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@NotNull(message = "Gender is mandatory")
	@Column(name="man")
	private boolean man;
	
	@OneToOne()
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@NotNull(message = "Phone Number is mandatory")
	@Column(name="phone")
	private String phone;
}

package com.mediscreen.patient.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.AddressService;
import com.mediscreen.patient.service.ConvertModelDTO;
import com.mediscreen.patient.service.PatientService;


@Controller
public class PatientWebController {

	private Logger logger = LogManager.getLogger();

	@Autowired
	AddressService addressService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ConvertModelDTO convertModelDTO;
	
	@GetMapping("/patient/add")
	public String getAddPatient(Model model) {
		logger.info("Get : AddPatient");
		model.addAttribute("PatientDTO", new PatientDTO());
		return "Add";
	}
	
	@PostMapping("/patient/add")
	public RedirectView postAddPatient(@Valid @ModelAttribute("PatientDTO") PatientDTO patientDTO, BindingResult result){
		
		logger.info("Post : AddPatient");
		
		if(!result.hasErrors()) {
			patientService.createPatient(patientDTO);
			return new RedirectView("/patient/list");
		}
		else {
			for(ObjectError objectError : result.getAllErrors()) {
				logger.info(objectError.toString());
			}
			return new RedirectView("/patient/add");
		}

	}
	
	@GetMapping("/patient/list")
	public String getListPatient(Model model) {
		logger.info("Get : List Patient");
		model.addAttribute("patients", patientService.getAllPatientsDTO());
		return "List";
	}
	
	@GetMapping("/patient/update")
	public String getUpdatePatient(@RequestParam(value = "id", required = true) int id, Model model) {
		logger.info("Get : Update Patient");
		
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("PatientDTO", convertModelDTO.patientToPatientDto(patient));
		
		return "Update";
	}
	
	@PostMapping("/patient/update")
	public RedirectView postUpdatePatient(@Valid @ModelAttribute("PatientDTO") PatientDTO patientDTO, BindingResult result){
		
		logger.info("Post : UpdatedPatient");
	
		if(!result.hasErrors()) {
			patientService.updatePatient(patientDTO);
			return new RedirectView("/patient/list");
		}
		else {
			for(ObjectError objectError : result.getAllErrors()) {
				logger.info(objectError.toString());
			}
			return new RedirectView("/patient/list");
		}
	}
	
	@GetMapping("/patient/delete")
	public RedirectView getDeletePatient(@RequestParam(value = "id", required = true) int id) {
		
		logger.info("Get : Delete Patient");
		patientService.deletePatient(id);
		
		return new RedirectView("/patient/list");
	}
	
}

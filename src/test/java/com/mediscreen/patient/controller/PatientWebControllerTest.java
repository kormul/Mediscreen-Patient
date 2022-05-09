package com.mediscreen.patient.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Address;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest

public class PatientWebControllerTest {

    private MockMvc mockMvc;

    @Autowired
    PatientService patientService;
	
    @Autowired
    private WebApplicationContext webContext;
    
    List<Patient> patients;
    PatientDTO patientDTO;
    
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();

        patients = new ArrayList<>();
        patients.add(new Patient(1,"Guillaume","Aubert", Date.from(Instant.now()), true, new Address(1,"4 la ruelle","Noisy-Rudignon", "77940", "France" ),"0669120050"));
        patients.add(new Patient(1,"Clemence","Depres", Date.from(Instant.now()), true, new Address(1,"4 Rugrande","Ville-st-Jacque", "77130", "France" ),"0669120077"));
        patientDTO = new PatientDTO(1,"Guillaume","Aubert", Date.from(Instant.now()), true, "0669120050", "4 la ruelle","Noisy-Rudignon", "77940", "France");
        
    }
    
    @Test
    public void getListPatient() throws Exception{
    	
    	mockMvc.perform(get("/patient/list"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("patients"))
        .andReturn();
  
    }
    
    @Test
    public void getAddPatient() throws Exception{
    	
    	mockMvc.perform(get("/patient/add"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("PatientDTO"))
        .andReturn();
    }
  
    @Test
    public void postAddPatient() throws Exception{
    	
    	mockMvc.perform(post("/patient/add")
        .flashAttr("patientDTO", patientDTO)
        .param("id", "1")
        .param("firstName", "Guillaume")
        .param("lastName", "Aubert")
        .param("dateOfBirth", "2001-01-13")
        .param("man", "true")
        .param("phone", "0669120050")
        .param("address", "4 la ruelle")
        .param("city", "Noisy-Rudignon")
        .param("zip", "77940")
        .param("country", "France")

        )
    	.andExpect(status().is3xxRedirection())
        .andReturn();
    	
    }	
    
    @Test
    public void getUpdatePatient() throws Exception{
    	
    	mockMvc.perform(get("/patient/update")
    	        .flashAttr("PatientDTO", patientDTO)
    	        .param("id", "1")
    	        .param("firstName", "Guillaume")
    	        .param("lastName", "Aubert")
    	        .param("dateOfBirth", "2001-01-13")
    	        .param("man", "true")
    	        .param("phone", "0669120050")
    	        .param("address", "4 la ruelle")
    	        .param("city", "Noisy-Rudignon")
    	        .param("zip", "77940")
    	        .param("country", "France")
    	        )
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("PatientDTO"))
        .andReturn();
    }
    
    @Test
    public void postUpdatePatient() throws Exception{
    	
    	mockMvc.perform(post("/patient/update")
        .flashAttr("patientDTO", patientDTO)
        .param("id", "1")
        .param("firstName", "Guillaume")
        .param("lastName", "Aubert")
        .param("dateOfBirth", "2001-01-13")
        .param("man", "true")
        .param("phone", "0669120050")
        .param("address", "4 la ruelle")
        .param("city", "Noisy-Rudignon")
        .param("zip", "77940")
        .param("country", "France")

        )
    	.andExpect(status().is3xxRedirection())
        .andReturn();
    	
    }
    
    @Test
    public void lastTestGetDelete() throws Exception{
    	
    	int id = patientService.getAllPatients().get(patientService.getAllPatients().size()-1).getId();
    	System.out.println(id);
    	mockMvc.perform(get("/patient/delete?id="+id))
    	.andExpect(status().is3xxRedirection())
        .andReturn();
    	
    }
    
}

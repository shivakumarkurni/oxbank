package com.hcl.ox.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.service.OfficerDetailsService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, OfficerDetailsController.class })
@WebAppConfiguration
public class TestOfficerDetailsController {
	
	@InjectMocks
	OfficerDetailsController officerDetailsController;
	
	private MockMvc mockMvc;

	@Mock
	OfficerDetailsService officerDetailsService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(officerDetailsController).build();
	}
	
	@Test
	public void testapprovingLoan() throws Exception {
		long customerId=100L;
		String str="value";
		ResponseEntity<String> st= new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
		Mockito.when(officerDetailsService.approvingLoan(Mockito.anyLong())).thenReturn("value");
		
		this.mockMvc.perform(
				post("/loan/{customerId}",100L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerId)))
				.andReturn();
		ResponseEntity<String> st1=	officerDetailsController.approvingLoan(customerId);
		assertEquals(st, st1);
	}
	
	@Test
	public void testapprovingLoan_1() throws Exception {
		long customerId=100L;
		String str=null;
		ResponseEntity<String> st= new ResponseEntity<String>(str, HttpStatus.NOT_ACCEPTABLE);
		Mockito.when(officerDetailsService.approvingLoan(Mockito.anyLong())).thenReturn(null);
		
		this.mockMvc.perform(
				post("/loan/{customerId}",100L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerId)))
				.andReturn();
		ResponseEntity<String> st1=	officerDetailsController.approvingLoan(customerId);
		assertEquals(st, st1);
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

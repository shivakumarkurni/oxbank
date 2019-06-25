package com.hcl.ox.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ox.entity.Customer;
import com.hcl.ox.service.CustomerLoanService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, CustomerLoanController.class })
@WebAppConfiguration
public class TestCustomerLoanController {

	@InjectMocks
	CustomerLoanController customerLoanController;

	private MockMvc mockMvc;

	@Mock
	CustomerLoanService customerLoanService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerLoanController).build();
	}

	@Test
	public void testloanRejections() throws Exception {
		long id = 10L;
		List<Customer> li = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setAge(45);
		li.add(customer);
		Mockito.when(customerLoanService.loanRejections(Mockito.anyLong())).thenReturn(li);
		this.mockMvc.perform(
				get("/rejectedcustomers/{id}", 10L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(id)))
				.andReturn();
		customerLoanController.loanRejections(id);
		assertEquals(45, customer.getAge());
	}

	@Test
	public void testloanApproved() throws Exception {
		long id = 10L;
		List<Customer> li = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setAge(45);
		li.add(customer);
		Mockito.when(customerLoanService.loanRejections(Mockito.anyLong())).thenReturn(li);
		this.mockMvc.perform(
				get("/approvedcustomers/{id}",10L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(id)))
				.andReturn();
		customerLoanController.loanApproved(id);
		assertEquals(45, customer.getAge());
	}
	
	@Test
	public void testloanApproved_1() throws Exception {
		long id = 10L;		
		Mockito.when(customerLoanService.loanRejections(Mockito.anyLong())).thenReturn(null);
		this.mockMvc.perform(
				get("/approvedcustomers/{id}",10L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(id)))
				.andReturn();
		List<Customer> li=customerLoanController.loanApproved(id);
		assertEquals(li, new Exception("no data present"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

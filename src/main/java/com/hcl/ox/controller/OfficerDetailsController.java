package com.hcl.ox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ox.service.OfficerDetailsService;

@RestController
@RequestMapping("/")
public class OfficerDetailsController {

	@Autowired
	OfficerDetailsService officerDetailsService;

	@PutMapping("/loan/{customerId}")
	public ResponseEntity<String> approvingLoan(@PathVariable long customerId) {
		String str = officerDetailsService.approvingLoan(customerId);
		if (str != null) {
			return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(str, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}

package com.finra.assignment.phonenumberalphanumericcombinations.controller;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finra.assignment.phonenumberalphanumericcombinations.exception.InvalidPhoneNumberException;
import com.finra.assignment.phonenumberalphanumericcombinations.model.Combination;
import com.finra.assignment.phonenumberalphanumericcombinations.service.PhoneNumberAlphaNumericCombinationsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/finra")
@Validated
public class PhoneNumberAlphaNumericCombinationsController {

	@Autowired
	PhoneNumberAlphaNumericCombinationsService service;

	@GetMapping()
	public String hello() {
		return "Hello FINRA";
	}

	@GetMapping("/{number}")
	public Page<Combination> getCombinations(@PathVariable("number") @Size(min = 7, max = 10) String number,
			Pageable pageable) throws InvalidPhoneNumberException {

		int size = number.length();
		if (size == 8 || size == 9) {
			throw new InvalidPhoneNumberException("Pleae Enter a valid 7 or 10 digit phone number.");
		}

		Page<Combination> combinations = service.getCombinations(number, pageable);
		
		return combinations;
	}
	
	@ExceptionHandler(InvalidPhoneNumberException.class)
	public String handleInvalidPhoneNumberException(InvalidPhoneNumberException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler
	public String handleArraysIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
		return e.getMessage();
	}

}

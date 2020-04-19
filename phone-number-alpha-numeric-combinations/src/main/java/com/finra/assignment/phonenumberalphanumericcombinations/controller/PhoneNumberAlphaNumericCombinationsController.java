package com.finra.assignment.phonenumberalphanumericcombinations.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finra.assignment.phonenumberalphanumericcombinations.exception.InvalidPhoneNumberException;
import com.finra.assignment.phonenumberalphanumericcombinations.model.Combination;
import com.finra.assignment.phonenumberalphanumericcombinations.service.PhoneNumberAlphaNumericCombinationsService;

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

	@PostMapping("/{number}")
	public Combination getCombinations(@PathVariable("number") @Size(min = 7, max = 10) String number,
			@RequestParam(value = "page", required = false) Integer page) throws InvalidPhoneNumberException {

		int size = number.length();
		if (size == 8 || size == 9) {
			throw new InvalidPhoneNumberException("Pleae Enter a valid 7 or 10 digit phone number.");
		}

		List<String> combinations = service.getCombinations(number);

		int count = combinations.size();
		List<Combination> combs = new ArrayList<>();
		int pages = count % 10 == 0 ? count / 10 : (count / 10) + 1;
		
		if(page !=null && page <=0 || page >pages)
			throw new ArrayIndexOutOfBoundsException("Please provide page between 1 and " + pages);
		
		int c = 0;
		for (int i = 0; i < pages; i++) {
			int toIndex = c + 10 > count ? count : c + 10;
			combs.add(new Combination(count, (i + 1), pages, combinations.subList(c, toIndex)));
			c = toIndex;
		}
		
		return page == null ? combs.get(0) : combs.get(page - 1);
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

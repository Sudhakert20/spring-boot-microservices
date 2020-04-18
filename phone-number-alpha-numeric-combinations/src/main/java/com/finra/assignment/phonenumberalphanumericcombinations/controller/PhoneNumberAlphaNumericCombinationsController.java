package com.finra.assignment.phonenumberalphanumericcombinations.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finra")
public class PhoneNumberAlphaNumericCombinationsController {
	
	@GetMapping()
	public String hello() {
		return "Hello FINRA";
	}
	
	@PostMapping("/{number}")
	public List<String> getCombinations(@PathVariable("number") Long number){
		
		return Arrays.asList("NUMBER");
	}

}

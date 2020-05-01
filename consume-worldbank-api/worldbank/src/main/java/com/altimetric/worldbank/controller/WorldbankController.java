package com.altimetric.worldbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetric.worldbank.model.Worldbank;
import com.altimetric.worldbank.service.WorldbankService;

@RestController
@RequestMapping("/worldbank")
public class WorldbankController {

	@Autowired
	WorldbankService service;

	@GetMapping
	public ResponseEntity<?> getAllWorldbankDetails() {
		List<Worldbank> countryList = service.getAllWorldbankDetails();
		return ResponseEntity.ok().body(countryList);
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getWorldbankCountryByName(@PathVariable String name, HttpServletResponse response) {
		Worldbank country = service.getWorldbankCountryByName(name);
		return ResponseEntity.ok().body(country);
	}

	@GetMapping("/{name}/options")
	public ResponseEntity<?> getWorldbankCountriesWithCapitalCity(@PathVariable String name,
			@RequestParam(required = false) boolean region, @RequestParam(required = false) boolean incomeLevel,
			@RequestParam(required = false) boolean lendingType, HttpServletResponse response) {

		List<Worldbank> countryWithCapitalList = service.getWorldBankCountriesWithCapitalCity(name, region, incomeLevel,
				lendingType);
		return ResponseEntity.ok().body(countryWithCapitalList);
	}

}

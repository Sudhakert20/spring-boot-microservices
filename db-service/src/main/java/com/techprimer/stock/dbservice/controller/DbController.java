package com.techprimer.stock.dbservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DbController {

	@GetMapping("/{user}")
	public String getUserStock(@PathVariable("user") String user) {
		
		return "Hi " + user + " Your Amazon stock price is at 2000$/share" ;
	}
}

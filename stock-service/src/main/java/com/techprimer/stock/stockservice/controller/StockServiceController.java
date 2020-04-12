package com.techprimer.stock.stockservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stock")
public class StockServiceController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{user}")
	public String getStockPrice(@PathVariable("user") String user) {
		
		ResponseEntity<List<String>> stocks = restTemplate.exchange("http://localhost:8301/db/"+user+"/stocks", HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
		//output: <200,[AMZN, GOOG, APPL],[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sun, 12 Apr 2020 23:31:08 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
		return stocks.toString();
	}
	
}

package com.techprimer.stock.stockservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stock")
public class StockServiceController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{user}")
	public List<String> getStockByUser(@PathVariable("user") String user) {
		ResponseEntity<List<String>> stocks = restExchange(user);

		return stocks.getBody();
	}

	@GetMapping("/{user}/fullname")
	public List<String> getStockPrice(@PathVariable("user") String user) {

		ResponseEntity<List<String>> stocks = restExchange(user);
		// output: ["AMZN","GOOG","APPL"]
		List<String> fullNames = stocks.getBody().stream().map(s -> getPrice(s).getName()).collect(Collectors.toList());

		return fullNames;
	}

	private ResponseEntity<List<String>> restExchange(String user) {
		ResponseEntity<List<String>> stocks = restTemplate.exchange("http://localhost:8301/db/" + user + "/stocks",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		return stocks;
	}

	private Stock getPrice(String stockName) {

		try {
			// Sending request:
			// http://download.finance.yahoo.com/d/quotes.csv?s=GOOG&f=nsc4xab2sa5sbb3sb6sl1sk3sd1t1opghva2kjm3m4sj2sss1sj1sf6sr1qdyee7e9e8rr5p6p5b4s6j4t8s7&e=.csv
			// java.net.UnknownHostException: download.finance.yahoo.com
			Stock stock = YahooFinance.get(stockName);
			return stock;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

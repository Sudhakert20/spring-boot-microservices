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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stock")
public class StockServiceController {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "yahooFallback", commandKey="stock", groupKey="stockGroup")
	@GetMapping("/{user}")
	public List<String> getStockByUser(@PathVariable("user") String user) {
		ResponseEntity<List<String>> stocks = restExchange(user);

		return stocks.getBody();
	}

	@HystrixCommand(fallbackMethod = "yahooFallback", commandKey="yahoo", groupKey="yahooGroup")
	@GetMapping("/{user}/fullname")
	public List<String> getStockPrice(@PathVariable("user") String user) {

		ResponseEntity<List<String>> stocks = restExchange(user);
		List<String> fullNames = stocks.getBody().stream().map(s -> getYahooStock(s).getName())
				.collect(Collectors.toList());
		return fullNames;
	}

	private ResponseEntity<List<String>> restExchange(String user) {
		ResponseEntity<List<String>> stocks = restTemplate.exchange("http://localhost:8301/db/" + user + "/stocks",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		return stocks;
	}

	private Stock getYahooStock(String stockName) {

		try {
			// https://query1.finance.yahoo.com/v7/finance/quote?symbols=GOOG
			Stock stock = YahooFinance.get(stockName);
			return stock;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private List<String> yahooFallback(String user) {
		return this.getStockByUser(user).stream().map(s -> s.concat(" -> Yahoo Finance retrieve full name failed."))
				.collect(Collectors.toList());
	}

}

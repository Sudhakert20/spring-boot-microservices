package com.techprimer.stock.stockservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.techprimer.stock.stockservice.model.StockDetails;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

//Error in UI: Access to XMLHttpRequest at 'http://127.0.0.1:8300/api/stock-service/stock/sam/details' from origin 'null' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
//Fix: @CrossOrigin
@CrossOrigin("*")
@RestController
@RequestMapping("/stock")
public class StockServiceController {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "yahooFallback", commandKey = "stock", groupKey = "stockGroup")
	@GetMapping("/{user}")
	public List<String> getStockByUser(@PathVariable("user") String user) {
		return restExchange(user);
	}

	@HystrixCommand(fallbackMethod = "yahooFallback", commandKey = "yahoo", groupKey = "yahooGroup")
	@GetMapping("/{user}/fullname")
	public List<String> getStockPrice(@PathVariable("user") String user) {

		List<String> stocks = restExchange(user);
		List<String> fullNames = stocks.stream().map(s -> getYahooStock(s).getName()).collect(Collectors.toList());
		return fullNames;
	}

	@GetMapping("/{user}/details")
	public List<StockDetails> getStockDetails(@PathVariable("user") String user) {

		List<String> dbStocks = restExchange(user);

		// Error: The method map(Function<? super String,? extends R>) in the type
		// Stream<String> is not applicable for the arguments ((<no type> s) -> {})
		// Fix: missed return keyword inside map interface.
		List<StockDetails> stockDetails = dbStocks.stream().map(s -> {
			Stock stock = getYahooStock(s);
			return new StockDetails(stock.getSymbol(), stock.getName(), stock.getStockExchange(),
					stock.getQuote().getPrice(), stock.getQuote().getPreviousClose(), stock.getQuote().getOpen(),
					stock.getQuote().getDayLow(), stock.getQuote().getDayHigh(), stock.getQuote().getYearLow(),
					stock.getQuote().getYearHigh());
		}).collect(Collectors.toList());

		return stockDetails;
	}

	private List<String> restExchange(String user) {
		// Error: java.lang.IllegalStateException: No instances available for localhost
		// Fix: When you use a @LoadBalanced RestTemplate the hostname needs to be a
		// serviceId not an actual hostname
		ResponseEntity<List<String>> stocks = restTemplate.exchange("http://db-service/db/" + user + "/stocks",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		return stocks.getBody();
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

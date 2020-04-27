package com.techprimer.stock.dbservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimer.stock.dbservice.model.Stock;
import com.techprimer.stock.dbservice.repository.DbServiceRepository;

//Error in UI: Access to XMLHttpRequest at 'http://127.0.0.1:8300/api/stock-service/stock/sam/details' from origin 'null' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
//Fix: @CrossOrigin
@CrossOrigin("*")
@RestController
@RequestMapping("/db")
public class DbController {
	
	@Autowired
	DbServiceRepository repo;
	
	@GetMapping("/{id}")
	public Stock getStockById(@PathVariable("id") int id) {
		return repo.findById(id).get();
	}
	
	@GetMapping("/{user}/stocks")
	public List<String> getUserStocks(@PathVariable("user") String user){
		return repo.findByUser(user).stream().map(Stock::getStockName).collect(Collectors.toList());
	}
	
	@GetMapping("/all")
	public List<Stock> getAllStocks(){
		return repo.findAll();
	}
	
	@GetMapping("all/{user}")
	public List<Stock> getStockByUser(@PathVariable("user") String user) {
		return repo.findByUser(user);
	}
	
	@PostMapping("/add")
	public Stock addUserStock(@RequestBody Stock stock) {
		return repo.save(stock);
	}
	
	@PostMapping("/delete/{stockName}")
	public List<Stock> deleteByStockName(@PathVariable("stockName") String stockName) {
		List<Stock> stocks = repo.findByStockName(stockName);
		
		stocks.stream().forEach(s -> repo.delete(s));
		
		return repo.findAll();
	}
	
}

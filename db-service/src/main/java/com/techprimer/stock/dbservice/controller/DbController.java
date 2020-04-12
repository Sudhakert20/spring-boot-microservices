package com.techprimer.stock.dbservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimer.stock.dbservice.model.Stock;
import com.techprimer.stock.dbservice.repository.DbServiceRepository;

@RestController
@RequestMapping("/db")
public class DbController {
	
	@Autowired
	DbServiceRepository repo;
	
	@GetMapping("/{id}")
	public Stock getStockById(@PathVariable("id") int id) {
		return repo.findById(id).get();
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
}

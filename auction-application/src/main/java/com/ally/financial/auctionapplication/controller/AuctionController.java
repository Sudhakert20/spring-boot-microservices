package com.ally.financial.auctionapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	@GetMapping
	public String getAuction() {
		return "Welcome to biding.";
	}
	
}

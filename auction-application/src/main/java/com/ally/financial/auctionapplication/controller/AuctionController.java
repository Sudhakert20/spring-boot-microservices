package com.ally.financial.auctionapplication.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ally.financial.auctionapplication.exception.ReservedPriceNotMetException;
import com.ally.financial.auctionapplication.exception.ResourceNotFoundException;
import com.ally.financial.auctionapplication.model.Auction;
import com.ally.financial.auctionapplication.repository.AuctionRepository;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	private static final Logger log = LoggerFactory.getLogger(AuctionController.class);

	@Autowired
	AuctionRepository repo;

	@GetMapping
	public String getAuction() {
		return "Welcome to Ally Auction.";
	}

	@GetMapping("/auctionItems")
	public List<Auction> getAuctionItems() {
		log.info("Get All Auction Items");
		return repo.findAll();
	}

	@GetMapping("/auctionItems/{auctionItemId}")
	public Auction getAuctionItemById(@PathVariable Long auctionItemId) {
		log.info("Get Auction Item By Id " + auctionItemId);
		Optional<Auction> auction = repo.findById(auctionItemId);
		return auction.isPresent() ? auction.get() : null;
	}

	@PostMapping(path = "/auctionItems", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Auction postAuctionItems(@RequestBody Auction auction) {
		log.info("Post Auction Item");
		auction.setCurrentBid(0.00);
		Auction item = repo.save(auction);
		return new Auction(item.getAuctionItemId());
	}

	@PostMapping("/bids")
	public void bids(@RequestBody Auction auction) throws Exception {
		log.info("Bid for an Item.");
		Optional<Auction> item = repo.findById(auction.getAuctionItemId());

		Auction updateItem = item.isPresent() ? item.get() : null;

		if (updateItem == null)
			throw new ResourceNotFoundException("Item Not found.");

		if (updateItem.getReservePrice().compareTo(auction.getMaxAutoBidAmount()) > 0) {
			if (updateItem.getCurrentBid().compareTo(auction.getMaxAutoBidAmount()) < 0) {
				updateItem.setCurrentBid(auction.getMaxAutoBidAmount());
				updateItem.setBidderName(auction.getBidderName());
				repo.save(updateItem);
				throw new ReservedPriceNotMetException("The Reserved Price is not met.");
			}
		}

	}

}

package com.ally.financial.auctionapplication.controller;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	AuctionRepository repo;

	@GetMapping
	public String getAuction() {
		return "Welcome to Ally Auction.";
	}

	@GetMapping("/auctionItems")
	public List<Auction> getAuctionItems() {
		return repo.findAll();
	}

	@GetMapping("/auctionItems/{auctionItemId}")
	public Optional<Auction> getAuctionItemById(@PathVariable Long auctionItemId) {
		return repo.findById(auctionItemId);
	}

	@PostMapping(path = "/auctionItems", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Auction postAuctionItems(@RequestBody Auction auction) {
		auction.setCurrentBid(0.00);
		Auction item = repo.save(auction);
		return new Auction(item.getAuctionItemId());
	}

	@PostMapping("/bids")
	public void bids(@RequestBody Auction auction) throws Exception {
		Optional<Auction> item = repo.findById(auction.getAuctionItemId());

		Auction updateItem = item.isPresent() ? item.get() : null;

		if (updateItem == null)
			throw new ResourceNotFoundException("Item Not found.");

		if (updateItem.getReservePrice().compareTo(auction.getMaxAutoBidAmount()) >= 0) {
			Double currentBid = updateItem.getCurrentBid().compareTo(auction.getMaxAutoBidAmount()) >= 0
					? updateItem.getCurrentBid()
					: auction.getMaxAutoBidAmount();

			updateItem.setCurrentBid(currentBid);
			updateItem.setBidderName(auction.getBidderName());

			repo.save(updateItem);

			throw new ReservedPriceNotMetException("The Reserved Price is not met.");
		}

	}

}

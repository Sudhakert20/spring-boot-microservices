package com.ally.financial.auctionapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include. NON_NULL)
@Entity
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long auctionItemId;
	private Double currentBid;
	private Double maxAutoBidAmount;
	private String bidderName;
	private Double reservePrice;
	@OneToOne(cascade=CascadeType.ALL)
	private Item item;
	
	public Auction() {}
	
	public Auction(Long auctionItemId) {
		super();
		this.auctionItemId = auctionItemId;
	}

	public Long getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(Long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

	public Double getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(Double currentBid) {
		this.currentBid = currentBid;
	}

	public Double getMaxAutoBidAmount() {
		return maxAutoBidAmount;
	}

	public void setMaxAutoBidAmount(Double maxAutoBidAmount) {
		this.maxAutoBidAmount = maxAutoBidAmount;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public Double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Auction [auctionItemId=" + auctionItemId + ", currentBid=" + currentBid + ", maxAutoBidAmount="
				+ maxAutoBidAmount + ", bidderName=" + bidderName + ", reservePrice=" + reservePrice + ", item=" + item
				+ "]";
	}

}

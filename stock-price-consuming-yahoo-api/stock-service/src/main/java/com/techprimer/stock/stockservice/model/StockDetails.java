package com.techprimer.stock.stockservice.model;

import java.math.BigDecimal;

public class StockDetails {

	private String symbol;
	private String name;
	private String exchange;
	private BigDecimal price;
	private BigDecimal previousClose;
	private BigDecimal open;
	private BigDecimal dayLow;
	private BigDecimal dayHigh;
	private BigDecimal yearLow;
	private BigDecimal yearHigh;

	public StockDetails() {
		super();
	}

	public StockDetails(String symbol, String name, String exchange, BigDecimal price, BigDecimal previousClose,
			BigDecimal open, BigDecimal dayLow, BigDecimal dayHigh, BigDecimal yearLow, BigDecimal yearHigh) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.price = price;
		this.previousClose = previousClose;
		this.open = open;
		this.dayLow = dayLow;
		this.dayHigh = dayHigh;
		this.yearLow = yearLow;
		this.yearHigh = yearHigh;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getDayLow() {
		return dayLow;
	}

	public void setDayLow(BigDecimal dayLow) {
		this.dayLow = dayLow;
	}

	public BigDecimal getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(BigDecimal dayHigh) {
		this.dayHigh = dayHigh;
	}

	public BigDecimal getYearLow() {
		return yearLow;
	}

	public void setYearLow(BigDecimal yearLow) {
		this.yearLow = yearLow;
	}

	public BigDecimal getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(BigDecimal yearHigh) {
		this.yearHigh = yearHigh;
	}

}

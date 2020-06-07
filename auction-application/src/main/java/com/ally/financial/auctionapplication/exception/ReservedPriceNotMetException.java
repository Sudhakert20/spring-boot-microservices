package com.ally.financial.auctionapplication.exception;

public class ReservedPriceNotMetException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ReservedPriceNotMetException(String message) {
		super(message);
	}

}

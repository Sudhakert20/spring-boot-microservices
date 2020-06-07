package com.ally.financial.auctionapplication.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<?> reservedPriceNotMetException(ReservedPriceNotMetException ex, WebRequest req) {
		ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.NOT_ACCEPTABLE.toString(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
}

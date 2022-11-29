package com.webtrade.exceptions;

public class NoProductFoundException extends Exception {
	
	public NoProductFoundException() {
		
	}
	
	public NoProductFoundException(String message) {
		super(message);
	}
}

package com.webtrade.exceptions;

public class NoOrderFoundException extends Exception {
	
	public NoOrderFoundException() {
		
	}
	
	public NoOrderFoundException(String message) {
		super(message);
	}
}

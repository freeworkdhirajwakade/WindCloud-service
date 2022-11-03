package com.windcloud.exception;

public class BetDetailsNotFoundException extends Exception{

	private static final long serialVersionUID = 3370147592966043877L;
	
	public BetDetailsNotFoundException() {
		super();
	}
	public BetDetailsNotFoundException(String message) {
		super(message);
	}

}

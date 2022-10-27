package com.windcloud.exception;

public class TransactionFailedException extends Exception{
	
	private static final long serialVersionUID = 6796267588746616903L;

	public TransactionFailedException() {
		super();
	}

	public TransactionFailedException(final String message) {
		super(message);
	}
}

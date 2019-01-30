package com.motoleague.exceptions;

@SuppressWarnings("serial")
public class UniqueFieldException extends RuntimeException {

	public UniqueFieldException(String message) {
		super(message);
	}
}

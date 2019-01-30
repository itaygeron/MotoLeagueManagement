package com.motoleague.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdDoesNotExistException extends RuntimeException {
	
	public IdDoesNotExistException(String message) {
		super(message);
	}

}

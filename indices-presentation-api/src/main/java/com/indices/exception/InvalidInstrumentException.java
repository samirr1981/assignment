package com.indices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class InvalidInstrumentException extends RuntimeException{
	public InvalidInstrumentException(String message) {
		super(message);
	}
}

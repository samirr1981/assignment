package com.indices.configuration;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indices.exception.InvalidInstrumentException;
import com.indices.exception.NoDataAvailableException;

@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidInstrumentException.class)
	public ResponseEntity<Object>  instrumentNotFound(String msg) throws IOException{
		 // Sending Null Body due to given requirement
	     return new ResponseEntity(msg, HttpStatus.NO_CONTENT);	
	}
	
	@ExceptionHandler(NoDataAvailableException.class)
	public ResponseEntity<Object>  noDataAvailable(String msg) throws IOException{
	     return new ResponseEntity(msg, HttpStatus.OK);	
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> genericException(String msg) {
		return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }
}

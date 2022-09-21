package com.example.demo.utils;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/*
 * Here Every Error Will come to take advice and see which section they below to.
 * Advantage -> Api caller get limited information (like Trace , which leaks folder structure , 
 * will not be shown) 
 */
@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@ExceptionHandler(value = RuntimeException.class)
	public PhoneServiceException handleException(Exception ex , WebRequest request) {
		return new PhoneServiceException(LocalDateTime.now(), 
				ex.getMessage() , request.getDescription(false)) ; 
	}
}

package com.qa.api.Exceptions;


//every exception class will extends runtimeException
public class APIException extends RuntimeException {

	
	public APIException(String Message) {
		
		super(Message);
	}
	
}

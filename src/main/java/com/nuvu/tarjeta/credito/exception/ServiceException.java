package com.nuvu.tarjeta.credito.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {


	private HttpStatus status;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException( String message ) {
		super(message);
	}
	
	public ServiceException( HttpStatus status ) {
		super();
		this.status = status;		
	}

	public HttpStatus getStatus() {
		return status;
	}
}

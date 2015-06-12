package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException {

	public PortfolioFullException(){
		super("Array is full");	
	}
	private static final long serialVersionUID = 1L;
}

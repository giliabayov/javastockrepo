package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class BalanceException extends PortfolioException{

	public BalanceException (){
		super("balance is negative or there isnt enough balance to complete purchase");
	}
	private static final long serialVersionUID = 1L;
	
}
										 
											
										
package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockNotExistException extends PortfolioException{
	
	public StockNotExistException (){
		super("Stock does not exist");
	}
	public StockNotExistException (String symbol){
		super(symbol+ " - stock does not exist");
	}
	private static final long serialVersionUID = 1L;
}

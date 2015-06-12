package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException{

	public StockAlreadyExistsException (){
		super("Stock is already exists in the portfolio");
	}
	public StockAlreadyExistsException (String symbol){
		super(symbol + "- stock is already exists in the portfolio");
	}
	private static final long serialVersionUID = 1L;

}

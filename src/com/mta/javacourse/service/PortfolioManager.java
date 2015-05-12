package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioManager {

	public Portfolio getPortfolio(){
		Portfolio newPortfolio = new Portfolio("Portfolio");
	
		Portfolio myPortfolio = new Portfolio("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000f);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 11, 15);
		Date date4 = cal.getTime();
		Date date5 = cal.getTime();
		Date date6 = cal.getTime();

		Stock stock4, stock5, stock6;
		stock4 = new Stock("PIH",10.0f, 8.5f, date4);
		stock5 = new Stock("AAL", 30.0f, 25.5f, date5);
		stock6 = new Stock("CAAS", 20.0f, 15.5f, date6);
		
		myPortfolio.buyStock(stock4, 20);
		myPortfolio.buyStock(stock5, 30);
		myPortfolio.buyStock(stock6, 40);
				
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
				
		
		return myPortfolio;
		
	}
}

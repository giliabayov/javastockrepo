package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.Portfolio;

public class PortfolioManager {

	public Portfolio getPortfolio(){
		Portfolio newPortfolio = new Portfolio();
		newPortfolio.setTitle("Gili");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		Date date1= cal.getTime();
		Date date2= cal.getTime();
		Date date3= cal.getTime();
		
		Stock stock1, stock2, stock3;
		stock1= new Stock ("PIH", 13.1f, 12.4f,date1);
		stock2= new Stock("AAL",5.78f,5.5f,date2);
		stock3= new Stock("CAAS",32.2f,31.5f,date3);
		
		newPortfolio.addStock(stock1);
		newPortfolio.addStock(stock2);
		newPortfolio.addStock(stock3);
		
		return newPortfolio;
	}
}
